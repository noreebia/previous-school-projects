#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#define BUFSZ 1024

int main(){
    char buf[BUFSZ];

    FILE *fp;
    errno = 0;
    fp = popen("ls","r");
    if( errno != 0 || fp == NULL ) {
        if(fp == NULL) {
             printf("ERROR: failed to popen ls\n");
        }
        printf("ERROR: %s\n", strerror(errno));
        exit(-1);
    } else {
        printf("Success!\n");
    }

    while(fgets(buf, BUFSZ, fp) != NULL) {
            printf("  %s", buf);
	}

	pclose(fp);
	
	exit(0);
}
