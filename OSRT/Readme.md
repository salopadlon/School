# Cypherpunk 1337  
* Semestral project for subject OSRT - Operating systems of real time  
  
Cypherpunk 1337 is a cryptographic tool, using combination of transposition and substitution (Caesar) ciphre.  
The program consists of 4 processes that are communincating through various types of IPC. Programs are compiled using makefile command:  
```
make all
```
Then, it is important to run the programs one by one in order:  
1. Run backbone `./backbone`  
2. Run interlayer `./interlayer`  
3. Run cipher `./cipher`  
4. Run input `./input`  
  
Backbone and interlayer are able to run in background, input and cipher are used as User Interface.
