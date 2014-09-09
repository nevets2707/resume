#include "pixel.h"

#ifndef _IMAGE_H
#define _IMAGE_H
#include "image.h"
typedef struct image_s
{

	int height;
	int width;
	int pixelCount;
	pixel** pix;
	

} image;

image* newImg(int w, int h);
void freeImg(image* img);
#endif
