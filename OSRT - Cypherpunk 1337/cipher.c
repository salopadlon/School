#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <time.h>
#include <signal.h>

void sigHandler(int);
timer_t createTimer(int);
void runTimer(timer_t, int);

struct cipherStruct
{
    char    plainText[128];
    char    transposition[16];
    char    caesarNum[16];
    char    cipherText[256];
};

int main()
{   
    signal(SIGINT, sigHandler);
    timer_t timer;
    timer = createTimer(SIGKILL);
    runTimer(timer, 60);

    pid_t   childpid;
    int     shmid, i, j, k, l, a = 0 ,len, m = 0, fd[2], tempCaesar, intTrans[64];
    key_t   key;
    char    *shm, *s, ch, t, tempText[128], tempTrans[16];

    key = 1666;

    if ((shmid = shmget(key, sizeof(struct cipherStruct), 0666)) < 0) {
        perror("shmget");
        exit(1);
    }

    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(1);
    }

    struct cipherStruct* cipher = (struct cipherStruct*)malloc(sizeof(struct cipherStruct)*1);

    while (cipher->caesarNum[0] == 0) {
        memcpy(cipher, shm, sizeof(struct cipherStruct));
    }

    len = strlen(cipher->plainText);

    i = 0;
    for (i = 0; i < len; ++i) {
        tempText[i] = cipher->plainText[i];
    }

    tempText[i] = '\0';

    i = 0;
    for (i = 0; i < strlen(cipher->transposition); ++i) {
        tempTrans[i] = cipher->transposition[i];
        intTrans[i] = atoi(&tempTrans[i]);
    }

    tempCaesar = atoi(cipher->caesarNum);

    pipe(fd);

    if((childpid = fork()) == -1) {
        perror("fork");
        exit(1);
    }

    if(childpid == 0) {
        close(fd[0]);
    
        for(i = 0; tempText[i] != '\0'; ++i) {
            ch = tempText[i];
            
            if(ch >= 'a' && ch <= 'z') {
                ch = ch + tempCaesar;
                
                if(ch > 'z') {
                    ch = ch - 'z' + 'a' - 1;
                }
                
                tempText[i] = ch;
            }

            else if(ch >= 'A' && ch <= 'Z') {
                ch = ch + tempCaesar;
                
                if(ch > 'Z') {
                    ch = ch - 'Z' + 'A' - 1;
                }
                
                tempText[i] = ch;
            }
        }

        write(fd[1], tempText, sizeof(tempText));

        exit(0);
    }

    else {
        close(fd[1]);

        read(fd[0], tempText, sizeof(tempText));

        l = strlen(cipher->transposition);
        char temp[l];
        i = 0;
        t = tempText[0];

        while(t != '\n' && t != '\0') {

            for(j = 0; j < l; ++j) {
                t = tempText[i];
                if(t != '\n' && t != '\0') {
                    temp[j] = t;
                    ++i;
                }

                else 

                    temp[j] = '.';
            }

            if(temp[0] != '.') {
                for(k = (0 + a); k < (l + a); ++k) {
                    cipher->cipherText[k] = temp[intTrans[k - a]];
                }
            }
            a += l;
        }

        cipher->cipherText[k] = '\0';

        printf("Your cipher is: %s\nPlease, write down your cipher. Program will be closed automatically.\n", cipher->cipherText);

        sleep(10);
        exit(0);
    }  
}

void sigHandler(int signal) 
{
    char c;

    printf("\nDo you really want to quit? [y/n] \n");
    c = getchar();
    
    if (c == 'y' || c == 'Y')
        exit(0);
    
    else {
        printf("    ▄▄▄▄▀▀▀▀▀▀▀▀▄▄▄▄▄▄\n");
        printf("    █░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▀▀▄\n");
        printf("   █░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░░█\n");
        printf("  █░░░░░░▄██▀▄▄░░░░░▄▄▄░░░█\n");
        printf(" ▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░░█\n");
        printf("█▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒▒█\n");
        printf("█▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀▄▒█\n");
        printf(" █▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░░█\n");
        printf("  █░░██░░▀█▄▄▄█▄▄█▄████░█\n");
        printf("    █░░░▀▀▄░█░░░█░███████░█\n");
        printf("       ▀▄▄░▒▒▒▒░░░░░░░░░░█\n");
        printf("          ▀▀▄▄░▒▒▒▒▒▒▒▒▒▒░█\n");
        printf("              ▀▄▄▄▄▄░░░░░█\n");
        printf("\nJust trolling. Exiting anyway.\n");
        sleep(2);
        exit(0);

    }
}

timer_t createTimer(int signal)
{
    struct sigevent kam;
    kam.sigev_notify=SIGEV_SIGNAL;
    kam.sigev_signo=signal;
    timer_t casovac;
    timer_create(CLOCK_REALTIME, &kam, &casovac);
    return(casovac); 
}

void runTimer(timer_t timer, int seconds)
{
    struct itimerspec casik;
    casik.it_value.tv_sec=seconds;
    casik.it_value.tv_nsec=0;
    casik.it_interval.tv_sec=0;
    casik.it_interval.tv_nsec=0;
    timer_settime(timer,CLOCK_REALTIME,&casik,NULL);
}