#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

int a = 1;
void *ThreadMain();
int main(void){

	pthread_t threadID;		
	int returnValue = pthread_create(&threadID, NULL, ThreadMain, NULL);

	if(pthread_join(threadID, NULL) == 0){
		printf("thread has exited safely");
	}
}

void *ThreadMain(){
	printf("%d", a);
}
