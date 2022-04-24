package y2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Printing3D {

	public static void main(String[] args) {
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt();
			for(int i = 1; i <= t; i++) {
				System.out.println("Case #"+i+": "+solveCase(in));
			}
			
		}
	}

	private static String solveCase(Scanner in) {
		// TODO Auto-generated method stub
		int minC = Integer.MAX_VALUE, minM = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minK = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			int c = in.nextInt();
			int m = in.nextInt();
			int y = in.nextInt();
			int k = in.nextInt();
			if(minC > c) minC = c;
			if(minM > m) minM = m;
			if(minY > y) minY = y;
			if(minK > k) minK = k;
		}
		
		if(minC + minM + minY + minK < 1_000_000) return "IMPOSSIBLE";

		int ink = 1_000_000;
		ink = ink - minC;
		if (minM <= ink) {
			ink = ink - minM;
		}
		else {
			minM = ink;
			minY = minK = 0;
			ink = 0;
		}
		if (minY <= ink) {
			ink = ink - minY;
		}
		else {
			minY = ink;
			minK = 0;
			ink = 0;
		}
		if (minK <= ink) {
			ink = ink - minK;
		}
		else {
			minK = ink;
		}
		
		
		return minC + " " + minM + " " + minY + " " + minK;
	}
}