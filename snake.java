import java.util.*;
public class snake {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
		int length = input.nextInt();
		int x0 = input.nextInt(), y0 = input.nextInt();
		int x1 = input.nextInt(), y1 = input.nextInt();
		String res = "";
		int x = 0;
		int y = length;
		/*
		 * 1. Start out by moving really far up and to the right.
		 * 2. Then, move left and down so that we are always entering the box from the upper-left corner with our body above us.
		 * 3. Then, go down to the bottom of the box, move one to the left, and go back to the top of the box.
		 * 4. Repeat step 3 until we hit the left side of the box. 
		 */
		for(int i = 0; i<1000; i++)
		{
			x++;
			res += "R";
		}
		for(int i = 0; i<1000; i++)
		{
			y++;
			res += "U";
		}
		// Now we are far above and to the right of the box.
		while(x > x1)
		{
			x--;
			res += "L";
		}
		while(y > y1)
		{
			y--;
			res += "D";
		}
		// Now we are in the upper-left corner of the box and all of our last moves were down so our body is above us.
		while(x >= x0)
		{
			if(y == y1)
			{
				// At y1, so move down to y0
				while(y > y0)
				{
					res += "D";
					y--;
				}
			}
			else
			{
				// At y0, so move up to y1
				while(y < y1)
				{
					res += "U";
					y++;
				}
			}
			if(x == x0) break;
			res += "L";
			x--;
		}
		System.out.printf("Mission #%d: %s\n", t+1, res);
	}
}
}
