


all : ssbros


objects : 
	g++ -g -c GameManager.cpp
	g++ -g -c Pursuer.cpp
	g++ -g -c OtherActors.cpp
	g++ -g -c GraphMap.cpp
	g++ -g -c Actor.cpp


Pos.o : Pos.cpp
	g++ -g -Wall -c Pos.cpp

simplehero.o : simplehero.cpp Pos.o
	g++ -g -Wall -c simplehero.cpp

SmartHero.o : SmartHero.cpp Pos.o
	g++ -g -Wall -c SmartHero.cpp

SmartEnemy.o : SmartEnemy.cpp Pos.o
	g++ -g -Wall -c SmartEnemy.cpp

SmartPowerup.o : SmartPowerup.cpp Pos.o
	g++ -g -Wall -c SmartPowerup.cpp

ssbros : ssbros.cpp OtherActors.o simplehero.o SmartHero.o SmartEnemy.o SmartPowerup.o Pos.o
	g++ -g -Wall -o ssbros ssbros.cpp Actor.o GraphMap.o GameManager.o OtherActors.o Pursuer.o simplehero.o SmartHero.o SmartEnemy.o SmartPowerup.o Pos.o -lncurses

OtherActors.o: OtherActors.cpp
	g++ -g -Wall -c OtherActors.cpp

# Modify the clean target to remove YOUR .o files...
clean:
	rm Pos.o simplehero.o SmartHero.o SmartEnemy.o SmartPowerup.o ssbros OtherActors.o

tarball:
	tar czf srmonson.tar.gz Makefile *.hpp *.cpp *.o maps/*.txt README

