#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define BUFSIZE 32
#define FILEBUFSIZE 1024

#define UPLOADFILEREQUEST 'p'
#define DOWNLOADFILEREQUEST 'g'
#define LISTFILESREQUEST 'r'
#define ACK 'a'
#define ECHO 'c'
#define EXIT 'q'

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
	FILE *fp;

	memset(stringBuffer, 0, BUFSIZE);
	if(recv(clntSocket, stringBuffer, BUFSIZE, 0) <0){
		DieWithError("recv() failed");
	}

	printf("Msg< %s\n", stringBuffer);

	memset(stringBuffer, 0, BUFSIZE);
	strcpy(stringBuffer, "hi");
	if(send(clntSocket, stringBuffer, BUFSIZE, 0) != BUFSIZE)
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("Msg> %s\n", stringBuffer);

	while(msgType != EXIT){
		memset(fileSizeInString, 0, 20);
		memset(fileName, 0, 256);
		memset(fileBuffer, 0, FILEBUFSIZE);
		memset(stringBuffer, 0, BUFSIZE);
		
		if((bytesRcvd = recv(clntSocket, &msgType, 1, 0)) <0){
			DieWithError("recv() failed");
		}
		printf("received msgType:%c\n", msgType);

		if(msgType == ECHO){
			totalBytesRcvd = 0;
			while(totalBytesRcvd < BUFSIZE){
				if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
			printf("Msg<%s\n", stringBuffer);

			if(strcmp(stringBuffer, "FT") != 0){
				msgType = ECHO;
				if(send(clntSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				if( send(clntSocket, stringBuffer, BUFSIZE,0) != BUFSIZE){
					DieWithError("send() failed");
				}
				printf("Msg>%s\n", stringBuffer);
			}
			else{
				msgType = ACK;

				if(send(clntSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
			}
		}
		
		else if(msgType == UPLOADFILEREQUEST){
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
			if( send(clntSocket, stringBuffer, BUFSIZE,0) != BUFSIZE){
				DieWithError("send() failed");
			}

			printf("sent to client:%s\n", stringBuffer);

			//open file
			fp = fopen(fileName, "wb");
			if(fp == NULL)
			{
				DieWithError("File open error");
			}

			//receive file contents
			totalBytesRcvd = 0;
			while(totalBytesRcvd < fileSize){
				if(fileSize - totalBytesRcvd < FILEBUFSIZE){
					bytesToWrite = fileSize - totalBytesRcvd;
				}
				else{
					bytesToWrite = FILEBUFSIZE;
				}
				printf("receiving...\n");		
				if((bytesRcvd = recv(clntSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				
				printf("bytes received:%d\n",bytesRcvd);
				fwrite(fileBuffer, sizeof(char), bytesToWrite, fp);
				totalBytesRcvd += bytesRcvd;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}
			printf("%s", fileBuffer);
			fclose(fp);
			printf("file received successfully\n");

			//send ack to signal successful file upload
			if( send(clntSocket, stringBuffer, BUFSIZE,0) != BUFSIZE){
				DieWithError("send() failed");
			}

			printf("sent to client:%s \nawaiting for operation\n", stringBuffer);
		}
		else if(msgType == DOWNLOADFILEREQUEST){
			printf("Received msgtype from client:%c\n", msgType);

			//receive name of file client wants to download
			if((bytesRcvd = recv(clntSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("received filename: %s\n", fileName);			

			fileSize = fSize(fileName);		
			sprintf(fileSizeInString, "%d", fileSize);	

			//open file
			fp = fopen(fileName, "r");
			if(fp == NULL){
				DieWithError("No such file exists.");
			}

			memset(fileBuffer, 0, FILEBUFSIZE);

			//send size of file client wants to download
			if(send(clntSocket, fileSizeInString, 20, 0) != 20)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent file size to client: %s\n", fileSizeInString);

			//receive ack from client
			if( (bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE - 1, 0)) <= 0)
				DieWithError("recv failed or connection closed prematurely");
		
			printf("Received from client: %s\n", stringBuffer);

			//sent contents of file to client
			while( (fread(fileBuffer, 1, FILEBUFSIZE, fp)) > 0){
				if((bytesSent = send(clntSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				printf("Sending => ##########\n");
				printf("bytes sent:%d\n", bytesSent);				
				totalBytesSent += bytesSent;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}			
			fclose(fp);

			printf("sent file contents to client:%s\n", fileBuffer);

			//receive ack from client 
			memset(stringBuffer, 0, BUFSIZE);
			totalBytesRcvd = 0;
			while(totalBytesRcvd < strlen("acknowledged")){
				if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE - 1, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
			printf("bytes received:%d, received content:%s\n", bytesRcvd, stringBuffer);
			printf("awaiting for operation\n");
		}
		else if(msgType == LISTFILESREQUEST){
			fp = popen("ls -l", "r");
			if(fp == NULL){
				DieWithError("popen failed");
			}

			printf("popened");
			fread(fileBuffer, FILEBUFSIZE, 1, fp);
			pclose(fp);

			printf("%s", fileBuffer);

			totalBytesSent = 0;
			while(totalBytesSent < FILEBUFSIZE){
				if((bytesSent = send(clntSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				printf("sending list of files on server to client\n");
				printf("bytes sent:%d\n", bytesSent);				
				totalBytesSent += bytesSent;
			}
			printf("total bytes sent:%d\n", totalBytesSent);
			
			totalBytesRcvd = 0;
			while(totalBytesRcvd < strlen("acknowledged")){
				if(  (bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE, 0)) < 0)
					DieWithError("recv failed or connection closed prematurely");	
				printf("why");
				printf("total bytes received:%d, bytes received:%d\n", totalBytesRcvd, bytesRcvd);

				totalBytesRcvd += bytesRcvd;
				printf("total bytes received:%d", totalBytesRcvd);
			}
			
			printf("total bytes received:%d, received string from client:%s\n", bytesRcvd, stringBuffer);
			printf("awaiting operation\n");
		}
	}
	printf("received:%c\n closing socket.\n", msgType);
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
