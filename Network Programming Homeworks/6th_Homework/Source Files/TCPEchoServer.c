#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAXPENDING 5
#define BUFSIZE 32

void DieWithError(char *errorMessage);
void HandleTCPClient(int clntSocket);

void main(){
	int servSock;
	int clntSock;
	struct sockaddr_in echoServAddr;
	struct sockaddr_in echoClntAddr;
	unsigned short echoServPort = 1080;
	unsigned int clntLen;
	int strLen;
	char buffer[BUFSIZE];
	int addr_size;

	if((servSock = socket(PF_INET, SOCK_DGRAM, 0)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(servSock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServAddr.sin_port = htons(echoServPort);

	if(bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
		DieWithError("bind() failed");

	/*
	memset(buffer, 0, BUFSIZE);
	strLen = recvfrom(servSock, buffer, BUFSIZE, 0, (struct sockaddr*)&echoClntAddr, &clntLen);
	printf("msg<%s\n", buffer);

	if(!strcmp(buffer, "hello")){
		memset(buffer, 0, BUFSIZE);
		strcpy(buffer, "hi");
		sendto(servSock, buffer, strLen, 0, (struct sockaddr*)&echoClntAddr, sizeof(echoClntAddr));
		printf("msg>%s\n", buffer);
	}
	*/

	while(1){
		clntLen = sizeof(echoClntAddr);
		
		memset(buffer, 0, BUFSIZE);
		strLen = recvfrom(servSock, buffer, BUFSIZE, 0, (struct sockaddr*)&echoClntAddr, &clntLen);
		printf("msg<%s\n", buffer);

		sendto(servSock, buffer, strLen, 0, (struct sockaddr*)&echoClntAddr, sizeof(echoClntAddr));
		printf("msg>%s\n", buffer);
	}
}
