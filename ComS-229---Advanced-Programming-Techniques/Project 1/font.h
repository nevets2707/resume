#include "letter.h"

#ifndef __FONT_H_
#define __FONT_H_

typedef struct font_s
{
	char* name;
	char* fileLocation;
	int charCount;
	letter** list;

} font;

font* openFont(char* file);
void freeFontDP(font** f, int count);
#endif
