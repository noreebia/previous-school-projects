#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char echoBuffer[RCVBUFSIZE];
	int recvMsgSize;
	FILE *fp;

	//clear the echoBuffer 
	memset(echoBuffer, 0, RCVBUFSIZE);
	
	if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
		DieWithError("recv() failed");

	while(recvMsgSize > 0){		
		if(send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
			DieWithError("send() failed");

		if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
	}
	/* print string received from client */
	printf("Received from client:%s\n",echoBuffer);

	/* open file in "append" mode, and if file opens successfully, print the echoBuffer to end of file and then close file */	
	fp = fopen("echo_history.log", "a");
	if(fp == NULL){
		DieWithError("Failed to open file.");
	}
	fprintf(fp, "%s\n", echoBuffer);
	fclose(fp);
	close(clntSocket);
}
