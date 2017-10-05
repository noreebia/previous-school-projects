#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

#define UPLOADFILEREQUEST 'p'
#define DOWNLOADFILEREQUEST 'g'
#define LISTFILESREQUEST 'l'
#define ACK 'a'

void DieWithError(char *errorMessage);
int fSize(char* file);

int main(int argc, char *argv[]){

	unsigned short serverPort = 1080;
	char servIP[] = "127.0.0.1";

	struct sockaddr_in echoServAddr;
	unsigned int echoStringLen;
	int sock;
	int fileSize;
	int bytesRcvd, totalBytesRcvd;
	int bytesSent, totalBytesSent;
	char *echoString;
	char echoBuffer[RCVBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[256];
	char fileSizeInString[20];
	char operation;
	char msgType;
	FILE *fp;

	if((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(serverPort);

	if(connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
		DieWithError("connect() failed");

	printf("Welcome to Socket FT client!\n");

	while(operation != 'e'){
		memset(echoBuffer, 0, RCVBUFSIZE);
		memset(fileName, 0, 256);
		memset(fileSizeInString, 0, 20);	
		memset(fileBuffer, 0, FILEBUFSIZE);	

		printf("ftp command [ p)ut g)et l)s r)ls e)xit ] ->");
		scanf("%c", &operation);

		if(operation == 'p'){
			msgType = UPLOADFILEREQUEST;

			printf("Name of file to put on server:");
			scanf("%s", fileName);
	
			fileSize = fSize(fileName);
			sprintf(fileSizeInString, "%d", fileSize);

			memset(fileBuffer, 0, FILEBUFSIZE);

			fp = fopen(fileName, "r");
			if(fp == NULL){
				DieWithError("No such file exists.");
			}
  		 	fread(fileBuffer, fileSize, 1, fp);
			fclose(fp);

			//send msgtype
			if(send(sock, &msgType, 1, 0) != 1)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent msgtype: %c\n", msgType);
				
			//send file name to upload
			if(send(sock, fileName, 256, 0) != 256)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent filename to server: %s\n", fileName);

			//send file size
			if(send(sock, fileSizeInString, 20, 0) != 20)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent file size to server: %s\n", fileSizeInString);

			//receive acknowledgement
			memset(echoBuffer, 0, RCVBUFSIZE);
			totalBytesRcvd = 0;
			while(totalBytesRcvd < strlen("acknowledged")){
				if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
		
			printf("Received from server: %s\n", echoBuffer);
			printf("Contents of file:%s, length of file:%d\n", fileBuffer, strlen(fileBuffer));
	
			//receive file contents
			totalBytesSent = 0;
			while(totalBytesSent < fileSize){
			if((bytesSent = send(sock, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
				DieWithError("send() sent a different number of bytes than expected");
			
			printf("Sending => ##########\n");
			printf("bytes sent:%d\n", bytesSent);				
			totalBytesSent += bytesSent;
			}

			printf("%s (%d bytes) uploading succeeded to %s", fileName, totalBytesSent, servIP);
	
			//receive acknowledgement
			memset(echoBuffer, 0, RCVBUFSIZE);
			totalBytesRcvd = 0;
			while(totalBytesRcvd < strlen("acknowledged")){
				if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
			printf("bytes received:%d, received content:%s\n",bytesRcvd, echoBuffer);
		}
		else if(operation == 'g'){
			msgType = DOWNLOADFILEREQUEST;

			printf("Name of file to get from server:");
			scanf("%s", fileName);

			if(send(sock, &msgType, 1, 0) != 1)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent message type: %c\n", msgType);
				
			//send name of file to download;
			if(send(sock, fileName, 256, 0) != 256)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent name of file that I want to download to server: %s\n", fileName);

			//receive size of file;
			memset(fileSizeInString, 0, 20);
			totalBytesRcvd = 0;
			while(totalBytesRcvd < 20){
				if((bytesRcvd = recv(sock, fileSizeInString, 20, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	

				totalBytesRcvd += bytesRcvd;
			}
			
			printf("bytes received:%d", bytesRcvd);
			printf("Received file size from server: %s", fileSizeInString);
			fileSize = atoi(fileSizeInString);
			printf("Length of file that server will send:%d\n", fileSize);

			//send acknowledgement
			strcpy(echoBuffer, "acknowledged");
			if(send(sock, echoBuffer, RCVBUFSIZE,0) != RCVBUFSIZE)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent to server: %s\n", echoBuffer);

			//receive file contents
			totalBytesRcvd = 0;
			while(totalBytesRcvd < fileSize){
				if((bytesRcvd = recv(sock, fileBuffer, FILEBUFSIZE, 0)) <= 0)
					DieWithError("recv failed or connection closed prematurely");	
				printf("Receiving => ##########\n");
				printf("received bytes:%d\n", bytesRcvd);
				totalBytesRcvd += bytesRcvd;
			}
			
			//open file and write
			fp = fopen(fileName, "w");
			if(fp == NULL){
				DieWithError("fopen failed.");
			}
			fwrite(fileBuffer, sizeof(char), fileSize, fp);
			fclose(fp);

			printf("%s (%d bytes) downloading succeeded from %s\n", fileName, totalBytesRcvd, servIP);

			//send acknowledgement of successful file download
			memset(echoBuffer, 0, RCVBUFSIZE);
			strcpy(echoBuffer, "acknowledged");
			if(send(sock, echoBuffer, RCVBUFSIZE,0) != RCVBUFSIZE)
				DieWithError("send() sent a different number of bytes than expected");
		}
		
		else if(operation == 'l'){
			fp = popen("ls", "r");
			if(fp == NULL){
				DieWithError("popen failed");
			}
			
			while( fgets(fileBuffer, FILEBUFSIZE,fp ) != NULL){
				printf(" %s", fileBuffer);
			}
			pclose(fp);
		}
		
	}
	/*
	if(send(sock, &msgType, 1, 0) != 1)
		DieWithError("send() sent a different number of bytes than expected");
	*/

	if(send(sock, &operation, 1, 0) != 1)
		DieWithError("send() sent a different number of bytes than expected");
	printf("Sent msgtype: %c\n", operation);

	printf("Exiting program.\n");
	close(sock);
	exit(0);
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
