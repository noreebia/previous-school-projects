#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

#define STRINGBUFSIZE 32
#define FILEBUFSIZE 1024

#define FileUploadReq 'p'
#define FileDownloadReq 'g'
#define FileDownloadError 'x'
#define FileDownloadReady 'y'
#define ListFilesReq 'r'
#define FILEACK 'a'
#define ChatReq 'c'
#define Exit 'q'

void DieWithError(char *errorMessage);
void *listenToSocket(void *chatSocket);
int fSize(char* file);

int shouldRun = 1;
int main(int argc, char *argv[]){
	unsigned short serverFTPPort = 1080;
	unsigned short serverChatPort = 1081;
	char servIP[20];

	struct sockaddr_in echoServAddr;
	struct sockaddr_in chatServAddr;

	unsigned int echoStringLen;
	int ftpSock;
	int chatSock;
	int fileSize;
	int bytesToWrite;
	int bytesRcvd, totalBytesRcvd;
	int bytesSent, totalBytesSent;
	char stringBuffer[STRINGBUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char fileName[256];
	char fileSizeInString[20];
	char command[20];
	char operation;
	char msgType;
	int mode=1; /* 1 is echo string mode, 2 is file transfer mode */
	int stringLength;
	char stringLengthInString[20];
	FILE *fp;

	/* receive ip server */
	printf("Server IP : ");
	scanf("%s", servIP);

	//ftp socket
	if((ftpSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(serverFTPPort);

	if(connect(ftpSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
		DieWithError("connect() failed");

	//chat socket
	if((chatSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&chatServAddr, 0, sizeof(chatServAddr));
	chatServAddr.sin_family = AF_INET;
	chatServAddr.sin_addr.s_addr = inet_addr(servIP);
	chatServAddr.sin_port = htons(serverChatPort);

	if(connect(chatSock, (struct sockaddr *) &chatServAddr, sizeof(chatServAddr))<0)
		DieWithError("connect() failed");

	/* send "hello "*/
	strcpy(stringBuffer, "hello");
	if(send(ftpSock, stringBuffer, STRINGBUFSIZE, 0) != STRINGBUFSIZE)
		DieWithError("send() sent a different number of bytes than expected");

	printf("Msg> %s\n", stringBuffer);	

	/* receive "hi" */
	memset(stringBuffer, 0, STRINGBUFSIZE);
	if((bytesRcvd = recv(ftpSock, stringBuffer, STRINGBUFSIZE, 0)) <0){
		DieWithError("recv() failed");
	}

	if(!strcmp(stringBuffer, "overcapacity")){
		printf("Server is busy.\n");
		close(ftpSock);
		close(chatSock);
		exit(0);
	}

	printf("Msg< %s\n\n", stringBuffer);	

	pthread_t threadID;

	int threadCreation = pthread_create(&threadID, NULL, listenToSocket, (void *)chatSock);

	while(1){
		/* chat mode */
		if(mode == 1){
			printf("Msg> ");
			/* receive string to send to server */
			scanf(" %s", stringBuffer);

			/* if "quit" is input, exit program*/
			if(strcmp(stringBuffer, "/quit") == 0){
				/* send message to server that this program will disconnect*/
				msgType = Exit;
				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				break;
			}
			/* if "FT" is input, switch to file transfer mode */
			else if(strcmp(stringBuffer, "FT") == 0){
				mode = 2;
				printf("Welcome to Socket FT client!\n");
			}
			/* send string */
			else{
				/* send msgType */
				msgType = ChatReq;
				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				stringLength = strlen(stringBuffer);
				sprintf(stringLengthInString, "%d", stringLength);

				if( send(ftpSock, stringLengthInString, 20,0) != 20){	
					DieWithError("recv() failed or connection closed prematurely");
				}			

				if( send(ftpSock, stringBuffer, stringLength,0) != stringLength){	
					DieWithError("recv() failed or connection closed prematurely");
				}
			}
		}
		/* file transfer mode */
		else if(mode == 2){
			/* reset all string variables */
			memset(fileName, 0, 256);
			memset(fileSizeInString, 0, 20);	
			memset(fileBuffer, 0, FILEBUFSIZE);	
			memset(command, 0, 20);

			printf("ftp command [p)ut g)et l)s r)ls e)xit ] -> ");
			scanf(" %s", command);
			if(strlen(command) != 1){
				/* user must input only one character */
				printf("Unrecognizable command. Please try again.\n");
				continue;
			}
			operation = command[0];

			/* file upload request */
			if(operation == 'p'){
				msgType = FileUploadReq;

				printf("Name of file to put on server:");
				scanf("%s", fileName);
	
				fileSize = fSize(fileName);
				sprintf(fileSizeInString, "%d", fileSize);

				fp = fopen(fileName, "rb");
				if(fp == NULL){
					printf("No such file exists. Try again.\n");
					continue;
				}

				/* send msgtype */
				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				
				/* send file name to upload*/
				if(send(ftpSock, fileName, 256, 0) != 256)
					DieWithError("send() sent a different number of bytes than expected");

				/* send file size */
				if(send(ftpSock, fileSizeInString, 20, 0) != 20)
					DieWithError("send() sent a different number of bytes than expected");

				printf("Sent file size to server: %s\n", fileSizeInString);

				/* receive ACK */
				if((bytesRcvd = recv(ftpSock, &msgType, 1, 0)) <0){
					DieWithError("recv() failed");
				}
		
				/* send file contents */
				printf("Sending => ");
				while( fread(fileBuffer, 1, FILEBUFSIZE, fp) > 0){
					if((bytesSent = send(ftpSock, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
						DieWithError("send() sent a different number of bytes than expected");
			
					printf("#");
					totalBytesSent += bytesSent;
					memset(fileBuffer, 0, FILEBUFSIZE);
				}
				fclose(fp);
				printf("\n");
			
				printf("%s (%d bytes) uploading succeeded to %s\n", fileName, fileSize, servIP);
			}
			/* file download request */
			else if(operation == 'g'){
				msgType = FileDownloadReq;

				printf("Name of file to get from server:");
				scanf("%s", fileName);

				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				
				/* send name of file to download */
				if(send(ftpSock, fileName, 256, 0) != 256)
					DieWithError("send() sent a different number of bytes than expected");

				if((bytesRcvd = recv(ftpSock, &msgType, 1, 0)) <0)
					DieWithError("recv() failed");	

				/* if file does not exist on server, display error message and try again */
				if(msgType == FileDownloadError){
					printf("File with that name does not exist on server. Please try again.\n");
					continue;
				}

				/* receive size of file */
				memset(fileSizeInString, 0, 20);
				totalBytesRcvd = 0;
				while(totalBytesRcvd < 20){
					if((bytesRcvd = recv(ftpSock, fileSizeInString, 20, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	

					totalBytesRcvd += bytesRcvd;
				}
			
				printf("Received file size from server: %s\n", fileSizeInString);
				fileSize = atoi(fileSizeInString);

				fp = fopen(fileName, "w");
				if(fp == NULL){
					DieWithError("fopen failed.");
				}

				/* send ACK */
				msgType = FILEACK;
				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");

				/* receive file contents */
				printf("Receiving => ");
				totalBytesRcvd = 0;
				while(totalBytesRcvd < fileSize){
					if(fileSize - totalBytesRcvd < FILEBUFSIZE){
						bytesToWrite = fileSize - totalBytesRcvd;
					}
					else{
						bytesToWrite = FILEBUFSIZE;
					}

					if((bytesRcvd = recv(ftpSock, fileBuffer, FILEBUFSIZE, 0)) < 0)
						DieWithError("recv failed or connection closed prematurely");	
					
					printf("#");
					fwrite(fileBuffer, sizeof(char), bytesToWrite, fp);
					totalBytesRcvd += bytesRcvd; 

					memset(fileBuffer, 0, FILEBUFSIZE);
				}			
				fclose(fp);
				printf("\n");

				printf("%s (%d bytes) downloading succeeded from %s\n", fileName, fileSize, servIP);
			}
			/* list files in current client directory */
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
			/* list files in server directory */
			else if(operation == 'r'){
				//send msgtype
				msgType = ListFilesReq;
				if(send(ftpSock, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
	
	
				totalBytesRcvd = 0;
				while(totalBytesRcvd < FILEBUFSIZE){
					if((bytesRcvd = recv(ftpSock, fileBuffer, FILEBUFSIZE, 0)) <= 0)
						DieWithError("recv failed or connection closed prematurely");	
					
					totalBytesRcvd += bytesRcvd;
				}
			
				printf("Files on FT server: %s\n", fileBuffer);
			}
			else if(operation == 'e'){
				/* switch to chat mode */
				mode = 1;
			}
			else{
				/* if command is unrecognizable, try again */
				printf("Unrecognizable command. Please try again.\n");
			}
		}
	}
	shouldRun = 0;
	int threadDestruction = pthread_join(threadID, NULL);
	if(threadDestruction == 0){
		printf("Thread joined successfully.\n");
	} else{
		DieWithError("Thread join failed\n");
	}
	printf("Exiting program.\n");
	close(ftpSock);
	close(chatSock);
	exit(0);
}

void *listenToSocket(void *chatSocket){
	int socket = (int) chatSocket;
	int receivedBytes;
	char chatBuffer[STRINGBUFSIZE];
	while(shouldRun){
		memset(chatBuffer, 0, STRINGBUFSIZE);
        recv(socket, chatBuffer, STRINGBUFSIZE -1, 0);
		chatBuffer[STRINGBUFSIZE] = '\0';
    	printf("msg<- %s\n", chatBuffer);	
	}
	printf("Exiting thread\n");
}

/* function that returns size of file */
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
