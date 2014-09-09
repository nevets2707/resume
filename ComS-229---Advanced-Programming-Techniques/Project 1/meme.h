#include "font.h"
#include "crop2.h"

#ifndef __MEME_H_
#define __MEME_H_

typedef struct pos_s pos;

typedef struct meme_s
{
	char* name;
	char* file;
	int locCount;
	int fontCount;
	pos** loc;
	font** fonts; /* maybe */
} meme;

struct pos_s
{
	char* name;
	int x;
	int y;
};

meme** readMemeFile(char* file);
int memeCounter(char* file);
int readActFile(char* file, meme** memes, int memeCount);
image* textImg(char* in, font* f);
image* addText(image* toChange, image* toAdd);
void freeMemeDP(meme** m, int count);
#endif

