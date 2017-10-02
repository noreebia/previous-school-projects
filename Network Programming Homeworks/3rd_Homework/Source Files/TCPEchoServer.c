#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAXPENDING 5

void DieWithError(char *errorMessage);
void HandleTCPClient(int clntSocket);

void main(){
	int servSock;
	int clntSock;
	struct sockaddr_in echoServAddr;
	struct sockaddr_in echoClntAddr;
	unsigned short echoServPort = 1080;
	unsigned int clntLen;

	if((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(servSock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServAddr.sin_port = htons(echoServPort);

	if(bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
		DieWithError("bind() failed");

	if(listen(servSock, MAXPENDING) < 0)
		DieWithError("listen() failed");

	while(1){
		clntLen = sizeof(echoClntAddr);
	
		if((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
			DieWithError("accept() failed");

		printf("Client IP : %s\n", inet_ntoa(echoClntAddr.sin_addr));
		printf("Port : %hu\n", ntohs(echoClntAddr.sin_port));

		HandleTCPClient(clntSock);
		printf("Listening again.\n\n");
	}
}
