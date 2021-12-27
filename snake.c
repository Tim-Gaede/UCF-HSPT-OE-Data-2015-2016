#include <stdlib.h>
#include <stdio.h>

/*
There are many ways to solve this problem. The approach taken in this solution is:

1. Move left or right until the snake's head is at x0 - 1
2. Move up until the snake's entire body is at x0 - 1 and the head is at least at y1
3. Move right 1 and possibly down until the head is on the upper left corner of the box
4. Start "snaking" right and left until the entire box has been visited

Notice that after step 2, the snake's entire body is outside of the box and in a position
that will never interfere with steps 3 and 4.
*/

void move(int dir, int amount);
int max(int a, int b);

// Store the 4 commands and their corresponding moves
char commands[4] = {'U', 'D', 'L', 'R'};
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

// Store the current location of the snake's head
int headX, headY;

int main()
{
    int t, i;
    scanf("%d", &t);

    // Loop through each test case
    for(i = 0; i < t; i++)
    {
        // Read in input for case
        int len, x0, y0, x1, y1;
        scanf("%d %d %d %d %d", &len, &x0, &y0, &x1, &y1);

        printf("Mission #%d: ", i + 1);

        // Initialize position of snake's head
        headX = 0;
        headY = len;

        // Move snake's head to x0 - 1
        if(headX < x0 - 1)
        {
            move(3, x0 - 1 - headX);
        }
        else
        {
            move(2, headX - (x0 - 1));
        }

        // Move snake so entire body is at x0 - 1 and the head is at least at y1
        move(0, max(len - 1, y1 - headY));

        // Move snake's head to upper left corner of box
        move(3, 1);
        move(1, headY - y1);

        // Move right, then left, then right, and so on, moving down one row each time
        // to cover the entire box
        int j;
        for(j = y1; j >= y0; j--)
        {
            move((y1 - j + 1) % 2 + 2, x1 - x0);

            if(j > y0)
            {
                move(1, 1);
            }
        }

        printf("\n");
    }
}

// Move the snake in the specified direction for the specified amount of moves and
// print the corresponding output
void move(int dir, int amount)
{
    int i;
    for(i = 0; i < amount; i++)
    {
        printf("%c", commands[dir]);
        headX += dx[dir];
        headY += dy[dir];
    }
}

// Return the max of a and b
int max(int a, int b)
{
    if(a > b)
    {
        return a;
    }

    return b;
}
