#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

void DieWithError(char *errorMessage);

int fSize(char* file);

void HandleTCPClient(int clntSocket){
	/*
	char fileSizeInString[20];
	int fileSize;
	char stringBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[256];
	int recvMsgSize;
	char msgType;
	FILE *fp;
	int totalBytesSent, bytesSent;
	int bytesRcvd, totalBytesRcvd;
	*/

	int fileSize;
	//int recvMsgSize;
	int totalBytesSent, bytesSent;
	int bytesRcvd, totalBytesRcvd;
	char fileSizeInString[20];
	char fileName[256];
	char stringBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char msgType;
	FILE *fp;

	
	while(1){
		memset(fileSizeInString, 0, 20);
		memset(fileName, 0, 256);
		memset(fileBuffer, 0, FILEBUFSIZE);
		memset(stringBuffer, 0, RCVBUFSIZE);

		if((bytesRcvd = recv(clntSocket, &msgType, 1, 0)) <0)
		{
			DieWithError("recv() failed");
		}

		if(msgType == 'p'){
			printf("Received msgtype from client:%c\n", msgType);
	

			//receive name of file clients wants to upload
			if((bytesRcvd = recv(clntSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("received filename: %s 1\n", fileName);


			//receive size of file client wants to upload
			if((bytesRcvd = recv(clntSocket, fileSizeInString, 20, 0)) <0)
			{
				DieWithError("recv() failed");
			}
			fileSize = atoi(fileSizeInString);
			printf("file size that client will send: %d\n", fileSize);	

			//send ack to client
			strcpy(stringBuffer, "acknowledged");
			if( send(clntSocket, stringBuffer, RCVBUFSIZE,0) != RCVBUFSIZE){
				DieWithError("send() failed");
			}

			printf("sent to client:%s\n", stringBuffer);

			fp = fopen(fileName, "wb");
			if(fp == NULL)
			{
				DieWithError("File open error");
			}
			/*
			receivedFileSize = 0;
			while(receivedFileSize < fileSize){
				printf("receiving...\n");		
				if((recvMsgSize = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				
				printf("%d",recvMsgSize);
				//fwrite(fileBuffer, sizeof(char), FILEBUFSIZE, fp);
				receivedFileSize += recvMsgSize;
			}
			*/

			//receive file contents
			totalBytesRcvd = 0;
			while(totalBytesRcvd < fileSize){
				printf("receiving...\n");		
				if((bytesRcvd = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				
				printf("bytes received:%d\n",bytesRcvd);
				//fwrite(fileBuffer, sizeof(char), FILEBUFSIZE, fp);
				totalBytesRcvd += bytesRcvd;
			}
			
			printf("%s", fileBuffer);
			fwrite(fileBuffer, sizeof(char), fileSize, fp);
			fclose(fp);
			printf("file received successfully\n");

			//send ack to signal successful file upload
			if( send(clntSocket, stringBuffer, RCVBUFSIZE,0) != RCVBUFSIZE){
				DieWithError("send() failed");
			}

			printf("sent to client:%s \nawaiting for operation\n", stringBuffer);
		}
		else if(msgType == 'g'){
			printf("Received msgtype from client:%c\n", msgType);

			//receive name of file client wants to download
			if((bytesRcvd = recv(clntSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("received filename: %s\n", fileName);			

			fileSize = fSize(fileName);		
			sprintf(fileSizeInString, "%d", fileSize);	

			fp = fopen(fileName, "r");
			if(fp == NULL){
				DieWithError("No such file exists.");
			}

			memset(fileBuffer, 0, FILEBUFSIZE);
  		 	fread(fileBuffer, fileSize, 1, fp);
			fclose(fp);

			//send size of file client wants to download
			if(send(clntSocket, fileSizeInString, 20, 0) != 20)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent file size to client: %s\n", fileSizeInString);

			//receive ack from client
			if( (bytesRcvd = recv(clntSocket, stringBuffer, RCVBUFSIZE - 1, 0)) <= 0)
				DieWithError("recv failed or connection closed prematurely");
		
			printf("Received from client: %s\n", stringBuffer);

			//sent contents of file to client
			totalBytesSent = 0;
			while(totalBytesSent < fileSize){
			if((bytesSent = send(clntSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
				DieWithError("send() sent a different number of bytes than expected");
			
			printf("Sending => ##########\n");
			printf("bytes sent:%d\n", bytesSent);				
			totalBytesSent += bytesSent;
			}

			printf("sent file contents to client:%s\n", fileBuffer);

			//receive ack from client 
			memset(stringBuffer, 0, RCVBUFSIZE);
			totalBytesRcvd = 0;
			while(totalBytesRcvd < strlen("acknowledged")){
				if((bytesRcvd = recv(clntSocket, stringBuffer, RCVBUFSIZE - 1, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
			printf("bytes received:%d, received content:%s\n", bytesRcvd, stringBuffer);
			printf("awaiting for operation\n");
		}
	}
	close(clntSocket);
}


int fSize(char* file) {
  int size;
  FILE* fh;

  fh = fopen(file, "r"); //binary mode
  if(fh != NULL){
    if( fseek(fh, 0, SEEK_END) ){
      fclose(fh);
      return -1;
    }

    size = ftell(fh);
    fclose(fh);
    return size;
  }

  return -1; //error
}
