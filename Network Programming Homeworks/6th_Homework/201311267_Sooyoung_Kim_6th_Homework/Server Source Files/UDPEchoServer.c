#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFSIZE 32

void DieWithError(char *errorMessage);

void main(){
	int servSock;
	struct sockaddr_in echoServAddr;
	struct sockaddr_in echoClntAddr;
	unsigned short echoServPort = 1080;
	unsigned int clntLen;
	int strLen;
	int addr_size;
	char buffer[BUFSIZE];

	/* Create UDP socket */
	if((servSock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(servSock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");

	/* Reset server address structure */
	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServAddr.sin_port = htons(echoServPort);

	if(bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
		DieWithError("bind() failed");

	while(1){
		memset(buffer, 0, BUFSIZE);
		
		/* Receive message from client */
		if((strLen = recvfrom(servSock, buffer, BUFSIZE, 0, (struct sockaddr*)&echoClntAddr, &clntLen)) < 0 ){
			DieWithError("recvfrom() failed");
		}

		/* Display client info */
		printf("Received UDP packet from client.\n");
		printf("Client IP: %s\n", inet_ntoa(echoClntAddr.sin_addr));
		printf("Client Port: %d\n", ntohs(echoClntAddr.sin_port));

		printf("msg<%s\n", buffer);
		
		/* Send message back to client */
		clntLen = sizeof(echoClntAddr);	
		if(sendto(servSock, buffer, strLen, 0, (struct sockaddr*)&echoClntAddr, clntLen) < 0){
			DieWithError("sendto() failed.");
		}
		printf("msg>%s\n\n", buffer);
	}
}
