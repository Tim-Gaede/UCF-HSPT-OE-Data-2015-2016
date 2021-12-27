import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class snakeChecker {
	Pattern outputPattern = Pattern.compile("Mission #([0-9]+): ?([RDLU]*)");
	int length, lowerLeftx, lowerLefty, upperRightx, upperRighty;
	boolean checkTests(Scanner snakeIn, Scanner snakeOut){
		boolean isCorrect = true;
		int tests = snakeIn.nextInt();
		for(int t = 1; t<=tests; t++){
			length = snakeIn.nextInt();
			lowerLeftx = snakeIn.nextInt();
			lowerLefty = snakeIn.nextInt();
			upperRightx = snakeIn.nextInt();
			upperRighty = snakeIn.nextInt();
			if(!snakeOut.hasNextLine())
				throw new SnakeError("Too little output. ["+t+"]");
			String str = snakeOut.nextLine();
			Matcher m = outputPattern.matcher(str);
			if(!m.matches())
				throw new SnakeError("Incorrect Format. ["+t+"]");
			int testNumber = Integer.parseInt(m.group(1));
			if(testNumber != t)
				throw new SnakeError("Unexpected Test Number. ["+t+"]");
			String sequence = m.group(2);
			try{
				verdict(sequence, t);
			}catch(SnakeError e){
				isCorrect = false;
				System.out.println(e.getMessage());
			}
		}
		if(snakeOut.hasNext())
			throw new SnakeError("Too Much Output.");
		return isCorrect;
	}
	void verdict(String sequence, int testNumber){
		if(sequence.length() > 1_000_000)
			throw new SnakeError("Wrong Answer - Answer is too long.");
		ArrayDeque<Point> snakeOrder = new ArrayDeque<>();
		HashSet<Point> snakeSet = new HashSet<>();
		for(int i = 1; i<=length; i++){
			Point p = new Point(0, i);
			snakeOrder.addLast(p);
			snakeSet.add(p);
		}
		Point prev = snakeOrder.peekLast();
		Point next;
		for(char cmd: sequence.toCharArray()){
			if(cmd == 'U')
				next = new Point(prev.x, prev.y+1);
			else if(cmd == 'D')
				next = new Point(prev.x, prev.y-1);
			else if(cmd == 'L')
				next = new Point(prev.x-1, prev.y);
			else // 'R'
				next = new Point(prev.x+1, prev.y);
			snakeSet.remove(snakeOrder.removeFirst());
			if(snakeSet.contains(next))
				throw new SnakeError("Wrong Answer - Snake crosses itself. ["+testNumber+"]");
			snakeSet.add(next);
			snakeOrder.addLast(next);
			prev = next;
		}
		for(Point p: snakeOrder){
			if(p.x > upperRightx || p.y > upperRighty || p.x < lowerLeftx || p.y < lowerLefty)
				throw new SnakeError("Wrong Answer - Snake not in box. ["+testNumber+"]");
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		snakeChecker check = new snakeChecker();
		Scanner snakeIn = new Scanner(new File(args[0]));
		Scanner snakeOut = new Scanner(new File(args[1]));
		boolean isCorrect = true;
		try{
			isCorrect = check.checkTests(snakeIn, snakeOut);
                        if (!isCorrect)
  				System.out.printf("Wrong Answer%n");
		}catch(SnakeError e){
			isCorrect = false;
			System.out.printf("Final Verdict: %s%n", e.getMessage());
		}
		snakeIn.close();
		snakeOut.close();
	}
	class Point{
		int x, y;
		Point(int xx, int yy){
			x = xx; y = yy;
		}
		@Override
		public boolean equals(Object obj) {
			Point o = (Point)obj;
			return o.x == x && o.y == y;
		}
		@Override
		public int hashCode() {
			return x*107+y;
		}
		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}
	class SnakeError extends Error{
		public SnakeError(String message) {
			super(message);
		}
	}
}
