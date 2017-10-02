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
	int bytesRcvd, totalBytesRcvd, totalBytesSent;
	char fileName[] = "test.txt";
	char fileSize[100];
	char fileBuffer[FILEBUFSIZE];
	FILE *fp;
	/*
	if((argc < 3) || (argc > 4)){
		fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Port>]\n", argv[0]);
		exit(1);
	}
	servIP = argv[1];
	echoString = argv[2];

	if(argc==4)
		serverPort = atoi(argv[3]);
	else
		serverPort = 7;
	*/

	//serverPort = atoi(argv[2]);

	if((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	printf("hie");	
	
	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(serverPort);

	if(connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
		DieWithError("connect() failed");
	/*
	echoStringLen = strlen(echoString);
	*/
	/*
	if(send(sock, echoString, echoStringLen, 0) != echoStringLen)
		DieWithError("send() sent a different number of bytes than expected");
	*/
	printf("hie");

	sprintf(fileSize, "%d", fSize(fileName));
	if(send(sock, fileSize, strlen(fileSize), 0) != strlen(fileSize))
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("sent filename");

	if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
		DieWithError("recv failed or connection closed prematurely");

	printf("server sends: %s", echoBuffer);

	if(send(sock, fileName, strlen(fileName), 0) != strlen(fileName))
		DieWithError("send() sent a different number of bytes than expected");

	printf("sent filename");

	fp = fopen(fileName,"r");
   	fread(fileBuffer, fSize(fileName), 1, fp);
	fclose(fp);
		
	if(send(sock, fileBuffer, fSize(fileName), 0) != fSize(fileName))
		DieWithError("send() sent a different number of bytes than expected");

	/*
	totalBytesRcvd = 0;
	printf("Received: ");
	while(totalBytesRcvd < echoStringLen){
		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE - 1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");
		totalBytesRcvd += bytesRcvd;
		echoBuffer[bytesRcvd] = '\0';
		printf(echoBuffer);		
	}
	*/
	printf("\n");
	close(sock);
	exit(0);
}

int fSize(char* file) {
  int size;
  FILE* fh;

  fh = fopen(file, "rb"); //binary mode
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
