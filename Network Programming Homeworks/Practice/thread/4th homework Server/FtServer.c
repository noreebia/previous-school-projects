#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

#define MAXPENDING 5

#define NUMOFCHATSOCKETS 10

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
void *HandleTCPClient(void *clientSocket);
void writeLog(char contents[]);
int fSize(char* file);

struct ClientInfo{
	char ip[20];
	unsigned short clientMainPort;
	unsigned short clientChatPort;
	int clientMainSocket;
	int clientChatSocket;
};

int chatSockets[NUMOFCHATSOCKETS] = {0};
int connectionCount = 0;

int main(int argc, char *argv[])
{
	int serverMainSocket;
	int clientMainSocket;
	struct sockaddr_in serverMainAddress;
	struct sockaddr_in clientMainAddress;
	unsigned short mainServerPort = 1080;
	unsigned int clientMainLength;

	int serverChatSocket;
	int clientChatSocket;
	struct sockaddr_in serverChatAddress;
	struct sockaddr_in clientChatAddress;
	unsigned short chatServPort = 1081;
	unsigned int clientChatLength;
	/*
	if(argc != 2){
		fprintf(stderr, "Usage: %s <Server Port>\n", argv[0]);
		exit(1);
	}
	*/

	if((serverMainSocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(serverMainSocket, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");


	memset(&serverMainAddress, 0, sizeof(serverMainAddress));
	serverMainAddress.sin_family = AF_INET;
	serverMainAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	serverMainAddress.sin_port = htons(mainServerPort);

	if(bind(serverMainSocket, (struct sockaddr *) &serverMainAddress, sizeof(serverMainAddress)) < 0)
		DieWithError("bind() failed");

	if(listen(serverMainSocket, MAXPENDING) < 0)
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

		clientMainLength = sizeof(clientMainAddress);
	
		if((clientMainSocket = accept(serverMainSocket, (struct sockaddr *) &clientMainAddress, &clientMainLength)) < 0)
			DieWithError("accept() failed");

		clientChatLength = sizeof(clientChatAddress);
	
		if((clientChatSocket = accept(serverChatSocket, (struct sockaddr *) &clientChatAddress, &clientChatLength)) < 0)
			DieWithError("accept() failed");


		chatSockets[connectionCount] = clientChatSocket;
		connectionCount++;

		struct ClientInfo *newClientInfo = (struct ClientInfo*) malloc(sizeof(struct ClientInfo));

		if(newClientInfo == NULL){
			DieWithError("ClientInfo struct creation failed");
		}

		strcpy(newClientInfo->ip, inet_ntoa(clientMainAddress.sin_addr));
		newClientInfo->clientMainPort = ntohs(clientMainAddress.sin_port);
		newClientInfo->clientChatPort = ntohs(clientChatAddress.sin_port);
		newClientInfo->clientMainSocket = clientMainSocket;
		newClientInfo->clientChatSocket = clientChatSocket;

		pthread_t threadID;
		//int newConnectionSocket = clientMainSocket;
		int threadCreation = pthread_create(&threadID, NULL, HandleTCPClient, newClientInfo);
		if(threadCreation){
			DieWithError("error creating thread");
		}
		else{
			printf("new thread created.");
		}

		printf("listening again...");
		//HandleTCPClient(clntSock);
	}	
}

void *HandleTCPClient(void *clientInfo){
	char clntIP[20];
	unsigned short clntMainPort = ((struct ClientInfo*)clientInfo) -> clientMainPort;
	unsigned short clntChatPort = ((struct ClientInfo*)clientInfo) -> clientChatPort;
	int clntSocket = ((struct ClientInfo*)clientInfo) -> clientMainSocket;
	int clntChatSocket = ((struct ClientInfo*)clientInfo) -> clientChatSocket;

	int fileSize;
	int bytesToWrite;
	int totalBytesSent, bytesSent;
	int bytesRcvd, totalBytesRcvd;
	int echoStringLength;
	char fileSizeInString[20];
	char fileName[256];
	char stringBuffer[BUFSIZE];
	char fileBuffer[FILEBUFSIZE];
	char msgType=0;
	char echoStringLengthInString[20];
	char logBuffer[1024];
	FILE *fp;

	strcpy(clntIP, ((struct ClientInfo*)clientInfo) -> ip);

	printf("Client IP : %s\n", clntIP);
	printf("Client Main Port : %d\n", clntMainPort);
	printf("Client Chat Port : %d\n", clntChatPort);

	sprintf(logBuffer, "New client has connected. Client IP : %s, Client Main Port : %d, Client Chat Port : %d", clntIP, clntMainPort, clntChatPort);
	writeLog(logBuffer);

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
		memset(logBuffer, 0, 1024);
		
		/* receive msgType from client */
		if((bytesRcvd = recv(clntSocket, &msgType, 1, 0)) <=0){
			DieWithError("recv() failed");
		}

		if(msgType == EchoReq){
			totalBytesRcvd = 0;

			/*
			msgType = EchoRep;
			if(send(clntSocket, &msgType, 1, 0) != 1)
				DieWithError("send() sent a different number of bytes than expected");
			*/

			if((bytesRcvd = recv(clntSocket, echoStringLengthInString, 20, 0)) <0)
				DieWithError("recv() failed");	
			
			echoStringLength = atoi(echoStringLengthInString);
			if(echoStringLength > BUFSIZE){	/* if string length exceeds buffer length */
				totalBytesRcvd = 0;				
				while(totalBytesRcvd < echoStringLength){
					if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE-1, 0)) <0)
						DieWithError("recv() failed");	
					printf("Msg<%s\n", stringBuffer);
					/*
					if( send(clntSocket, stringBuffer, bytesRcvd,0) != bytesRcvd){
						DieWithError("send() failed");
					}
					*/
					for(int i=0;i<NUMOFCHATSOCKETS;i++){
						if(chatSockets[i] != 0 && chatSockets[i] != clntChatSocket){
							if( send(chatSockets[i], stringBuffer, bytesRcvd,0) != bytesRcvd){
								DieWithError("send() failed");
							}				
						}
					}
					printf("Msg>%s\n", stringBuffer);
					totalBytesRcvd += bytesRcvd;

					sprintf(logBuffer, "Client has sent chat message:%s Client IP:%s, Client Chat Port:%hu", stringBuffer, clntIP, clntChatPort);
					writeLog(logBuffer);
					memset(stringBuffer, 0, BUFSIZE);
				}
			}
			else{	/* if string length does not exceed buffer length */
				if((bytesRcvd = recv(clntSocket, stringBuffer, BUFSIZE, 0)) <0)
					DieWithError("recv() failed");	
				printf("Msg<%s\n", stringBuffer);
				/*
				if( send(clntSocket, stringBuffer, bytesRcvd,0) != bytesRcvd){
					DieWithError("send() failed");
				}
				*/
				for(int i=0;i<NUMOFCHATSOCKETS;i++){
					if(chatSockets[i] != 0 && chatSockets[i] != clntChatSocket){
						if( send(chatSockets[i], stringBuffer, bytesRcvd,0) != bytesRcvd){
							DieWithError("send() failed");
						}				
					}
				}
				sprintf(logBuffer, "Client has sent chat message:%s Client IP:%s, Client Chat Port:%hu", stringBuffer, clntIP, clntChatPort);
				writeLog(logBuffer);
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
			sprintf(logBuffer, "Received file '%s' from main port of client. Client IP: %s, Client Main Port: %hu", fileName, clntIP, clntMainPort);
			writeLog(logBuffer);
		
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
			sprintf(logBuffer, "Sent file '%s' to main port of client. Client IP:%s, Client Main Port:%hu", fileName, clntIP, clntMainPort);
			writeLog(logBuffer);

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
			sprintf(logBuffer, "Sent list of files on server to main port of client. Client IP:%s, Client Main Port:%hu\n%s", clntIP, clntMainPort, fileBuffer);
			writeLog(logBuffer);

			printf("Sent list of files on directory to client:");
			printf(" %s", fileBuffer);

			printf("Waiting for operation from client...\n\n");
		}
	}
	printf("Client has disconnected.\nClosing socket.\n");
	for(int i=0; i<NUMOFCHATSOCKETS;i++){
		if(chatSockets[i] == clntChatSocket){
			printf("changed sockets back to 0\n");
			chatSockets[i] = 0;
			break;
		}
	}
	close(clntChatSocket);
	close(clntSocket);
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
