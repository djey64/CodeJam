package y2022.r1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PancakeDeque {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)))) {
			int t = in.nextInt();
			for (int i = 1; i <= t; i++) {
				System.out.println("Case #" + i + ": " + solveCase(in));
			}

		}
	}

	private static int solveCase(Scanner in) {
		int n = in.nextInt();
		List<Integer> pancakes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			pancakes.add(in.nextInt());
		}
		
		int paying = 0;
		int max = Integer.MIN_VALUE;
		
		while(!pancakes.isEmpty()) {
			int d = 0;
			if(max > pancakes.get(0) && max > pancakes.get(pancakes.size() -1))
				d = removeBiggest(pancakes);
			else 
				d = removeSmallest(pancakes);
			if(d >= max) 
				paying++;
			
			max = Math.max(d, max);
		}
		
		return paying;
	}

	private static int removeSmallest(List<Integer> pancakes) {
		if(pancakes.get(0) < pancakes.get(pancakes.size() -1))
			return pancakes.remove(0);
		else 
			return pancakes.remove(pancakes.size() -1);
	}

	private static int removeBiggest(List<Integer> pancakes) {
		if(pancakes.get(0) > pancakes.get(pancakes.size() -1))
			return pancakes.remove(0);
		else 
			return pancakes.remove(pancakes.size() -1);
	}
}
