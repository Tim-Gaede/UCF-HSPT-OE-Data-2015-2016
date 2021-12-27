import java.util.*;
public class fairies {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		/*
		 * These numbers are called Catalan numbers. Since we only want to know the minimum number of
		 * groups that the faries can form, we really just need to know if the sum of all the Catalan 
		 * numbers in a range is even or odd. If it's even, we need to form an extra group because we 
		 * can't put a fairy at the tip of the group if every fairy is part of a pair. So, if the sum 
		 * is even we must form two groups. Otherwise, we can form one. Since we only need to know if
		 * the sum of the Catalan numbers in the given range is even or odd, we really only need to
		 * know how many odd Catalan numbers are part of the range. If we look at the first several
		 * Catalan numbers, a pattern becomes apparent: Catalan numbers are odd if their index is one
		 * less than a power of two. So, the problem becomes figuring out how many numbers that are 
		 * one less than a power of two are in the given range.
		 */
		
		//Here we're precomputing all of the powers of two less than 10e18, and adding them to a list.
		ArrayList<Long> twos = new ArrayList<Long>();
		long last = 1;
		for(int i = 1; i < 63; i++){
			//The -1 is because we're actually looking for numbers one less than a power of two.
			twos.add(last - 1);
			last*=2;
		}
		for(int z = 0; z < t; z ++){
			long a = in.nextLong();
			long b = in.nextLong(); 
			
			//Now let's go through our list of valid indices and see how many are in our range. 
			int ctr = 0;
			for(int i = 0; i < twos.size(); i ++){
				if(twos.get(i) >= a && twos.get(i) <= b){
					ctr++;
				}
			}
			
			/*
			 * If the number of odd Catalan numbers is even, we want to say that we formed two 
			 * groups. If it's odd, we want to say that we formed one.
			 */
			ctr++;
			System.out.println((ctr % 2) + 1);
		}
	}
}
