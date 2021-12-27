#include<stdio.h>

/*
fence.c

The best way to place L to maximize area, is to create a right triangle with equal side lengths.

To find the maximum area possible, we need to find what the side lengths are.
We know that A^2 + B^2 = C^2 for a right triangle where A and B are side lengths, and C is the hypotenuse.

Since we want A and B to be the same length, we can change our equation to 2 * A^2  = C^2, and since we know that our hypotenuse is L, our equation becomes:  2 * A^2 = L^2, or A = sqrt((L^2) / 2)

Since we know the length of the sides of our triangle, we can calculate the area with the formula: base * height / 2.  In our case, A * A / 2 or sqrt((L^2) / 2) * sqrt((L^2) / 2) / 2.  Since sqrt(x) * sqrt(x) = x,  our equation becomes ((L^2) / 2) / 2 or L^2 / 4 to calculate the area.

So we want to determine if x <= L^2 / 4.
We can rewrite this as 4*x <= L^2 so we can keep the arithmetic in integers so there will not be a loss of precision with floating points.

 
by Nick Buelich
*/

main()
{
	int fences, fence; 
	scanf("%d",&fences);
	for(fence = 1; fence <= fences; fence++){
		int L, x;
      scanf("%d %d",&L,&x);
		printf("Wall #%d: %s\n",fence,4*x<=L*L?"YES":"NO");
	}
}
