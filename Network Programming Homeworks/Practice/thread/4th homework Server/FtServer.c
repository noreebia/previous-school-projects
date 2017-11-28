#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

#define MAXPENDING 5

#define NUMOFCHATSOCKETS 2
#define BUFSIZE 32
#define FILEBUFSIZE 1024

#define FileUploadReq 'p'
#define FileDownloadReq 'g'
#define FileDownloadError 'x'
#define FileDownloadReady 'y'
#define ListFilesReq 'r'
#define FILEACK 'a'
#define ChatReq 'c'
#define ChatRep 'h'
#define Exit 'q'

void DieWithError(char *errorMessage);
void *HandleTCPClient(void *clientSocket);
void writeLog(char contents[]);
int fSize(char* file);

struct ClientInfo{
	char ip[20];
	unsigned short clientFTPPort;
	unsigned short clientChatPort;
	int clientFTPSocket;
	int clientChatSocket;
};

int chatSockets[NUMOFCHATSOCKETS] = {0};
int connectionCount = 0;

int main(int argc, char *argv[])
{
	int serverFTPSocket;
	int clientFTPSocket;
	struct sockaddr_in serverFTPAddress;
	struct sockaddr_in clientFTPAddress;
	unsigned short mainServerPort = 1080;
	unsigned int clientFTPLength;

	int serverChatSocket;
	int clientChatSocket;
	struct sockaddr_in serverChatAddress;
	struct sockaddr_in clientChatAddress;
	unsigned short chatServPort = 1081;
	unsigned int clientChatLength;


	if((serverFTPSocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(serverFTPSocket, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");


	memset(&serverFTPAddress, 0, sizeof(serverFTPAddress));
	serverFTPAddress.sin_family = AF_INET;
	serverFTPAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	serverFTPAddress.sin_port = htons(mainServerPort);

	if(bind(serverFTPSocket, (struct sockaddr *) &serverFTPAddress, sizeof(serverFTPAddress)) < 0)
		DieWithError("bind() failed");

	if(listen(serverFTPSocket, MAXPENDING) < 0)
		DieWithError("listen() failed");

	//chat socket
	if((serverChatSocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(serverChatSocket, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");

	memset(&serverChatAddress, 0, sizeof(serverChatAddress));
	serverChatAddress.sin_family = AF_INET;
	serverChatAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	serverChatAddress.sin_port = htons(chatServPort);

	if(bind(serverChatSocket, (struct sockaddr *) &serverChatAddress, sizeof(serverChatAddress)) < 0)
		DieWithError("bind() failed");

	if(listen(serverChatSocket, MAXPENDING) < 0)
		DieWithError("listen() failed");

	while(1){
		printf("Listening for clients...\n");

		clientFTPLength = sizeof(clientFTPAddress);
	
		if((clientFTPSocket = accept(serverFTPSocket, (struct sockaddr *) &clientFTPAddress, &clientFTPLength)) < 0)
			DieWithError("accept() failed");

		clientChatLength = sizeof(clientChatAddress);
	
		if((clientChatSocket = accept(serverChatSocket, (struct sockaddr *) &clientChatAddress, &clientChatLength)) < 0)
			DieWithError("accept() failed");


		if(connectionCount >= (NUMOFCHATSOCKETS)){
			if(send(clientFTPSocket, "overcapacity", strlen("overcapacity"), 0) != strlen("overcapacity"))
				DieWithError("send() failed");
		}else{
			chatSockets[connectionCount] = clientChatSocket;
			connectionCount++;

			struct ClientInfo *newClientInfo = (struct ClientInfo*) malloc(sizeof(struct ClientInfo));

			if(newClientInfo == NULL){
				DieWithError("ClientInfo struct creation failed");
			}

			strcpy(newClientInfo->ip, inet_ntoa(clientFTPAddress.sin_addr));
			newClientInfo->clientFTPPort = ntohs(clientFTPAddress.sin_port);
			newClientInfo->clientChatPort = ntohs(clientChatAddress.sin_port);
			newClientInfo->clientFTPSocket = clientFTPSocket;
			newClientInfo->clientChatSocket = clientChatSocket;

			pthread_t threadID;
			int threadCreation = pthread_create(&threadID, NULL, HandleTCPClient, newClientInfo);
			if(threadCreation){
				DieWithError("error creating thread");
			}
			else{
				printf("new thread created.");
			}
			printf("listening again...");
		}
	}	
}

void *HandleTCPClient(void *clientInfo){
	char clntIP[20];
	unsigned short clntFTPPort = ((struct ClientInfo*)clientInfo) -> clientFTPPort;
	unsigned short clntChatPort = ((struct ClientInfo*)clientInfo) -> clientChatPort;
	int clntFTPSocket = ((struct ClientInfo*)clientInfo) -> clientFTPSocket;
	int clntChatSocket = ((struct ClientInfo*)clientInfo) -> clientChatSocket;

	int fileSize;
	int bytesToWrite;
	int totalBytesSent, bytesSent;
	int bytesRcvd, totalBytesRcvd;
	int chatStringLength;
	char fileSizeInString[20];
	char fileName[256];
	char stringBuffer[BUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char msgType=0;
	char chatStringLengthInString[20];
	char logBuffer[1024];
	FILE *fp;

	strcpy(clntIP, ((struct ClientInfo*)clientInfo) -> ip);

	printf("New client has connected.\n");
	printf("Client IP : %s\n", clntIP);
	printf("Client FTP Port : %d\n", clntFTPPort);
	printf("Client Chat Port : %d\n", clntChatPort);

	sprintf(logBuffer, "New client has connected. Client IP : %s, Client FTP Port : %d, Client Chat Port : %d", clntIP, clntFTPPort, clntChatPort);
	writeLog(logBuffer);

	/* receive "hello" */
	memset(stringBuffer, 0, BUFSIZE);
	if(recv(clntFTPSocket, stringBuffer, BUFSIZE, 0) <0){
		DieWithError("recv() failed");
	}

	printf("Msg< %s\n", stringBuffer);

	/* send "hi" */
	memset(stringBuffer, 0, BUFSIZE);
	strcpy(stringBuffer, "hi");
	if(send(clntFTPSocket, stringBuffer, BUFSIZE, 0) != BUFSIZE)
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("Msg> %s\n\n", stringBuffer);

	while(msgType != Exit){
		/* reset string variables */
		memset(fileSizeInString, 0, 20);
		memset(fileName, 0, 256);
		memset(fileBuffer, 0, FILEBUFSIZE);
		memset(stringBuffer, 0, BUFSIZE);
		memset(chatStringLengthInString, 0, 20);
		memset(logBuffer, 0, 1024);
		
		/* receive msgType from client */
		if((bytesRcvd = recv(clntFTPSocket, &msgType, 1, 0)) <=0){
			DieWithError("recv() failed");
		}

		if(msgType == ChatReq){
			totalBytesRcvd = 0;

			if((bytesRcvd = recv(clntFTPSocket, chatStringLengthInString, 20, 0)) <0)
				DieWithError("recv() failed");	
			
			chatStringLength = atoi(chatStringLengthInString);
			if(chatStringLength > BUFSIZE){	/* if string length exceeds buffer length */
				totalBytesRcvd = 0;				
				while(totalBytesRcvd < chatStringLength){
					if((bytesRcvd = recv(clntFTPSocket, stringBuffer, BUFSIZE-1, 0)) <0)
						DieWithError("recv() failed");	

					for(int i=0;i<NUMOFCHATSOCKETS;i++){
						if(chatSockets[i] != 0 && chatSockets[i] != clntChatSocket){
							if( send(chatSockets[i], stringBuffer, bytesRcvd,0) != bytesRcvd){
								DieWithError("send() failed");
							}				
						}
					}
					totalBytesRcvd += bytesRcvd;
					sprintf(logBuffer, "Client has sent chat message '%s' from chat port. Delivering to other clients. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu", stringBuffer, clntIP, clntFTPPort, clntChatPort);
					writeLog(logBuffer);
					printf("%s\n",logBuffer);
					memset(stringBuffer, 0, BUFSIZE);
				}
			}
			else{	/* if string length does not exceed buffer length */
				if((bytesRcvd = recv(clntFTPSocket, stringBuffer, BUFSIZE, 0)) <0)
					DieWithError("recv() failed");	

				for(int i=0;i<NUMOFCHATSOCKETS;i++){
					if(chatSockets[i] != 0 && chatSockets[i] != clntChatSocket){
						if( send(chatSockets[i], stringBuffer, bytesRcvd,0) != bytesRcvd){
							DieWithError("send() failed");
						}				
					}
				}
				sprintf(logBuffer, "Client has sent chat message '%s' from chat port. Delivering to other clients. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu", stringBuffer, clntIP, clntFTPPort, clntChatPort);
				writeLog(logBuffer);
				printf("%s\n",logBuffer);
			}
		}
		
		else if(msgType == FileUploadReq){
			/* receive name of file clients wants to upload */
			if((bytesRcvd = recv(clntFTPSocket, fileName, 256, 0)) <0)
				DieWithError("recv() failed");	

			printf("File name client will upload: %s\n", fileName);


			/* receive size of file */ 
			if((bytesRcvd = recv(clntFTPSocket, fileSizeInString, 20, 0)) <0)
			{
				DieWithError("recv() failed");
			}
			fileSize = atoi(fileSizeInString);
			printf("File size that client will send: %d\n", fileSize);	

			/* send ACK */
			msgType = FILEACK;
			if(send(clntFTPSocket, &msgType, 1, 0) != 1)
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
				if((bytesRcvd = recv(clntFTPSocket, fileBuffer, FILEBUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				
				printf("#");
				fwrite(fileBuffer, sizeof(char), bytesToWrite, fp);/* write to file */
				totalBytesRcvd += bytesRcvd;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}
			fclose(fp);
			printf("\n");		
			printf("%s (%d bytes) successfully received from client\n", fileName, fileSize);

			sprintf(logBuffer, "Received file '%s' from FTP port of client. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu", fileName, clntIP, clntFTPPort, clntChatPort);
			writeLog(logBuffer);

			printf("Waiting for operation from client...\n\n");
		}
		else if(msgType == FileDownloadReq){

			/* receive name of file client wants to download */
			if((bytesRcvd = recv(clntFTPSocket, fileName, 256, 0)) <0)
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
				if(send(clntFTPSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
				continue;
			}
			else{
				msgType = FileDownloadReady;
				if(send(clntFTPSocket, &msgType, 1, 0) != 1)
					DieWithError("send() sent a different number of bytes than expected");
			}

			/* send size of file client wants to download */
			if(send(clntFTPSocket, fileSizeInString, 20, 0) != 20)
				DieWithError("send() sent a different number of bytes than expected");

			printf("Sent size of file to client: %s\n", fileSizeInString);

			/* receive ACK from client */
			if((bytesRcvd = recv(clntFTPSocket, &msgType, 1, 0)) <0){
				DieWithError("recv() failed");
			}

			/* send file contents */
			printf("Sending => ");
			while( (fread(fileBuffer, 1, FILEBUFSIZE, fp)) > 0){
				if((bytesSent = send(clntFTPSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				printf("#");
				totalBytesSent += bytesSent;
				memset(fileBuffer, 0, FILEBUFSIZE);
			}			
			fclose(fp);
			printf("\n");
			printf("%s (%d bytes) successfully sent to client\n", fileName, fileSize);

			sprintf(logBuffer, "Sent file '%s' to FTP port of client. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu", fileName, clntIP, clntFTPPort, clntChatPort);
			writeLog(logBuffer);

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
				if((bytesSent = send(clntFTPSocket, fileBuffer, FILEBUFSIZE, 0)) != FILEBUFSIZE)
					DieWithError("send() sent a different number of bytes than expected");
			
				totalBytesSent += bytesSent;
			}
			sprintf(logBuffer, "Sent list of files on server to FTP port of client. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu\n%s", clntIP, clntFTPPort, clntChatPort, fileBuffer);
			writeLog(logBuffer);

			printf("Sent list of files on directory to client:");
			printf(" %s", fileBuffer);

			printf("Waiting for operation from client...\n\n");
		}
	}
	sprintf(logBuffer, "Client has disconnected. Client IP:%s, Client FTP Port:%hu, Client Chat Port:%hu\n", clntIP, clntFTPPort, clntChatPort);
	writeLog(logBuffer);
	printf("%s\n",logBuffer);
	for(int i=0; i<NUMOFCHATSOCKETS;i++){
		if(chatSockets[i] == clntChatSocket){
			printf("changed sockets back to 0\n");
			chatSockets[i] = 0;
			break;
		}
	}
	printf("Closing Socket.\n");
	close(clntChatSocket);
	close(clntFTPSocket);
}

void writeLog(char contents[]){
	FILE* file = fopen("log.txt", "a");
	if(file != NULL){
		fprintf(file, "%s\n", contents);
		fclose(file);
	} else{
		DieWithError("Error opening log file");
	}
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
