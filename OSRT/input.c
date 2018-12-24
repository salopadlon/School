#include <sys/socket.h>  
#include <netinet/in.h>  
#include <stdio.h>  
#include <string.h>  
#include <stdlib.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <signal.h>

int main() 
{  
    char    sendBuf[128]={0};
    int     i, k, len;

    printf("   _____            _                                 _      __ ____ ____ ______ \n");
    printf("  / ____|          | |                               | |    /_ |___ \\___ \\____  |\n");
    printf(" | |    _   _ _ __ | |__   ___ _ __ _ __  _   _ _ __ | | __  | | __) |__) |  / / \n");
    printf(" | |   | | | | '_ \\| '_ \\ / _ \\ '__| '_ \\| | | | '_ \\| |/ /  | ||__ <|__ <  / /  \n");
    printf(" | |___| |_| | |_) | | | |  __/ |  | |_) | |_| | | | |   <   | |___) |__) |/ /   \n");
    printf("  \\_____\\__, | .__/|_| |_|\\___|_|  | .__/ \\__,_|_| |_|_|\\_\\  |_|____/____//_/    \n");
    printf("         __/ | |                   | |                                           \n");
    printf("        |___/|_|                   |_|                                           \n");

    int sock_desc = socket(AF_INET, SOCK_STREAM, 0); 
    if (sock_desc == -1) {
        printf("cannot create socket!\n");
        return 0;
    }

    struct sockaddr_in client;  
    memset(&client, 0, sizeof(client));  
    client.sin_family = AF_INET;  
    client.sin_addr.s_addr = inet_addr("127.0.0.1");  
    client.sin_port = htons(1677);  

    if (connect(sock_desc, (struct sockaddr*)&client, sizeof(client)) != 0) {
        printf("cannot connect to server!\n");
        close(sock_desc);
    }

    while(1) {
        for (i = 0;i < 3; ++i) {  
            switch(i) {

                case 0:
                    printf("Enter text to encrypt (without spaces, maximum length 128)\n");  
                    break; 

                case 1:
                    printf("Enter transposition (numbers without spaces, maximum length 9)\n"); 
                    break;    

                case 2:
                    printf("Enter Caesar number\n");
                    break;
            }
            
            scanf("%s", sendBuf);
            len = strlen(sendBuf);

            while (len > 0) {

                k = send(sock_desc, sendBuf, len, 0);

                if (sendBuf[0] != 0)
                    memset(sendBuf, 0, sizeof(sendBuf));

                if (k == -1) {

                    printf("cannot write to backbone!\n");
                    close(sock_desc);
                    exit(1);
                }

                len -= k;

            }

            if (k == -1) {
                printf("cannot write to backbone!\n");
                close(sock_desc);
                exit(1);
            } 
        }
        break;
    }

    sleep(5);
    close(sock_desc);
    printf("backbone disconnected\n");
    exit(0);  
} 