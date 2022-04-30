package y2022.qualif;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class D1000000 {

	public static void main(String[] args) {
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt();
			for(int i = 1; i <= t; i++) {
				System.out.println("Case #"+i+": "+solveCase(in));
			}
			
		}
	}

	private static int solveCase(Scanner in) {
		int n = in.nextInt();
		List<Integer> dices = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			dices.add(in.nextInt());
		}
		Collections.sort(dices);
		int nextStraight = 1;
		for (Integer die: dices) {
			if(die >= nextStraight) 
				nextStraight++;
		}
		return nextStraight - 1;
	}
	
}
