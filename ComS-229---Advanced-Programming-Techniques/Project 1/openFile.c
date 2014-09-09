/**
 *
 *  Program to open binary folders
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "pixel.h"
#include "image.h"
#include "openFile.h"

image* open(char* file)

{
	FILE* infile;
	char* newFile;
	image* temp;
	int i, j;

	temp = (image*)malloc(sizeof(image));
	infile = fopen(file, "rb");

	if(infile == 0)
	{
		printf("Invalid file %s\n", file);
		return temp;
	

	}
		
	if(!feof(infile))
	{
		fread(&(temp->width), sizeof(int), 1, infile);	
		fread(&(temp->height), sizeof(int), 1, infile);

		temp->pix = (pixel**)malloc(temp->height * sizeof(pixel*));

		for(i = 0; i < temp->height; i++)
		{
			temp->pix[i] = (pixel*)malloc(temp->width * sizeof(pixel));
		}
	

		for(i = 0; i < temp->height; i++)
		{
			for(j = 0; j < temp->width; j++)
			{						
				fread(&(temp->pix[i][j].red), sizeof(char), 1, infile);
				fread(&(temp->pix[i][j].green), sizeof(char), 1, infile);
				fread(&(temp->pix[i][j].blue), sizeof(char), 1, infile);
				fread(&(temp->pix[i][j].alpha), sizeof(char), 1, infile);
			}
		}
	}

	return temp;

}  
