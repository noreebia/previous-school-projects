#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char fileSize[RCVBUFSIZE];
	int sizeOfFile;
	char sendBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[RCVBUFSIZE];
	int recvMsgSize;
	int receivedFileSize;
	FILE *fp;

	//clear the echoBuffer 
	//memset(recvBuffer, 0, RCVBUFSIZE);
	
	memset(fileSize, 0, RCVBUFSIZE);
	memset(fileName, 0, RCVBUFSIZE);
	memset(fileBuffer, 0, FILEBUFSIZE);

	if((recvMsgSize = recv(clntSocket, fileSize, RCVBUFSIZE, 0)) <0)
	{
		DieWithError("recv() failed");
	}

	sizeOfFile = atoi(fileSize);

	printf("file size that I will receive: %d\n", sizeOfFile);	

	strcpy(sendBuffer, "acknowledged");

	send(clntSocket, sendBuffer, RCVBUFSIZE,0);
	
	printf("sent acknowledgement\n");

	if((recvMsgSize = recv(clntSocket, fileName, RCVBUFSIZE, 0)) <0)
		DieWithError("recv() failed");	

	printf("received filename\n");

	fp = fopen(fileName, "wb");
	if(fp == NULL)
	{
		DieWithError("File open error");
	}
	
	receivedFileSize = 0;
	while(receivedFileSize < sizeOfFile){
		printf("receiving...\n");		
		if((recvMsgSize = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
		
		printf("%d",recvMsgSize);
		//fwrite(fileBuffer, sizeof(char), FILEBUFSIZE, fp);
		receivedFileSize += recvMsgSize;
	}
	
	printf("%s", fileBuffer);
	fwrite(fileBuffer, sizeof(char), sizeOfFile, fp);

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
