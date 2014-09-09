/**
 *
 * File to make image black and white
 *
 *
 * need to use round!!!!!!!!
 */


#include <stdio.h>
#include "pixel.h"
#include "image.h"
#include "openFile.h"
#include "saveFile.h"

int main(int argc, char** argv)
{
	image* img;
	int i;
	int j;
	int avg;

	if(argc != 3)
	{
		printf("Invalid number of arguments. Please try again\n");
		return 1;
	}

	img  = open(argv[1]);

	for(i = 0; i < img->height; i++)
	{
		for(j = 0; j < img->width; j++)
		{
			unsigned char r, g, b;
			r = img->pix[i][j].red;
			g = img->pix[i][j].green;
			b = img->pix[i][j].blue;
			
			avg = (r + b + g) / 3;
			
			img->pix[i][j].red = avg;
			img->pix[i][j].green = avg;
			img->pix[i][j].blue = avg;
		
		}
	}

	save(img, argv[2]);
	freeImg(img);
	return 0;
}
