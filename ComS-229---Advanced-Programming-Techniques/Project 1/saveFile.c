
#include <stdio.h>
#include <stdlib.h>
#include "pixel.h"
#include "image.h"
#include "saveFile.h"


int save(image* img, char* file)
{

	int i, j;
	FILE* out = fopen(file, "wb");

	fwrite(&(img->width), sizeof(int), 1, out);
	fwrite(&(img->height), sizeof(int), 1, out);

	for(i = 0; i < img->height; i++)
	{
		for(j = 0; j < img->width; j++)
		{
			fputc(img->pix[i][j].red, out);
			fputc(img->pix[i][j].green, out);
			fputc(img->pix[i][j].blue, out);
			fputc(img->pix[i][j].alpha, out);
		}
	}

	fclose(out);

	return 0;
}
