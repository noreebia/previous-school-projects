#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define RCVBUFSIZE 32
#define FILEBUFSIZE 1024

int fsize(char* file);

int main(int argc, char *argv[]){
	char fileBuffer[FILEBUFSIZE];
	char fileName[] = "test.txt";
	int fileSize = fsize("test.txt");	
	FILE *fp = fopen(fileName, "r");
	
	fread(fileBuffer, fileSize, 1, fp);
	printf("%s", fileBuffer);
	fclose(fp);
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
