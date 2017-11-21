#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <unistd.h>

#define BUFSIZE 32
#define FILEBUFSIZE 1024

#define FileUploadReq 'p'
#define FileDownloadReq 'g'
#define FileDownloadError 'x'
#define FileDownloadReady 'y'
#define ListFilesReq 'r'
#define FILEACK 'a'
#define EchoReq 'c'
#define EchoRep 'h'
#define Exit 'q'

void DieWithError(char *errorMessage);

int fSize(char* file);

void HandleTCPClient(int clntSocket){
	int fileSize;
	int bytesToWrite;
	int totalBytesSent, bytesSent;
	int bytesRcvd, totalBytesRcvd;
	char fileSizeInString[20];
	char fileName[256];
	char stringBuffer[BUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char msgType=0;
	char echoStringLengthInString[20];
	int echoStringLength;
	FILE *fp;

	/* receive "hello" */
	memset(stringBuffer, 0, BUFSIZE);
	if(recv(clntSocket, stringBuffer, BUFSIZE, 0) <0){
		DieWithError("recv() failed");
	}

	printf("Msg< %s\n", stringBuffer);

	/* send "hi" */
	memset(stringBuffer, 0, BUFSIZE);
	strcpy(stringBuffer, "hi");
	if(send(clntSocket, stringBuffer, BUFSIZE, 0) != BUFSIZE)
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("Msg> %s\n\n", stringBuffer);

	while(msgType != Exit){
		/* reset string variables */
		memset(fileSizeInString, 0, 20);
		memset(fileName, 0, 256);
		memset(fileBuffer, 0, FILEBUFSIZE);
		memset(stringBuffer, 0, BUFSIZE);
		memset(echoStringLengthInString, 0, 20);
		
		/* receive msgType from client */
		if((bytesRcvd = recv(clntSocket, &msgType, 1, 0)) <=0){
			DieWithError("recv() failed");
		}

		if(msgType == EchoReq){
			totalBytesRcvd = 0;

			msgType = EchoRep;
			if(send(clntSocket, &msgType, 1, 0) != 1)
				DieWithError("send() sent a different number of bytes than expected");

			if((bytesRcvd = recv(clntSocket, echoStringLengthInString, 20, 0)) <0)
				DieWithError("recv() failed");	

			echoStringLength = atoi(echoStringLengthInString);
			if(echoStringLength > BUFSIZE){	/* if string length exceeds buffer length */
				totalBytesRcvd = 0;				
				while(totalBytesRcvd < echoStringLength){
					if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE-1, 0)) <0)
						DieWithError("recv() failed");	
					printf("Msg<%s\n", stringBuffer);

					if( send(clntSocket, stringBuffer, bytesRcvd,0) != bytesRcvd){
						DieWithError("send() failed");
					}
					printf("Msg>%s\n", stringBuffer);
					totalBytesRcvd += bytesRcvd;
					memset(stringBuffer, 0, BUFSIZE);
				}
			}
			else{	/* if string length does not exceed buffer length */
				if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				printf("Msg<%s\n", stringBuffer);

				if( send(clntSocket, stringBuffer, bytesRcvd,0) != bytesRcvd){
					DieWithError("send() failed");
				}
				printf("Msg>%s\n", stringBuffer);
			}
		}
		
		else if(msgType == FileUploadReq){
			/* receive name of file clients wants to upload */
			if((bytesRcvd = recv(clntSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("File name client will upload: %s\n", fileName);


			/* receive size of file */ 
			if((bytesRcvd = recv(clntSocket, fileSizeInString, 20, 0)) <0)
			{
				DieWithError("recv() failed");
			}
			fileSize = atoi(fileSizeInString);
			printf("File size that client will send: %d\n", fileSize);	

			/* send ACK */
			msgType = FILEACK;
			if(send(clntSocket, &msgType, 1, 0) != 1)
				DieWithError("send() sent a different number of bytes than expected");

			//open file
			fp = fopen(fileName, "wb");
			if(fp == NULL)
			{
				DieWithError("File open error");
			}

			/* receive file contents */
			printf("Receiving => ");
			totalBytesRcvd = 0;
			while(totalBytesRcvd < fileSize){
				/* if number of bytes left to receive is smaller than file buffer size, receive the number of bytes left, not the whole file buffer length*/
				if(fileSize - totalBytesRcvd < FILEBUFSIZE){
					bytesToWrite = fileSize - totalBytesRcvd;
				}
				else{	/* if number of bytes left to receive is bigger than file buffer, receive whole buffer size */
					bytesToWrite = FILEBUFSIZE;
				}
				if((bytesRcvd = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				
				printf("#");
				fwrite(fileBuffer, sizeof(char), bytesToWrite, fp);/* write to file */
				totalBytesRcvd += bytesRcvd;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}
			fclose(fp);
			printf("\n");
		
			printf("%s (%d bytes) successfully received from client\n", fileName, fileSize);

			printf("Waiting for operation from client...\n\n");
		}
		else if(msgType == FileDownloadReq){

			/* receive name of file client wants to download */
			if((bytesRcvd = recv(clntSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("Received name of file client wants to download: %s\n", fileName);			

			fileSize = fSize(fileName);		
			sprintf(fileSizeInString, "%d", fileSize);	

			/* open file. if file does not exist, send error message to client. */
			fp = fopen(fileName, "r");
			if(fp == NULL){
				printf("File does not exist. Sending error message to client.\n");
				printf("Waiting for operation from client...\n\n");
				msgType = FileDownloadError;
				if(send(clntSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				continue;
			}
			else{
				msgType = FileDownloadReady;
				if(send(clntSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
			}


			/* send size of file client wants to download */
			if(send(clntSocket, fileSizeInString, 20, 0) != 20)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent size of file to client: %s\n", fileSizeInString);

			/* receive ACK from client */
			if((bytesRcvd = recv(clntSocket, &msgType, 1, 0)) <0){
				DieWithError("recv() failed");
			}

			/* send file contents */
			printf("Sending => ");
			while( (fread(fileBuffer, 1, FILEBUFSIZE, fp)) > 0){
				if((bytesSent = send(clntSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				printf("#");
				totalBytesSent += bytesSent;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}			
			fclose(fp);
			printf("\n");

			printf("%s (%d bytes) successfully sent to client\n", fileName, fileSize);

			printf("Waiting for operation from client...\n\n");
		}
		else if(msgType == ListFilesReq){
			fp = popen("ls -l", "r");	/* use popen to read result of "ls -l"*/
			if(fp == NULL){
				DieWithError("popen failed");
			}

			fread(fileBuffer, FILEBUFSIZE, 1, fp);
			pclose(fp);

			/* sent list of directory to client */
			totalBytesSent = 0;
			while(totalBytesSent < FILEBUFSIZE){
				if((bytesSent = send(clntSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				totalBytesSent += bytesSent;
			}

			printf("Sent list of files on directory to client:");
			printf(" %s", fileBuffer);

			printf("Waiting for operation from client...\n\n");
		}
	}
	printf("Client has disconnected.\nClosing socket.\n");
	close(clntSocket);
}

/* function to get size of file */
int fSize(char* file) {
  int size;
  FILE* fh;

  fh = fopen(file, "r");
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
