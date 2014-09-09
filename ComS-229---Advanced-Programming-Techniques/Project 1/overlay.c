/**
 *
 * File for overlaying
 *
 */

#include <stdio.h> 
#include "image.h"
#include "openFile.h"
#include "saveFile.h"

int main(int argc, char** argv)
{

	image* img1;
	image* img2;
	image* img3;
	int i, j;
	unsigned char r, g, b, a;
	int x = atoi(argv[4]);
	int y = atoi(argv[5]);


	if(argc != 6)
	{
		printf("Invalid number of arguments. Please try again\n");
		return 1;
	}

	while(x < 0)
	{
		printf("Please enter a valid x position:\n>");
		scanf("%d", &x);
	}
	while(y < 0)
	{
		printf("Please enter a valid y position:\n>");
		scanf("%d", &y);
	}

	img1 = open(argv[1]);
	img2 = open(argv[2]);
	img3 = newImg(img1->width, img1->height);

	for(i = 0; i < img3->height; i++)
	{
		for(j = 0; j < img3->width; j++)
		{
			if(i >= y && i < img2->height + y && j >= x && j < img2->width + x)
			{
				if(img2->pix[i - y][j - x].alpha == 255)
				{
					img3->pix[i][j].red = img2->pix[i - y][j - x].red;
					img3->pix[i][j].green = img2->pix[i - y][j - x].green;
					img3->pix[i][j].blue = img2->pix[i - y][j - x].blue;
					img3->pix[i][j].alpha = img2->pix[i - y][j - x].alpha;
				}
				else
				{
				
			 	r = (img2->pix[i - y][j - x].alpha * img2->pix[i - y][j - x].red + img1->pix[i][j].alpha * (255 - img2->pix[i - y][j - x].alpha) * img1->pix[i][j].red / 255) / 255;
				
				g = (img2->pix[i - y][j - x].alpha * img2->pix[i - y][j - x].green + img1->pix[i][j].alpha * (255 - img2->pix[i - y][j - x].alpha) * img1->pix[i][j].green / 255) / 255; 
				b = (img2->pix[i - y][j - x].alpha * img2->pix[i - y][j - x].blue + img1->pix[i][j].alpha * (255 - img2->pix[i - y][j - x].alpha) * img1->pix[i][j].blue / 255) / 255; 

				a = img2->pix[i - y][j - x].alpha + img1->pix[i][j].alpha * (255 - img2->pix[i - y][j - x].alpha) / 255;

				img3->pix[i][j].red = r;
				img3->pix[i][j].green = g;
				img3->pix[i][j].blue = b;
				img3->pix[i][j].alpha = a;
				}
			}
			else
			{
				img3->pix[i][j].red = img1->pix[i][j].red;
				img3->pix[i][j].green = img1->pix[i][j].green;
				img3->pix[i][j].blue = img1->pix[i][j].blue;
				img3->pix[i][j].alpha = img1->pix[i][j].alpha;
		}
			
		}
	}

	save(img3, argv[3]);
	freeImg(img1);
	freeImg(img2);
	freeImg(img3);
	return 0;
}

