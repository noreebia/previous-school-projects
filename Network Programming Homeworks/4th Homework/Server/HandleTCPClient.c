#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char fileSize[RCVBUFSIZE];
	int fileSizeInt;
	char sendBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[RCVBUFSIZE];
	int receivedMsgSize;
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

	fileSizeInt = atoi(fileSize);
	
	printf("file size that I will receive: %d", fileSizeInt);	

	strcpy(sendBuffer, "acknowledged");

	send(clntSocket, sendBuffer, RCVBUFSIZE,0);
	
	printf("sent acknowledgement");

	if((recvMsgSize = recv(clntSocket, fileName, RCVBUFSIZE, 0)) <0)
		DieWithError("recv() failed");	

	fp = fopen(fileName, "w");
	if(fp == NULL)
	{
		DieWithError("File open error");
	}

	receivedFileSize = 0;
	while(receivedFileSize < fileSizeInt){		
		if((recvMsgSize = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
		fwrite(fileBuffer, sizeof(char), FILEBUFSIZE, fp);
		receivedFileSize += recvMsgSize;
	}
	printf("file received successfully");
	fclose(fp);
	close(clntSocket);
}
