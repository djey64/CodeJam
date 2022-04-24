package y2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PunchedCards {
	public static void main(String[] args) {
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for(int i = 1; i <= t; i++) {
				int r = in.nextInt();
				int c = in.nextInt();
				System.out.println("Case #"+i+":");

				for(int j = 0; j < r * 2 + 1; j++) {
					StringBuilder sb = new StringBuilder();
					for(int k = 0; k < c * 2 + 1; k++) {
						char character = getChar(j, k);
						sb.append(character);
					}
					System.out.println(sb.toString());
				}
			}
		}
	}

	private static char getChar(int r, int c) {
		if((r == 0 || r == 1) && (c == 0 || c == 1)) return '.';
		if(isEven(r) && isEven(c)) return '+';
		if(!isEven(r) && !isEven(c)) return '.';
		if(isEven(r) && !isEven(c)) return '-';
		return '|';
	}
	
	private static boolean isEven(int i) {
		return i % 2 == 0;
	}
} 
