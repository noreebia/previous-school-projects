#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 

int fsize(char* file);

int main(int argc, char *argv[]){
	printf("%d", fsize("test.txt"));	
	exit(0);
}

int fsize(char* file) {
  int size;
  FILE* fh;

  fh = fopen(file, "r"); //
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
