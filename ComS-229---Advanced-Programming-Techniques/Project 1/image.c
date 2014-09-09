/** 
 *
 * File to create a new
 * image
 *
 *
 */

#include <stdlib.h>
#include "image.h"
#include "pixel.h"


image* newImg(int w, int h)
{

	int i;
	image* img = (image*)malloc(sizeof(image));

	img->width = w;
	img->height = h;

	img->pix = (pixel**)malloc(img->height * sizeof(pixel*));

	for(i = 0; i < img->height; i++)
	{
		img->pix[i] = (pixel*)malloc(img->width * sizeof(pixel));
	}

	return img;
}

void freeImg(image* img)
{
	int i;

	if(img->pix != 0)
	{
		for(i = 0; i < img->height; i++)
		{
			if(img->pix[i] != 0)
			{
				free(img->pix[i]);
				img->pix[i] = 0;
			}
		}
		free(img->pix);
		img->pix = 0;
	}

	free(img);
	img = 0;
}
