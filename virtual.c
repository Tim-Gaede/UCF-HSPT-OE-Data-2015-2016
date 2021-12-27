#include<stdio.h>

main()
{
    int numberOfCases,caseOn,breakTime,playTime,neededTime,neededBreaks,totalTime;
	scanf("%d",&numberOfCases);		
	for (caseOn = 1; caseOn <= numberOfCases; caseOn++) {
		scanf("%d %d %d",&breakTime,&playTime,&neededTime);		
		
		// The number of playing sessions we need is the total time we need
		// to play divided the amount of time we play each session rounding
		// up to the nearest integer. The number of breaks we need is this
		// number minus since we don't need a break before the first play
		// session.
		neededBreaks = ((neededTime + (playTime - 1)) / playTime) - 1;
		
		// The total time taken is the total playing time needed plus the
		// number breaks we take times the amount of time per break.
		totalTime = neededTime + breakTime * neededBreaks;
		printf("%d\n",totalTime);
	}
}