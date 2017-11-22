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
	unsigned int clntLen, clntLen2;

	int chatSock;
	int chatClientSock;
	struct sockaddr_in chatServAddr;
	struct sockaddr_in chatClientAddr;
	unsigned short chatServPort = 1081;

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

	//chat socket
	if((chatSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(chatSock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");
	
	memset(&chatServAddr, 0, sizeof(chatServAddr));
	chatServAddr.sin_family = AF_INET;
	chatServAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	chatServAddr.sin_port = htons(chatServPort);

	if(bind(chatSock, (struct sockaddr *) &chatServAddr, sizeof(chatServAddr)) < 0)
		DieWithError("bind() failed");


	if(listen(chatSock, MAXPENDING) < 0)
		DieWithError("listen() failed");


	while(1){
		clntLen = sizeof(echoClntAddr);
		if((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
			DieWithError("accept() failed");

		clntLen2 = sizeof(chatClientAddr);
		if((chatClientSock = accept(chatSock, (struct sockaddr *) &chatClientAddr, &clntLen2)) < 0)
			DieWithError("accept() failed");	

		printf("%d", clntSock);
		printf("Client IP : %s\n", inet_ntoa(echoClntAddr.sin_addr));
		printf("Port : %hu\n", ntohs(echoClntAddr.sin_port));

		printf("%d", chatClientSock);
		printf("Client IP : %s\n", inet_ntoa(chatClientAddr.sin_addr));
		printf("Port : %hu\n", ntohs(chatClientAddr.sin_port));

		HandleTCPClient(clntSock);
		printf("Listening again.\n\n");
	}
}
