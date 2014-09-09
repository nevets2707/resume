#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "letter.h"
#include "font.h"

font* openFont(char* file)
{
	int i;
	int charPos, length;
	int count, line;
	int size;
	char c;
	char* temp;
	char* temp2;
	font* newFont;
	FILE* in = fopen(file, "r");

	if(in == 0)
	{
		printf("Couldn't open the font file\n");
		return;
	}
	
	size = 0;
	newFont = (font*)malloc(sizeof(font));
	newFont->list = (letter**)malloc(size);
	temp = (char*)malloc(128 * sizeof(char));

	newFont->charCount = 0;

	c = getc(in);
	count = 0;
	line = 0;

	while(c != EOF)
	{
		count = 0;
		while(c != '\n' && c != EOF)
		{
			temp[count] = c;
			count++;
			c = getc(in);
		}
		if(c == '\n')
		{
			c = getc(in);
		}
		temp[count] = 0;

		i = 0;
		while(temp[i] != 0)
		{
			if(temp[i] == ' ' || temp[i] == ':')
			{
				if(temp[i + 1] != ':')
				{
					temp[i] = 0;
				}
			}
			i++;
		}

		charPos = 0;
		temp2 = &(temp[charPos]);
		if(strcmp(temp2, "NAME") == 0)
		{
			length = strlen(temp2);
			charPos += length + 1;
			temp2 = &(temp[charPos]);
			newFont->name = (char*)malloc(strlen(temp2) + 1 * sizeof(char));
			strcpy(newFont->name, temp2);

		}
		else if(strcmp(temp2, "IMAGE") == 0)
		{
			length = strlen(temp2);
			charPos += length + 1;
			temp2= &(temp[charPos]);
			newFont->fileLocation = (char*)malloc(strlen(temp2) + 1 * sizeof(char));
			strcpy(newFont->fileLocation, temp2);
		
		}
		else if(strncmp(temp2, "CHARACTER", 9) == 0)
		{
			/* realloc memory for the list of letters and adds data from the line */
			size += sizeof(letter*);
			newFont->list = (letter**)realloc(newFont->list, size);
			
			newFont->list[line] = (letter*)malloc(sizeof(letter));
			

			newFont->list[line]->value = temp2[9];
		
			length = strlen(temp2);
			charPos += length + 1;
			temp2 = &(temp[charPos]);
			newFont->list[line]->x = atoi(temp2);
	
			length = strlen(temp2);
			charPos += length + 1;
			temp2 = &(temp[charPos]);		
			newFont->list[line]->y = atoi(temp2);

			length = strlen(temp2);
			charPos += length + 1;
			temp2 = &(temp[charPos]);
			newFont->list[line]->w = atoi(temp2);
	
			length = strlen(temp2);
			charPos += length + 1;
			temp2 = &(temp[charPos]);
			newFont->list[line]->h = atoi(temp2);

			line++;
		}

		newFont->charCount = line;
	}
	return newFont;
}

void freeFontDP(font** f, int count)
{
	int i, j;

	for(i = 0; i < count; i++)
	{
		free(f[i]->name);
		free(f[i]->fileLocation);
		
		for(j = 0; j < f[i]->charCount; j++)
		{
			free(f[i]->list[j]);
		}
		free(f[i]->list);

	}
	free(f);
}
