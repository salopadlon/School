#include <sys/socket.h>  
#include <netinet/in.h>  
#include <stdio.h>  
#include <string.h> 
#include <stdlib.h>  
#include <arpa/inet.h> 
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

void * thread();

sem_t bin_sem;
typedef struct cipherStruct {
    char    plainText[128];
    char    transposition[16];
    char    caesarNum[16];
    char    cipherText[256];
} CIPHER;

CIPHER cipher;

int main() 
{  
    pthread_t tid;
    char retazec[100], buf[128];
    int k, i, j; 

    if ((sem_init(&bin_sem, 0, 0)) != 0) {
        perror("Semaphore initialization failed");
        exit(EXIT_FAILURE);
    }

    if ((pthread_create(&tid,NULL,thread,NULL)) != 0) {
        perror("Thread creation failed");
        exit(EXIT_FAILURE);
    }

    int sock_desc = socket(AF_INET, SOCK_STREAM, 0);

    if (sock_desc == -1) {
        printf("cannot create socket!\n");
        return 0;
    }

    struct sockaddr_in server;  
    memset(&server, 0, sizeof(server));  
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = inet_addr("127.0.0.1");  
    server.sin_port = htons(1677);  

    if (bind(sock_desc, (struct sockaddr*)&server, sizeof(server)) != 0) {
        printf("cannot bind socket!\n");
        close(sock_desc);  
        return 0;
    }
        
    if (listen(sock_desc, 20) != 0) {
        printf("cannot listen on socket!\n");
        close(sock_desc);  
        return 0;
    }

    struct sockaddr_in client;  
    memset(&client, 0, sizeof(client));  
    socklen_t len = sizeof(client); 

    int temp_sock_desc = accept(sock_desc, (struct sockaddr*)&client, &len);  

    if (temp_sock_desc == -1) {
        printf("cannot accept client!\n");
        close(sock_desc);  
        return 0;
    } 
    while(1) {
        for (i=0; i<3;++i) {

            k = recv(temp_sock_desc, buf, 128, 0);  

            switch (k) {

                case -1:
                    printf("\ncannot read from input!\n");
                    close(temp_sock_desc);  
                    close(sock_desc); 
                    exit(1);

                case 0:
                    printf("\ninput disconnected.\n");
                    close(temp_sock_desc);  
                    close(sock_desc); 
                    exit(1);
            }

            if (k > 0)    
            {
                switch(i) {

                case 0: 
                    j = 0;
                    for (j = 0; j < k; ++j)
                        cipher.plainText[j] = buf[j];
                    sem_post(&bin_sem); 
                    break;   

                case 1:
                    j = 0;
                    for (j = 0; j < k; ++j)
                        cipher.transposition[j] = buf[j];
                    sem_post(&bin_sem); 
                    break;

                case 2:
                    j = 0;
                    for (j = 0; j < k; ++j)
                        cipher.caesarNum[j] = buf[j];
                    sem_post(&bin_sem); 
                    break; 
                }
            }
        }
    }    

    sleep(2);

    close(temp_sock_desc);  
    close(sock_desc);

    if ((pthread_join(tid,NULL)) != 0) {
        perror("Thread join failed");
        exit(EXIT_FAILURE);
    }

    sem_destroy(&bin_sem);

    printf("server disconnected\n");
    return 0;  
} 

void * thread()
{
    int m, i;

    int sock_desc2 = socket(AF_INET, SOCK_STREAM, 0);
    if (sock_desc2 == -1)
    {
        printf("cannot create socket2!\n");
        return 0;
    }

    struct sockaddr_in server2;  
    memset(&server2, 0, sizeof(server2));  
    server2.sin_family = AF_INET;
    server2.sin_addr.s_addr = inet_addr("127.0.0.1");  
    server2.sin_port = htons(11677);  
    if (bind(sock_desc2, (struct sockaddr*)&server2, sizeof(server2)) != 0)
    {
        printf("cannot bind socket2!\n");
        close(sock_desc2);  
        return 0;
    }
        

    if (listen(sock_desc2, 20) != 0)
    {
        printf("cannot listen on socket2!\n");
        close(sock_desc2);  
        return 0;
    }

    struct sockaddr_in client2;  
    memset(&client2, 0, sizeof(client2));  
    socklen_t len2 = sizeof(client2); 
    int temp_sock_desc2 = accept(sock_desc2, (struct sockaddr*)&client2, &len2);  
    if (temp_sock_desc2 == -1)
    {
        printf("cannot accept client2!\n");
        close(sock_desc2);  
        return 0;
    }  
 
    sem_wait(&bin_sem);
    while(1) {
        for (i=0; i<3;++i) {
              
            switch(i) {

            case 0: 
                m = send(temp_sock_desc2, cipher.plainText, sizeof(cipher.plainText), 0);
                sem_wait(&bin_sem);
                break;   

            case 1:
                m = send(temp_sock_desc2, cipher.transposition, sizeof(cipher.transposition), 0);  
                sem_wait(&bin_sem);                 
                break;

            case 2:
                m = send(temp_sock_desc2, cipher.caesarNum, sizeof(cipher.caesarNum), 0);
                sem_wait(&bin_sem);
                break; 
            }
        }
    }

    sleep(1);
 
    close(temp_sock_desc2);  
    close(sock_desc2); 
    printf("interlayer disconnected\n");
}