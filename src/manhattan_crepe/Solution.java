package manhattan_crepe;
import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int p = in.nextInt();
			int q = in.nextInt();

			int minX = 0, minY = 0;
			int maxX = q, maxY = q;

			for(int j = 0; j < p; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				String d = in.next();

				if (d.equals("N")) 
					minY = Math.max(minY, y);
				else if (d.equals("S")) 
					maxY = Math.min(maxY, y);
				else if (d.equals("W")) 
					maxX = Math.min(maxX, x);
				else if (d.equals("E")) 
					minX = Math.max(minX, x);
			}

			int resultX = minX == 0 ? 0 : maxX - minX > 1 ? minX + 1 : minX;
			int resultY = minY == 0 ? 0 : maxY - minY > 1 ? minY + 1 : minY;
			System.out.println("Case #" + i + ": " + resultX + " " + resultY);
		}
	}
}
