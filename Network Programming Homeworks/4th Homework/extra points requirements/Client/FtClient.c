#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

#define UPLOADFILE 'p'
#define DOWNLOADFILE 'g'
#define LISTFILES 'l'
#define ACK 'a'

void DieWithError(char *errorMessage);
int fSize(char* file);

int main(int argc, char *argv[]){
	int sock;
	struct sockaddr_in echoServAddr;
	unsigned short serverPort = 1080;
	char servIP[] = "127.0.0.1";
	char *echoString;
	char echoBuffer[RCVBUFSIZE];
	unsigned int echoStringLen;
	int bytesRcvd, totalBytesRcvd;
	//char fileName[] = "test.txt";
	char fileName[256];
	int fileSize;
	char operation;
	char msgType;
	char fileSizeInString[20];
	char fileBuffer[FILEBUFSIZE];
	int bytesSent, totalBytesSent;
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

	printf("ftp command [ p)ut g)et l)s r)ls e)xit ] ->");
	scanf("%c", &operation);

	if(operation == 'p'){
		msgType = UPLOADFILE;

		printf("Name of file to put on server:");
		scanf("%s", fileName);

		fileSize = fSize(fileName);
		sprintf(fileSizeInString, "%d", fileSize);

		fp = fopen(fileName, "r");
		if(fp == NULL){
			DieWithError("No such file exists.");
		}

		memset(fileBuffer, 0, FILEBUFSIZE);
  	 	fread(fileBuffer, fileSize, 1, fp);
		fclose(fp);
	
		/*
		if(send(sock, fileSizeInString, strlen(fileSizeInString), 0) != strlen(fileSizeInString))
			DieWithError("send() sent a different number of bytes than expected");
		
		printf("Sent file size to server. File size: %d\n", fileSize);
		*/

		if(send(sock, &msgType, 1, 0) != 1)
			DieWithError("send() sent a different number of bytes than expected");

		printf("Sent message type: %c", msgType);
				
		if(send(sock, fileName, 256, 0) != 256)
			DieWithError("send() sent a different number of bytes than expected");

		printf("Sent filename to server: %s\n", fileName);

		if(send(sock, fileSizeInString, 20, 0) != 20)
			DieWithError("send() sent a different number of bytes than expected");

		printf("Sent file size to server: %s\n", fileSizeInString);

		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");
		
		printf("Received from server: %s\n", echoBuffer);

		/*	
		memset(fileBuffer, 0, FILEBUFSIZE);

		fp = fopen(fileName,"rb");
  	 	fread(fileBuffer, sizeOfFile, 1, fp);
		fclose(fp);
		*/
		printf("Contents of file:%s, length of file:%d", fileBuffer, strlen(fileBuffer));
	
		totalBytesSent = 0;
		while(totalBytesSent < fileSize){
		if((bytesSent = send(sock, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
				DieWithError("send() sent a different number of bytes than expected");
			
			printf("Sending => ##########");
			printf("%d\n", bytesSent);				
			totalBytesSent += bytesSent;
		}
	
		//printf("total bytes sent: %d", totalBytesSent);	
		printf("%s (%d bytes) uploading succeeded to %s", fileName, totalBytesSent, servIP);
	
		memset(echoBuffer, 0, RCVBUFSIZE);
		totalBytesRcvd = 0;
		while(totalBytesRcvd < strlen("acknowledged")){
			if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
				DieWithError("recv failed or connection closed prematurely");	

			totalBytesRcvd += bytesRcvd;
		}
		printf("bytes received:%d, received content:%s\n",bytesRcvd, echoBuffer);

		printf("closing socket\n");	
	
		close(sock);
		exit(0);	
	}
	else if(operation == 'e'){
		printf("Exiting program.\n");
		close(sock);
		exit(0);
	}
	/*
	sizeOfFile = fSize(fileName);
	sprintf(fileSize, "%d", sizeOfFile);
	if(send(sock, fileSize, strlen(fileSize), 0) != strlen(fileSize))
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("file size to send: %d\n", sizeOfFile);

	if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
		DieWithError("recv failed or connection closed prematurely");

	printf("server sends: %s\n", echoBuffer);

	if(send(sock, fileName, strlen(fileName), 0) != strlen(fileName))
		DieWithError("send() sent a different number of bytes than expected");

	printf("sent filename\n");

	memset(fileBuffer, 0, FILEBUFSIZE);

	fp = fopen(fileName,"rb");
   	fread(fileBuffer, sizeOfFile, 1, fp);
	fclose(fp);
		
	printf("%s, length:%d", fileBuffer, strlen(fileBuffer));
	
	totalBytesSent = 0;
	while(totalBytesSent < sizeOfFile){
		if((bytesSent = send(sock, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
			DieWithError("send() sent a different number of bytes than expected");
		printf("%d\n", bytesSent);
		totalBytesSent += bytesSent;
	}
	
	printf("total bytes sent: %d", totalBytesSent);
	printf("sent file contents");
	
	memset(echoBuffer, 0, RCVBUFSIZE);
	totalBytesRcvd = 0;
	while(totalBytesRcvd < strlen("acknowledged")){
		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");	

		totalBytesRcvd += bytesRcvd;
	}
	printf("bytes received:%d, received content:%s\n",bytesRcvd, echoBuffer);

	printf("closing socket\n");
	
	close(sock);
	exit(0);
	*/
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
