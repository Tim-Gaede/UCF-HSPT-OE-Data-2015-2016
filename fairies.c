#include <stdio.h>
#include <stdlib.h>
/* There are three cases:
   0 faries fly in 0 groups
   an odd number of fairies fly in 1 group
   an even numbe of fairies flys in 2 groups (1 fairy seperate and the rest together)
   
   0 fairies never happens (input 0 0 involves one fairy) so we only care about if 
   the number of fairies that can live in the trees is even or odd.
   
   Now, to determine if there the number of fairies in trees 0-N (inclusive) is odd or even
   Because of how ridiciously large the input is, we know there is a pattern or this would be
   unsolvable.  We can write a program to output the catalan numbers in order and see what
   the pattern is. We can also try and logic out how often you add an odd number of odd numbers
   but this is much harder unless mathmatics is your thing.

   We find from this the C(0), C(1), C(3), C(7), and C(15) are all odd which are all one less than a
   power of two.  This means that the sum of all catalans from 0-N switches between even or odd at
   every time the catalan number for the final tree is one less than a power of 2 (probably, but only
   question authors need to write formal proofs!).

   To decide if the sum of all catalans from 0-N is even or odd, we need to know the last time it
   switched, aka the largest power of 2 less than N.  This can be found by figuring out
   what the left most one bit in N+1 is since each bit in N represents a power of two less than
   or equal to it.  If that bit is in an odd posistion the sum is odd otherwise it is even.

   Once we know if, for our input values a and b, 0-a and 0-b are even or odd we can look at
   the range a to b as 0 to b minus 0 to (a-1).  Odd minus even or even minus odd is odd,
   all others are even.  We model this as leftBit(a)+leftBit(b-1) % 2. Now when we consider we
   add one before our check, the answer is ACTUALLY found as leftbit(a+1)+leftBit(b) % 2.
*/

int leftBit(unsigned long long a);

int main() {
    int numForests;
    int caseNum = 0;
    scanf("%d", &numForests);
    
    //iterate through all case numbers
    while (caseNum ++ < numForests)
    {
	//unsigned long long holds up to 2^64
	unsigned long long a, b;
	scanf("%llu %llu", &a, &b);

	//check to make sure input follows bounds
	if (a > b) 
	{
	    printf("error! ");
	}

	//parity is if a number is odd
	int parity = (leftBit(b+1) + leftBit(a)) %2;

	//print one if odd, two if even
	printf("%d\n", parity == 1 ? 1 : 2);
    }
}

//gets the left most bit
int leftBit(unsigned long long a)
{
    int result = 0;
    //while there is a bit more left than what we are at
    while (a > 0)
    {
        //increment our left bit counter and move the number right one bit
	result++;
	a /= 2;
    }

    return result;
}
