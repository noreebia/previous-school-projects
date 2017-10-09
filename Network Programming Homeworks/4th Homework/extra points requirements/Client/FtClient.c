#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define STRINGBUFSIZE 32
#define FILEBUFSIZE 1024

#define UPLOADFILEREQUEST 'p'
#define DOWNLOADFILEREQUEST 'g'
#define LISTFILESREQUEST 'r'
#define DOWNLOADFIILEERROR 'x'
#define FILEACK 'a'
#define ECHOREQ 'c'
#define ECHOREP 'h'
#define EXIT 'q'

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

int main(int argc, char *argv[]){
	/*
	unsigned short serverPort = 1080;
	char servIP[] = "127.0.0.1";
	*/
	unsigned short serverPort;
	char servIP[20];

	struct sockaddr_in echoServAddr;
	unsigned int echoStringLen;
	int sock;
	int fileSize;
	int bytesToWrite;
	int bytesRcvd, totalBytesRcvd;
	int bytesSent, totalBytesSent;
	char *echoString;
	char stringBuffer[STRINGBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[256];
	char fileSizeInString[20];
	char command[20];
	char operation;
	char msgType;
	int mode=1;
	int stringLength;
	char stringLengthInString[20];
	FILE *fp;

	printf("server ip : ");
	scanf("%s", servIP);

	printf("port : ");
	scanf("%hu", &serverPort);

	if((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(serverPort);

	if(connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
		DieWithError("connect() failed");

	strcpy(stringBuffer, "hello");
	if(send(sock, stringBuffer, STRINGBUFSIZE, 0) != STRINGBUFSIZE)
		DieWithError("send() sent a different number of bytes than expected");

	printf("Msg> %s\n", stringBuffer);	

	memset(stringBuffer, 0, STRINGBUFSIZE);
	if((bytesRcvd = recv(sock, stringBuffer, STRINGBUFSIZE, 0)) <0){
		DieWithError("recv() failed");
	}

	printf("Msg< %s\n\n", stringBuffer);	

	while(1){

		if(mode == 1){
			printf("Msg> ");
			/* Read string through input */
			scanf(" %s", stringBuffer);


			if(strcmp(stringBuffer, "/quit") == 0){
				msgType = Exit;
				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				
				break;
			}
			
			else if(strcmp(stringBuffer, "FT") == 0){
				mode = 2;
				printf("Welcome to Socket FT client!\n");
			}
			
			else{
				msgType = EchoReq;
				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				//printf("sent msgType: %c\n", msgType);
				/* Send inputted string to server */
				stringLength = strlen(stringBuffer);
				sprintf(stringLengthInString, "%d", stringLength);

				if( send(sock, stringLengthInString, 20,0) != 20){	
					DieWithError("recv() failed or connection closed prematurely");
				}			

				
				if( send(sock, stringBuffer, stringLength,0) != stringLength){	
					DieWithError("recv() failed or connection closed prematurely");
				}
				
				//printf("sent stringbuffer:%s", stringBuffer);
		
				if((bytesRcvd = recv(sock, &msgType, 1, 0)) <0){
					DieWithError("recv() failed");
				}


				//printf("received msgType:%c\n", msgType);

				/* Receive echoed string from server */
				memset(stringBuffer, 0, STRINGBUFSIZE);
				//printf("length of string:%d\n", stringLength);
				totalBytesRcvd = 0;
				//while(totalBytesRcvd < stringLength){
				while(totalBytesRcvd < stringLength){
					//printf("length of string:%d\n", stringLength);
   	 	   		 	if((bytesRcvd = recv(sock, stringBuffer, STRINGBUFSIZE-1, 0)) <= 0){
						DieWithError("recv failed or connection closed prematurely");
					}			
   		    	 	totalBytesRcvd += bytesRcvd;
					//echoBuffer[bytesRcvd] = '\0';
					stringBuffer[bytesRcvd] = '\0';
	       			printf("Msg< %s\n\n", stringBuffer);
					memset(stringBuffer, 0, STRINGBUFSIZE);
				}
				//memset(stringBuffer, 0, STRINGBUFSIZE);
			}
		}

		else if(mode == 2){
			memset(fileName, 0, 256);
			memset(fileSizeInString, 0, 20);	
			memset(fileBuffer, 0, FILEBUFSIZE);	
			memset(command, 0, 20);

			printf("ftp command [p)ut g)et l)s r)ls e)xit ] -> ");
			//scanf(" %c", operation);
			scanf(" %s", command);
			if(strlen(command) != 1){
				printf("Unrecognizable command. Please try again.\n");
				continue;
			}
			operation = command[0];

			if(operation == 'p'){
				msgType = FileUploadReq;

				printf("Name of file to put on server:");
				scanf("%s", fileName);
	
				fileSize = fSize(fileName);
				sprintf(fileSizeInString, "%d", fileSize);

				fp = fopen(fileName, "rb");
				if(fp == NULL){
					//DieWithError("No such file exists.");
					printf("No such file exists. Try again.\n");
					continue;
				}

				//send msgtype
				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
	
				//printf("Sent msgtype: %c\n", msgType);	
				
				//send file name to upload
				if(send(sock, fileName, 256, 0) != 256)
					DieWithError("send() sent a different number of bytes than expected");

				//printf("Sent filename to server: %s\n", fileName);

				//send file size
				if(send(sock, fileSizeInString, 20, 0) != 20)
					DieWithError("send() sent a different number of bytes than expected");

				printf("Sent file size to server: %s\n", fileSizeInString);

				//receive acknowledgement
				/*
				memset(stringBuffer, 0, STRINGBUFSIZE);
				totalBytesRcvd = 0;
				while(totalBytesRcvd < strlen("acknowledged")){
					if((bytesRcvd = recv(sock, stringBuffer, STRINGBUFSIZE, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	

					totalBytesRcvd += bytesRcvd;
				}
				*/

				if((bytesRcvd = recv(sock, &msgType, 1, 0)) <0){
					DieWithError("recv() failed");
				}
				
				//printf("Received from server: %c\n", msgType);
				//printf("Contents of file:%s, length of file:%d\n", fileBuffer, strlen(fileBuffer));
	
				printf("Sending => ");
				//send file contents
				while( fread(fileBuffer, 1, FILEBUFSIZE, fp) > 0){
					if((bytesSent = send(sock, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
						DieWithError("send() sent a different number of bytes than expected");
			
					printf("#");
					//printf("Sending => ##########\n");
					//printf("bytes sent:%d\n", bytesSent);				
					totalBytesSent += bytesSent;
					memset(fileBuffer, 0, FILEBUFSIZE);
				}
				fclose(fp);
				printf("\n");
			

				printf("%s (%d bytes) uploading succeeded to %s\n", fileName, fileSize, servIP);
	
				//receive acknowledgement
				/*
				memset(stringBuffer, 0, STRINGBUFSIZE);
				totalBytesRcvd = 0;
				while(totalBytesRcvd < strlen("acknowledged")){
					if((bytesRcvd = recv(sock, stringBuffer, STRINGBUFSIZE - 1, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	
	
					totalBytesRcvd += bytesRcvd;
				}
				printf("bytes received:%d, received content:%s\n",bytesRcvd, stringBuffer);
				*/
			}
			else if(operation == 'g'){
				msgType = FileDownloadReq;

				printf("Name of file to get from server:");
				scanf("%s", fileName);

				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				//printf("Sent message type: %c\n", msgType);
				
				//send name of file to download;
				if(send(sock, fileName, 256, 0) != 256)
					DieWithError("send() sent a different number of bytes than expected");

				//printf("Sent name of file that I want to download to server: %s\n", fileName);

				//receive name of file clients wants to upload
				if((bytesRcvd = recv(sock, &msgType, 1, 0)) <0)
					DieWithError("recv() failed");	

				if(msgType == FileDownloadError){
					printf("File with that name does not exist on server. Please try again.\n");
					continue;
				}

				//receive size of file;
				memset(fileSizeInString, 0, 20);
				totalBytesRcvd = 0;
				while(totalBytesRcvd < 20){
					if((bytesRcvd = recv(sock, fileSizeInString, 20, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	

					totalBytesRcvd += bytesRcvd;
				}
			
				//printf("bytes received:%d", bytesRcvd);
				printf("Received file size from server: %s", fileSizeInString);
				fileSize = atoi(fileSizeInString);
				//printf("Length of file that server will send:%d\n", fileSize);

				fp = fopen(fileName, "w");
				if(fp == NULL){
					DieWithError("fopen failed.");
				}

				//send acknowledgement
				/*
				strcpy(stringBuffer, "acknowledged");
				if(send(sock, stringBuffer, STRINGBUFSIZE,0) != STRINGBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
				*/
				msgType = FILEACK;
				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				printf("Sent to server: %c\n", msgType);


				//receive file contents
				printf("Receiving => ");
				totalBytesRcvd = 0;
				while(totalBytesRcvd < fileSize){
					if(fileSize - totalBytesRcvd < FILEBUFSIZE){
						bytesToWrite = fileSize - totalBytesRcvd;
					}
					else{
						bytesToWrite = FILEBUFSIZE;
					}

					if((bytesRcvd = recv(sock, fileBuffer, FILEBUFSIZE, 0)) < 0)
						DieWithError("recv failed or connection closed prematurely");	
					
					//printf("received bytes:%d\n", bytesRcvd);

					printf("#");
					fwrite(fileBuffer, sizeof(char), bytesToWrite, fp);
					totalBytesRcvd += bytesRcvd; 

					//printf("Receiving => ##########\n");
					memset(fileBuffer, 0, FILEBUFSIZE);
				}			
				//open file and write
				fclose(fp);
				printf("\n");

				printf("%s (%d bytes) downloading succeeded from %s\n", fileName, fileSize, servIP);

				//send acknowledgement of successful file download
				/*
				memset(stringBuffer, 0, STRINGBUFSIZE);
				strcpy(stringBuffer, "acknowledged");
				if(send(sock, stringBuffer, STRINGBUFSIZE,0) != STRINGBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
				*/
			}
		
			else if(operation == 'l'){
				fp = popen("ls -l", "r");
				if(fp == NULL){
					DieWithError("popen failed");
				}
			
				while( fgets(fileBuffer, FILEBUFSIZE,fp ) != NULL){
					printf(" %s", fileBuffer);
				}
				pclose(fp);
			}
			else if(operation == 'r'){
				//send msgtype
				msgType = ListFilesReq;
				if(send(sock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
	
				//printf("Sent msgtype: %c\n", msgType);
	
				totalBytesRcvd = 0;
				while(totalBytesRcvd < FILEBUFSIZE){
					if((bytesRcvd = recv(sock, fileBuffer, FILEBUFSIZE, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	
					
					//printf("received bytes:%d\n", bytesRcvd);
					totalBytesRcvd += bytesRcvd;
				}
			
				//printf("total received bytes:%d, received contents:%s\n", totalBytesRcvd, fileBuffer);
				printf("Files on FT server: %s\n", fileBuffer);

				/*
				strcpy(stringBuffer, "acknowledged");
				totalBytesSent = 0;
				while(totalBytesSent < strlen(stringBuffer)){
					if((bytesSent = send(sock, stringBuffer, STRINGBUFSIZE, 0)) != STRINGBUFSIZE)
						DieWithError("send() sent a different number of bytes than expected");
			
					printf("bytes sent:%d\n", bytesSent);				
					totalBytesSent += bytesSent;
				}
				printf("Sent to server: %s\n", stringBuffer);
				*/
			}

			else if(operation == 'e'){
				mode = 1;
			}
			else{
				printf("Unrecognizable command. Please try again.\n");
			}
		}
	}
	printf("Sent msgtype: %c\n", msgType);

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
