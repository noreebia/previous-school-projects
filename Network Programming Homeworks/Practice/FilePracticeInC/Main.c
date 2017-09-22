#include <stdio.h>

int main(){
	FILE *out = fopen("filename", "a");
	fprintf(out, "%s", "aString");
	fclose(out);
	return 0;
}
