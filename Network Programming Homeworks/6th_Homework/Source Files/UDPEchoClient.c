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
	
	/* Construct server address structure */
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr(servIP);
	serverAddress.sin_port = htons(echoServPort);

	/* Create UDP socket */
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

	printf("Commencing UDP echo chat.\n\n");

	while(1){
		printf("msg>");
		scanf("%s", sendBuffer);
		sendBufferLength = strlen(sendBuffer);

		if(!strcmp(sendBuffer, "/quit")){
			break;
		}

		if(sendto(sock, sendBuffer, sendBufferLength, 0, (struct sockaddr*)&serverAddress, sizeof(serverAddress)) < 0){
			DieWithError("sendto() failed");
		}

		/*
		sendto(sock, sendBuffer, sendBufferLength, 0, (struct sockaddr*)&serverAddress, sizeof(serverAddress));
		*/
	
		memset(recvBuffer, 0, BUFSIZE);
		addr_size = sizeof(from_addr);
		strLen = recvfrom(sock, recvBuffer, BUFSIZE, 0, (struct sockaddr*)&from_addr, &addr_size);
		if(strLen < 0){
			DieWithError("recvfrom() failed");
		}		
		recvBuffer[strLen] = 0;
		printf("msg<%s\n\n", recvBuffer);		
	}
	printf("Exiting program.\n");
	close(sock);
	exit(0);
}
