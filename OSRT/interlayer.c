#include <sys/socket.h>  
#include <netinet/in.h>  
#include <stdio.h>  
#include <string.h>  
#include <stdlib.h> 
#include <arpa/inet.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

struct cipherStruct
{
    char    plainText[128];
    char    transposition[16];
    char    caesarNum[16];
    char    cipherText[256];
};

int main() 
{  
    struct cipherStruct cipher;
    int shmid, i, j, m, len;
    key_t key;
    char buf[128], *shm;

    int sock_desc = socket(AF_INET, SOCK_STREAM, 0); 
    if (sock_desc == -1)
    {
        printf("cannot create socket!\n");
        return 0;
    }

    struct sockaddr_in client;  
    memset(&client, 0, sizeof(client));  
    client.sin_family = AF_INET;  
    client.sin_addr.s_addr = inet_addr("127.0.0.1");  
    client.sin_port = htons(11677);  

    if (connect(sock_desc, (struct sockaddr*)&client, sizeof(client)) != 0)
    {
        printf("cannot connect to backbone!\n");
        close(sock_desc);
    }

    key = 1666;

    if ((shmid = shmget(key, sizeof(struct cipherStruct), IPC_CREAT | 0666)) < 0) {
        perror("shmget");
        exit(1);
    }

    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(1);
    }


    while(1) 
    {   
        for (i = 0; i < 3; ++i) {

            m = recv(sock_desc, buf, 128, 0); 

            switch(m) {

            case -1:
                printf("\ncannot read from backbone!\n");
                close(sock_desc);
                exit(1);

            case 0:
                printf("\nbackbone disconnected.\n");
                close(sock_desc);
                exit(1);
            }

        if (m > 0) {      
             switch(i) {

                case 0: 
                    j = 0;
                    for (j = 0; j < m; ++j)
                        cipher.plainText[j] = buf[j];
                    break;   

                case 1:
                    j = 0;
                    for (j = 0; j < m; ++j)
                        cipher.transposition[j] = buf[j];
                    break;

                case 2:
                    j = 0;
                    for (j = 0; j < m; ++j)
                        cipher.caesarNum[j] = buf[j];
                    break; 
                }  
            }
        }
        break;  
    }  

    memcpy(shm, &cipher, sizeof(struct cipherStruct));

    while(cipher.cipherText[0] == 0) {
        memcpy(&cipher, shm, sizeof(struct cipherStruct));
    }

    sleep(5);
    shmdt(shm);
    shmctl(shmid, IPC_RMID, NULL);
    close(sock_desc);  
    printf("backbone disconnected\n");
    exit(0);
}