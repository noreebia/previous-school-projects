#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define RCVBUFSIZE 32

void DieWithError(char *errorMessage);

void HandleTCPClient(int clntSocket){
	char echoBuffer[RCVBUFSIZE];
	char *connectionInitString = "hi";
	int recvMsgSize;

	/* Clear the echoBuffer */
	memset(echoBuffer, 0, RCVBUFSIZE);
	
	/* Receive string from client */
	if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
		DieWithError("recv() failed");
	
	/*Display received string*/
	printf("Received from client: %s\n",echoBuffer);

	/* If client sent "hello", reply with "hi" */
	if( strcmp(echoBuffer, "hello") == 0){
		if(send(clntSocket, connectionInitString, strlen(connectionInitString), 0) < 0){
			DieWithError("send() failed");
		}
		printf("Sent to client: %s\n", connectionInitString);
	}	
	
	printf("Commencing echo chat.\n\n");

	/* Clear the buffer that stores the string that will be received and sent back */
	memset(echoBuffer, 0, RCVBUFSIZE);
	
	while( recvMsgSize > 0){
		/* Receive from client */
		if((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) <0)
			DieWithError("recv() failed");	
		printf("Received from client: %s\n", echoBuffer);

		/* Sent to client the same exact string*/
		if(send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
			DieWithError("send() failed");
		printf("Sent to client: %s\n\n", echoBuffer);

		/* Clear the buffer */
		memset(echoBuffer, 0, RCVBUFSIZE);
	}
	printf("Closing socket.\n");
	close(clntSocket);
}
