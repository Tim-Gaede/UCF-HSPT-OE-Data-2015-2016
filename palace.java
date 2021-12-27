import java.util.*;

public class palace {
	public static void main(String[] args) {
		Scanner br = new Scanner(System.in);

		int numberOfCases = br.nextInt();
		for (int caseOn = 1; caseOn <= numberOfCases; caseOn++) {

			int length = br.nextInt();

			int neededSize = br.nextInt();

			// We are making a right triangle shaped area in this problem and we
			// are given the hypotenuse so in order to maximize the area we want
			// to make the other two sides equal length. Using Pythagorean
			// Theorem we get
			// length*length = x*x+x*x
			// where x is the length of the other two sides. Solving this we get
			// x = square root ( length *length /2)
			// We then know the area of the triangle will be x*x/2 as x is both
			// the length of the base of the triangle and the height of it.

			double x = Math.sqrt(length * length / 2.0);

			double maxArea = (x * x) / 2.0;

			if (maxArea >= neededSize) {
				System.out.println("Wall #" + caseOn + ": YES");
			} else {
				System.out.println("Wall #" + caseOn + ": NO");
			}

		}
	}
}
