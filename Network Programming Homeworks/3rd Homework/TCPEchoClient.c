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
	unsigned short echoServPort;
	char servIP[20];
	char echoString[RCVBUFSIZE];
	char echoBuffer[RCVBUFSIZE];
	unsigned int echoStringLen;
	int bytesRcvd, totalBytesRcvd;

	/* Display IP and port of server */
	//printf("Server ip: %s\n", inet_ntoa(echoServAddr.sin_addr));
	//printf("Server ip: %s\n", servIP);
	//printf("Port: %hu\n", htons(echoServPort));

	printf("Server IP: ");
	scanf("%s", servIP);
	printf("Port: ");
	scanf("%hu", &echoServPort);

	if((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = inet_addr(servIP);
	echoServAddr.sin_port = htons(echoServPort);
	
	/* Connect to server */
	if(connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr))<0)
	{
		DieWithError("connect() failed");
	}

	/* Set value of echoString to "hello" */
	strcpy(echoString, "hello");
	echoStringLen = strlen(echoString);

	/* Send "hello" to server */
	if(send(sock, echoString, echoStringLen, 0) != echoStringLen)
		DieWithError("send() sent a different number of bytes than expected");
	
	printf("Sent to server: %s\n", echoString);

	/* Receive "hi" from server */
	totalBytesRcvd = 0;
	printf("Received from server: ");
	while(totalBytesRcvd < strlen("hi")){
		if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE -1, 0)) <= 0)
			DieWithError("recv failed or connection closed prematurely");
		totalBytesRcvd += bytesRcvd;
		echoBuffer[bytesRcvd] = '\0';
		printf("%s\n", echoBuffer);		
	}
	
	printf("Commencing echo chat.\n");

	while(strcmp(echoString, "/quit") != 0){
		printf("\nSend to server: ");
		/* Read string */
		scanf("%s", echoString);
		echoStringLen = strlen(echoString);

		/* Send inputted string to server */
		if( send(sock, echoString, echoStringLen,0) != echoStringLen){	
			DieWithError("recv() failed or connection closed prematurely");
		}
		
		/* Clear the buffer that stores the string that will be sent to the server */
		memset(echoBuffer, 0, RCVBUFSIZE);

		/* Receive echoed string from server */
		totalBytesRcvd = 0;
		while(totalBytesRcvd < echoStringLen){
        	if((bytesRcvd = recv(sock, echoBuffer, RCVBUFSIZE -1, 0)) <= 0){
				DieWithError("recv failed or connection closed prematurely");
			}			
        	totalBytesRcvd += bytesRcvd;
			echoBuffer[bytesRcvd] = '\0';
       		printf("Received from server: %s\n", echoBuffer);
		}
	}
	/* Exit the program if "/quit" is entered as input */
	printf("\nClosing socket.\nExiting program.\n");
	close(sock);
	exit(0);
}
