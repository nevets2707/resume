# Make file for project 1


all : bw crop overlay colorshift meme

clean : 
	rm *.o bw crop colorshift overlay 


openFile.o : openFile.c 
	gcc -ansi -pedantic -g -c openFile.c

saveFile.o : saveFile.c
	gcc -ansi -pedantic -g -c saveFile.c

image.o : image.c
	gcc -ansi -pedantic -g -c image.c

crop.o : crop.c openFile.o saveFile.o image.o
	gcc -ansi -pedantic -g -c crop.c

bw.o : bw.c openFile.o saveFile.o image.o
	gcc -ansi -pedantic -g -c bw.c

overlay.o : overlay.c openFile.o saveFile.o image.o
	gcc -ansi -pedantic -g -c overlay.c

colorshift.o : colorshift.c saveFile.o image.o
	gcc -ansi -pedantic -g -c colorshift.c

crop : crop.o openFile.o
	gcc -ansi -pedantic -o crop crop.o openFile.o saveFile.o image.o

	
bw : bw.o openFile.o
	gcc -ansi -pedantic -o bw bw.o openFile.o saveFile.o image.o

overlay : overlay.o openFile.o
	gcc -ansi -pedantic -o overlay overlay.o openFile.o saveFile.o image.o

colorshift : colorshift.o openFile.o
	gcc -ansi -pedantic -o colorshift colorshift.o openFile.o saveFile.o image.o

crop2.o : crop2.c openFile.o saveFile.o image.o 
	gcc -ansi -pedantic -g -c crop2.c

overlay2.o : overlay2.c openFile.o saveFile.o image.o
	gcc -ansi -pedantic -g -c overlay2.c

font.o : font.h font.c letter.h
	gcc -ansi -pedantic -g -c font.c

meme.o : meme.c
	gcc -ansi -pedantic -g -c meme.c

meme : font.o meme.o image.o crop2.o overlay2.o openFile.o saveFile.o
	gcc -ansi -pedantic -o meme font.o meme.o image.o crop2.o overlay2.o openFile.o saveFile.o

tarball : 
	tar czf srmonson.tar.gzip *.c *.h Makefile README

valGrind :
	valgrind --leak-check=full --dsymutil=yes --show-reachable=yes ./meme memes.mem insaneproject.act

