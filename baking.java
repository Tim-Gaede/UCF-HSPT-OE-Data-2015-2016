/**********************
*
* In this problem, we are tasked with finding the area of a shape consisting
* only of sides parallel to the major axes, with the property that any vertical
* line drawn through the shape will divide it into at most 2 pieces. This property
* is vital to the solution presented below, which employs a scan-line approach to
* sum the area of each width-1 slice of the shape. Because any vertical line will
* divide the shape into at most 2 parts, we guarantee that a width-1 slice of the
* shape will be rectangular, given that corner coordinates are integers.
*
* Our strategy will be to maintain a scan-line, or sliding window, representing the
* vertical slice of the shape (low y and high y) at some x-coordinate, as we sweep it
* through the shape from left to right and adjust it accordingly. At each step, the 
* window will represent a width-1 slice of the shape whose area we can add to a running sum.
* We are guaranteed that the leftmost side of the shape has exactly 2 corners, and because
* of the property described above, we know that at any x coordinate there will be either
* 0, 2, or 4 corners. So, if we maintain a list of corners for each x coordinate, we can
* update our scan-line during the sweep! The logic behind this update and how to use the
* corner data is described in greater detail below.
*
**********************/
import java.util.*;

public class baking{
	//Offset value to add to each x-coordinate, translating the shape onto the positive side of the x-axis.
	//We do this so we can easily use the x-coordinate as an index into a 0-based array.
	static int OFFSET = 10000;
	
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		int times = reader.nextInt();
		for(int t = 1; t <= times; t++){
			int n = reader.nextInt();
			
			//Maintain these arrays for corner data
			int[] cnt = new int[20001]; //Number of corners with the same x-coordinate (at most 4)
			int[][] pts = new int[20001][4]; //pts[x] stores the y-coordinates of all corners that share that x coordinate (at most 4), sorted in increasing order
			
			//Find the x-interval in which the shape resides
			int start = 20001; //The leftmost x-coordinate
			int end = -1; //The rightmost x-coordinate
			
			for(int i = 0; i < n; i++){
				//Read the corner coordinate and translate it by the x-offset
				int x = reader.nextInt() + OFFSET;
				int y = reader.nextInt();
				
				//Update our start and end values (widen the x-interval if appropriate)
				start = Math.min(start, x);
				end = Math.max(end, x);
				
				//Add this y coordinate to the list of corners sharing this x coordinate
				pts[x][cnt[x]] = y;
				//Increment the number of corners here
				cnt[x]++;
				//Sort the y coordinates at this location
				Arrays.sort(pts[x], 0, cnt[x]);
			}
			
			//Initialize our scanline with the leftmost face of the shape
			int bot = pts[start][0]; //The low y-coordinate
			int top = pts[start][1]; //The high y-coordinate
			//We take the first slice for fre
			int sum = top-bot;
			
			//Iterate from start+1 to end, exclusive
			for(int i = start+1; i < end; i++){
				//Working corners
				int[] y = pts[i];
				
				//Update our scanline here
				if(cnt[i] == 2){ //2 corners share this coordinate
					//If this is the case, then only one side of our window will change.
					//Which side of the window it is depends on which side matches one of
					//the given corners. For example, if the low-y (bot) of our window is
					//equal to the lesser of the 2 corner y coordinates, then the bottom
					//of our window will elevate to the greater of the 2 corner y coordinates.
					if(y[0] == bot)
						bot = y[1];
					else if(y[1] == bot)
						bot = y[0];
					else if(y[0] == top)
						top = y[1];
					else
						top = y[0];
				}else if(cnt[i] == 4){ //4 corners share this coordinate
					//We can handle the low-y and high-y of our window seperately in this case.
					//Similar to the case where cnt[i] == 2, we flip-flop based on which value
					//in the corner y-coordinates is equal to this end of the window.
					
					//We check the low-y of our window against y[0] and y[1]
					if(y[0] == bot)
						bot = y[1];
					else
						bot = y[0];
					
					//We check the high-y of our window against y[2] and y[3]
					if(y[3] == top)
						top = y[2];
					else
						top = y[3];
				}
				
				//Add the slice to our total
				sum += top-bot;
			}
			
			//Output the result
			System.out.println("Batch #" + t + ": " + sum + " square centimeters");
		}
	}
}
