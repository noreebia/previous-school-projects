#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char echoBuffer[RCVBUFSIZE];
	char *connectionInitString = "hi";
	int recvMsgSize;

	printf("start of handletcpclient \n");
	//clear the echoBuffer 

	
	if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
		DieWithError("recv() failed");
	
	printf("Received from client:%s\n",echoBuffer);

	if( strcmp(echoBuffer, "hello") == 0){
		if(send(clntSocket, connectionInitString, strlen(connectionInitString), 0) < 0){
			DieWithError("send() failed");
		}
		printf("Sent to client: %s\n", connectionInitString);
	}	
	
	printf("commencing echo chat.\n\n");

	/*
	while(recvMsgSize > 0){		
		if(send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
			DieWithError("send() failed");

		if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
	}
	*/
	memset(echoBuffer, 0, RCVBUFSIZE);
	
	while( recvMsgSize > 0){
		if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
		printf("Received from client:%s\n", echoBuffer);

		if(send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
			DieWithError("send() failed");
		printf("Sent to client:%s\n", echoBuffer);

		memset(echoBuffer, 0, RCVBUFSIZE);
	}
	printf("end of handtcpclient \n");
	close(clntSocket);
}
