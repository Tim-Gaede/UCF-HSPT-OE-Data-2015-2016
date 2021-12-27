import java.util.*;
public class virtual {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
		int b = input.nextInt();
		int p = input.nextInt();
		int timeNeeded = input.nextInt();
		int totalTime = 0;
		while(timeNeeded > p)
		{
			totalTime += p; // Play for p minutes.
			timeNeeded -= p;
			totalTime += b; // Still more playing to be done, so take a break.
		}
		totalTime += timeNeeded; // We can finish the rest of the game all at once.
		System.out.println(totalTime);
	}
}
}
