#include<stdio.h>

/*
wrut.c
 
by Nick Buelich
*/

main()
{
	int equations, equation; 
	scanf("%d",&equations);
	for(equation = 1; equation <= equations; equation++){
		int A, B, C;
      scanf("%d %d %d",&A,&B,&C);
		printf("%s\n", A+B==C?"Correct!":"Wrut Row!");
	}
}
