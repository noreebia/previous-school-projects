#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char fileSizeInString[20];
	int fileSize;
	char sendBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[256];
	int recvMsgSize;
	int receivedFileSize;
	char msgType;
	FILE *fp;

	//clear the echoBuffer 
	//memset(recvBuffer, 0, RCVBUFSIZE);
	
	memset(fileSizeInString, 0, 20);
	memset(fileName, 0, 256);
	memset(fileBuffer, 0, FILEBUFSIZE);

	if((recvMsgSize = recv(clntSocket, &msgType, 1, 0)) <0)
	{
		DieWithError("recv() failed");
	}

	printf("Received msgtype from client:%c", msgType);
	
	if((recvMsgSize = recv(clntSocket, fileName, 256, 0)) <0)
		DieWithError("recv() failed");	

	printf("received filename: %s 1\n", fileName);

	if((recvMsgSize = recv(clntSocket, fileSizeInString, 20, 0)) <0)
	{
		DieWithError("recv() failed");
	}
	fileSize = atoi(fileSizeInString);
	printf("file size that client will send: %d\n", fileSize);	

	strcpy(sendBuffer, "acknowledged");
	send(clntSocket, sendBuffer, RCVBUFSIZE,0);
	
	printf("sent acknowledgement\n");

	fp = fopen(fileName, "wb");
	if(fp == NULL)
	{
		DieWithError("File open error");
	}
	
	receivedFileSize = 0;
	while(receivedFileSize < fileSize){
		printf("receiving...\n");		
		if((recvMsgSize = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
		
		printf("%d",recvMsgSize);
		//fwrite(fileBuffer, sizeof(char), FILEBUFSIZE, fp);
		receivedFileSize += recvMsgSize;
	}
	
	printf("%s", fileBuffer);
	fwrite(fileBuffer, sizeof(char), fileSize, fp);

	printf("file received successfully");
	/*
	strcpy(sendBuffer, "acknowledged");
	send(clntSocket, sendBuffer, strlen(sendBuffer),0);
	*/
	if( send(clntSocket, sendBuffer, RCVBUFSIZE,0) != RCVBUFSIZE){
		DieWithError("send() failed");
	}

	printf("sent acknowledgement\nclosing socket");
	fclose(fp);
	close(clntSocket);
}
