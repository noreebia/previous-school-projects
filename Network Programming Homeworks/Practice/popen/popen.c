#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

int main(){
	FILE *fp;
	char fileBuffer[100];
	
	printf("hello");

	
	printf("gonna open file");

	fp = popen("ls", "r");
	
	printf("file opened");
	
	if(fp == NULL){
		printf("file does not exist\n");
	}
	
	printf("file opened");
	/*
	while (fgets(fileBuffer, 100, fp) != NULL){
    	printf("   %s", fileBuffer);
	}	
	*/
	fread(fileBuffer, 100, 1, fp);
	pclose(fp);
	printf("%s", fileBuffer);
	exit(0);
}
