/**
 *
 *
 *
 * colorshifter
 *
 *
 */

#include <stdio.h>
#include <string.h>
#include "pixel.h"
#include "image.h"
#include "openFile.h"
#include "saveFile.h"

int main(int argc, char** argv)
{
	image* img;
	image* img2;
	int i = 0;
	int j;
	int opt;
	char* key = argv[3];

	if(argc != 4)
	{
		printf("Invalid number of arguments. Please try again\n");
		return 1;
	}

	

	img = open(argv[1]);
	img2 = newImg(img->width, img->height);


	if(strcmp(key, "RGB") == 0 || strcmp(key, "GBR") == 0 || strcmp(key, "BRG") == 0)
	{
		opt = 1;
	}
	else if(strcmp(key, "RBG") == 0 || strcmp(key, "BGR") == 0 || strcmp(key, "GRB") == 0)
	{
		opt = 2;
	}
	else if(strcmp(key, "RG") == 0 || strcmp(key, "GR") == 0)
	{
		opt = 3;
	}
	else if(strcmp(key, "BR") == 0 || strcmp(key, "RB") == 0)
	{
		opt = 4;
	}
	else if(strcmp(key, "GB") == 0 || strcmp(key, "BG") == 0)
	{
		opt = 5;
	}
	else
	{
		printf("Incorrect pattern. Please try again.\n");
		return 1;
		/* TODO can I just do this??? */
	}

	switch(opt)
	{
		case 1:
			for(i = 0; i < img2->height; i++)
			{
				for(j = 0; j < img2->width; j++)
				{
					img2->pix[i][j].red = img->pix[i][j].blue;
					img2->pix[i][j].green = img->pix[i][j].red;
					img2->pix[i][j].blue = img->pix[i][j].green;			
					img2->pix[i][j].alpha = img->pix[i][j].alpha;
				}
			}
			break;

		case 2:
			for(i = 0; i < img2->height; i++)
			{
				for(j = 0; j < img2->width; j++)
				{
					img2->pix[i][j].red = img->pix[i][j].green;
					img2->pix[i][j].green = img->pix[i][j].blue;
					img2->pix[i][j].blue = img->pix[i][j].red;
					img2->pix[i][j].alpha = img->pix[i][j].alpha;
				}
			}
			break;

		case 3:
			for(i = 0; i < img2->height; i++)
			{
				for(j = 0; j < img2->width; j++)
				{
					img2->pix[i][j].red = img->pix[i][j].green;
					img2->pix[i][j].green = img->pix[i][j].red;
					img2->pix[i][j].blue = img->pix[i][j].blue;
					img2->pix[i][j].alpha = img->pix[i][j].alpha;
				}
			}
			break;

		case 4:
			for(i = 0; i < img2->height; i++)
			{
				for(j = 0; j < img2->width; j++)
				{
					img2->pix[i][j].red = img->pix[i][j].blue;
					img2->pix[i][j].green = img->pix[i][j].green;
					img2->pix[i][j].blue = img->pix[i][j].red;
					img2->pix[i][j].alpha = img->pix[i][j].alpha;
				}
			}

			break;

		case 5:
			for(i = 0; i < img2->height; i++)
			{
				for(j = 0; j < img2->width; j++)
				{
					img2->pix[i][j].red = img->pix[i][j].red;
					img2->pix[i][j].green = img->pix[i][j].blue;
					img2->pix[i][j].blue = img->pix[i][j].green;
					img2->pix[i][j].alpha = img->pix[i][j].alpha;
				}
			}	
			break;

	}

	save(img2, argv[2]);
	freeImg(img);
	freeImg(img2);

	return 0;
}

