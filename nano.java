import java.util.*;

public class nano
{
	public static void main(String[] args)
	{
		// All the possible slashes that could occur in the combat logs
		char[] slashes = new char[] {'-', '|', '/', '\\'};
		
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();

		// Iterate over every cyborg
		for (int z = 1; z <= t; z++)
		{
			// Read in the combat log for the current cyborg
			String s = in.next();
		
			// Iniitially the cyborg is in one piece
			int pieces = 1;

			// Iterate over every character in the combat log
			for (int i = 0; i < s.length(); i++)
			{
				// Check to see if the current character is a slash character
				for (int j = 0; j < slashes.length; j++)
				{
					// If the current slash character matches with the current character in the combat log
					// then the number of pieces the cyborg is in is increased by one
					if (slashes[j] == s.charAt(i))
					{
						pieces++;
						break;
					}
				}
			}

			// Print out the start of the output for the current cyborg
			System.out.print("Cyborg #" + z + ": ");

			// Output the correct message
			if (pieces == 1)
			{
				System.out.println("1 piece? You're supposed to be stronger than this!");
			}
			else
			{
				System.out.println(pieces + " pieces!");
			}
		}
	}
}
