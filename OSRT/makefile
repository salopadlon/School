CC=gcc

all: backbone interlayer cipher input

UI: input.c 
	$(CC) -o input input.c

backbone: backbone.c
	$(CC) -o backbone backbone.c -lpthread 

interlayer: interlayer.c 
	$(CC) -o interlayer interlayer.c

cipher: cipher.c
	$(CC) -o cipher cipher.c -lrt

clear:
	rm input backbone interlayer cipher