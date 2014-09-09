/**
 *
 * File for cropping
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "image.h"
#include "pixel.h"
#include "openFile.h"
#include "saveFile.h"


int main(int argc, char** argv)
{
	image* img;
	image* img2;
	int i, j;
	int x, y, w, h;
	if(argc != 7)
	{
		printf("Invalid number of arguments. Please try again\n");
		return 1;
	}
	
	x = atoi(argv[3]);
	y = atoi(argv[4]);
	w = atoi(argv[5]);
	h = atoi(argv[6]);

	while(x < 0)
	{
		printf("Please enter a valid x position:\n>");
		scanf("%d", &x);
	}
	while(y < 0)
	{
		printf("Please enter a valid y opsition:\n>");
		scanf("%d", &y);
	}
	
	img = open(argv[1]);
	
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

	save(img2, argv[2]);
	freeImg(img);
	freeImg(img2);
	return 0;
}

