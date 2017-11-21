#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

#define MAXPENDING 5

void DieWithError(char *errorMessage);
void HandleTCPClient(int clntSocket);
void *ThreadMain(void * threadArgs);

struct ThreadArgs{
	int clntSock;
};

int main(void){
	int servSock;
	int clntSock;
	struct sockaddr_in echoServAddr;
	struct sockaddr_in echoClntAddr;
	unsigned short echoServPort = 1080;
	unsigned int clntLen;

	if((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
		DieWithError("socket() failed");

	if (setsockopt(servSock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
	    DieWithError("setsockopt(SO_REUSEADDR) failed");

	memset(&echoServAddr, 0, sizeof(echoServAddr));
	echoServAddr.sin_family = AF_INET;
	echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServAddr.sin_port = htons(echoServPort);

	if(bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
		DieWithError("bind() failed");

	if(listen(servSock, MAXPENDING) < 0)
		DieWithError("listen() failed");

	while(1){
		clntLen = sizeof(echoClntAddr);
	
		if((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
			DieWithError("accept() failed");

		printf("Client IP : %s\n", inet_ntoa(echoClntAddr.sin_addr));
		printf("Port : %hu\n", ntohs(echoClntAddr.sin_port));

		struct ThreadArgs *threadArgs = (struct ThreadArgs*) malloc(sizeof(struct ThreadArgs));

		if(threadArgs == NULL){
			DieWithError("malloc() failed");
		}

		threadArgs->clntSock = clntSock;
		pthread_t threadID;
		
		int returnValue = pthread_create(&threadID, NULL, ThreadMain, threadArgs);

		if(returnValue!=0){
			DieWithError("pthread_create() failed");
		printf("with thread %ld\n", (long int)threadID);
		}

		//HandleTCPClient(clntSock);
		printf("Listening again.\n\n");
	}
}

void *ThreadMain(void *threadArgs){
	pthread_detach(pthread_self());
	int clntSock = ((struct ThreadArgs*)threadArgs) -> clntSock;
	free(threadArgs);

	HandleTCPClient(clntSock);
	pthread_exit(NULL);
}
