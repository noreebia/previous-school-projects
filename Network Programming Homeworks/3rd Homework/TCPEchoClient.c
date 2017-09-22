#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 32

void DieWithError(char *errorMessage);

int main(int argc, char *argv[]){
	int sock;
	struct sockaddr_in echoServAddr;
	unsigned short echoServPort = 7;
	char *servIP = "127.0.0.1";
	char echoString[RCVBUFSIZE];
	char echoBuffer[RCVBUFSIZE];
	unsigned int echoStringLen;
	int bytesRcvd, totalBytesRcvd;

	if((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(echoServPort);

	printf("server ip: %s\n", servIP);
	printf("port: %hu\n", htons(echoServPort));
	
	if(connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
	{
		DieWithError("connect() failed");
	}
	else{
		printf("connected to server.\n");
	}

	//echoString = "hello";
	strcpy(echoString, "hello");
	echoStringLen = strlen(echoString);
	printf("Sent to server: %s\n", echoString);

	if(send(sock, echoString, echoStringLen, 0) != echoStringLen)
		DieWithError("send() sent a different number of bytes than expected");

	totalBytesRcvd = 0;
	printf("Received: ");
	while(totalBytesRcvd < strlen("hi")){
		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE -1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");
		totalBytesRcvd += bytesRcvd;
		echoBuffer[bytesRcvd] = '\0';
		printf("%s\n", echoBuffer);		
	}
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
	while(strcmp(echoString, "/quit") != 0){
		printf("Send to server: ");
		scanf("%s", echoString);
		printf("typed in %s\n", echoString);		

		echoStringLen = strlen(echoString);
		if( send(sock, echoString, echoStringLen,0) != echoStringLen){	
			DieWithError("recv() failed or connection closed prematurely");
		}
		/*
		totalBytesRcvd = 0;
	    printf("Received: ");
		while(totalBytesRcvd < echoStringLen){
        if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE -1, 0)) <= 0)
            DieWithError("recv failed or connection closed prematurely");
		}
        totalBytesRcvd += bytesRcvd;
        echoBuffer[bytesRcvd] = '\0';
        printf(echoBuffer);
		*/
		memset(echoBuffer, 0, RCVBUFSIZE);
		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE -1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");
		printf("Received from server:%s\n",echoBuffer);
	}
	printf("\n");
	close(sock);
	exit(0);
}
