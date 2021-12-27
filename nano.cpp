#include <stdio.h>

using namespace std;

int main()
{
    int tests;
    scanf("%d", &tests);

    char log[1001];
    for(int t = 1; t<=tests; t++)
    {
        scanf("%s", log);

        // We always start with one piece. Every slice adds another piece.
        int answer = 1;

        int index = 0;
        while(log[index])
        {
            // Check if this log entry is a slice.
            if(log[index]=='/' || log[index]=='\\' || log[index]=='|' || log[index]=='-')
            {
                answer++;
            }
            index++;
        }

        // Now print out our answer.
        if(answer == 1)
        {
            printf("Cyborg #%d: 1 piece? You're supposed to be stronger than this!\n", t);
        }
        else
        {
            printf("Cyborg #%d: %d pieces!\n", t, answer);
        }
    }
	return 0;
}
