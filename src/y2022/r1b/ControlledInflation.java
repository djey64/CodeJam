package y2022.r1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//1
//3 3
//2000000000 2000000000 1
//2000000000 1 2000000000
//2000000000 2000000000 2000000000



public class ControlledInflation {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)))) {
			int t = in.nextInt();
			for (int i = 1; i <= t; i++) {
				System.out.println("Case #" + i + ": " + solveCase(in));
			}
		}
	}

	private static long solveCase(Scanner in) {
		int n = in.nextInt();
		int p = in.nextInt();
		
		long previous = 0;
		long totalPump = 0;
		for (int i = 0; i < n; i++) {
			List<Long> products = new ArrayList<>();
			for (int j = 0; j < p; j++) {
				products.add((long) in.nextInt());
			}
			Collections.sort(products);
			boolean isAsc = Math.abs(products.get(0) - previous) <= Math.abs(products.get(products.size() - 1) - previous);
			
			if(isAsc) {
				totalPump += Math.abs(products.get(0) - previous);
				for(int k = 0; k <= products.size() - 2; k++) {
					totalPump += Math.abs(products.get(k) - products.get(k + 1));
				}
				previous = products.get(products.size() - 1);
			}
			else {
				totalPump += Math.abs(products.get(products.size() - 1) - previous);
				for(int k = products.size() - 1; k >= 1; k--) {
					totalPump += Math.abs(products.get(k) - products.get(k - 1));
				}
				previous = products.get(0);
			}
		}
		
		return totalPump;
	}

}
