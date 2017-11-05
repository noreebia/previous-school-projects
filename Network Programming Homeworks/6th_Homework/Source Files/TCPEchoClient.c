#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFSIZE 32

void DieWithError(char *errorMessage);

int main(int argc, char *argv[]){
	int sock;
	struct sockaddr_in serverAddress;
	struct sockaddr_in from_addr;
	unsigned short echoServPort;
	char servIP[20];
	char sendBuffer[BUFSIZE];
	char recvBuffer[BUFSIZE];
	int sendBufferLength;
	int recvBufferLength;
	unsigned int bufferLength;
	int addr_size;
	int strLen;

	/* Get IP and port of server through input */
	printf("Server IP: ");
	scanf("%s", servIP);
	printf("Port: ");
	scanf("%hu", &echoServPort);
	
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr(servIP);
	serverAddress.sin_port = htons(echoServPort);

	sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	if(sock < 0){
		DieWithError("socket() failed");
	}

	/*
	strcpy(sendBuffer, "hello");
	sendBufferLength = strlen(sendBuffer);

	sendto(sock, sendBuffer, sendBufferLength, 0, (struct sockaddr*)&serverAddress, sizeof(serverAddress));
	printf("msg>%s\n", sendBuffer);

	memset(recvBuffer, 0, BUFSIZE);
	strLen = recvfrom(sock, recvBuffer, BUFSIZE, 0, (struct sockaddr*)&from_addr, &addr_size);
	recvBuffer[strLen] = 0;
	printf("msg<%s\n", recvBuffer);	
	*/

	printf("Commencing echo chat.\n");

	while(1){
		printf("\nmsg>");
		scanf("%s", sendBuffer);
		sendBufferLength = strlen(sendBuffer);

		if(!strcmp(sendBuffer, "/quit")){
			break;
		}

		sendto(sock, sendBuffer, sendBufferLength, 0, (struct sockaddr*)&serverAddress, sizeof(serverAddress));
				
		memset(recvBuffer, 0, BUFSIZE);
		strLen = recvfrom(sock, recvBuffer, BUFSIZE, 0, (struct sockaddr*)&from_addr, &addr_size);
		recvBuffer[strLen] = 0;
		printf("msg<%s", recvBuffer);		
	}

	printf("Exiting program.\n");
	close(sock);
	exit(0);
}
