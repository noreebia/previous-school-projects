#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

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
	char fileName[] = "test.txt";
	int sizeOfFile;
	char fileSize[100];
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

	printf("\n");

	for(;;)
	{
		sleep(10000000);
	}
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
