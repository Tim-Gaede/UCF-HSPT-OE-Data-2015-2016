import java.util.*;

public class wrut {
	public static void main(String[] args) {
		// Create a scanner for standard input.
		Scanner in = new Scanner(System.in);
		
		// Input the number of equations to check.
		int N = in.nextInt();

		// Loop for each equation.
		for (int i=0; i<N; i++) {
			
			// Read in the 3 values.
			int A = in.nextInt();
			int B = in.nextInt();
			int C = in.nextInt();
			
			// Check if the equation is valid, and
			// output the message accordingly.
			if (A+B==C)
				System.out.println("Correct!");
			else
				System.out.println("Wrut Row!");

		}
	}
}
