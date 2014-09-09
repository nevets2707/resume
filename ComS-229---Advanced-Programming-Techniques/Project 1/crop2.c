#include <stdio.h>
#include <stdlib.h>
#include "image.h"
#include "pixel.h"
#include "openFile.h"
#include "saveFile.h"
#include "crop2.h"

image* crop(char* file, int x, int y, int w, int h)
{
	

	int i, j;
	image* img;
	image* img2;
	
	img = open(file);
	
	if(w + x > img->width)
	{
		w = img->width - x - 1;
	}
	if(h + y > img->height)
	{
		h = img->height - y - 1;
	}
	
	img2 = newImg(w, h);

	for(i = 0; i < img2->height; i++)
	{
		for(j = 0; j < img2->width; j++)
		{
			img2->pix[i][j].red = img->pix[i + y][j + x].red;
			img2->pix[i][j].green = img->pix[i + y][j + x].green;
			img2->pix[i][j].blue = img->pix[i + y][j + x].blue;
			img2->pix[i][j].alpha = img->pix[i + y][j + x].alpha;
		}
	}
	
	free(img);
	return img2;
}
