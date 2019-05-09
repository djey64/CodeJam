package robot_programming_strategy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	
	private static final String R = "R";
	private static final String S = "S";
	private static final String P = "P";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int a = in.nextInt();
			boolean winner = false;
			StringBuilder result = new StringBuilder();
			List<String> opponents = new ArrayList<String>();
			for (int j = 0; j < a; j++) {
				opponents.add(in.next());
			}
			int j = 0;
			while(j < 500) {
				Set<String> tries = new HashSet<>();
				for(String c : opponents) {
					tries.add(Character.toString(c.charAt(j % c.length())));
				}
				String myTry = getWinning(tries);
				if(myTry.equals("error")) {
					//IMPOSSIBLE
					break;
				}
				result.append(myTry);
				if(tries.size() == 1) {
					// Winner
					System.out.println("Case #" + i + ": " + result.toString());
					winner = true;
					break;	
				} 
				// Remove loosing opponents
				final int jj = j;
				opponents = opponents.stream().filter(o -> !isLoosing(myTry, Character.toString(o.charAt(jj % o.length())))).collect(Collectors.toList());
				j++;
			}
			// No winning ending => DRAW
			if(!winner)
			System.out.println("Case #" + i + ": IMPOSSIBLE");
		}
	}
	
	private static boolean isLoosing(String myChar, String opponentChar) {
		if(myChar.equals(P) && opponentChar.equals(R)) return true;
		if(myChar.equals(R) && opponentChar.equals(S)) return true;
		if(myChar.equals(S) && opponentChar.equals(P)) return true;
		return false;
	}

	private static String getWinning(Set<String> tries) {
		if(tries.size() == 1){
			if(tries.contains(R)) return P;
			if(tries.contains(P)) return S;
			if(tries.contains(S)) return R;
		}
		else if (tries.size() == 2) {
			if(tries.contains(R) && tries.contains(P)) return P;
			if(tries.contains(R) && tries.contains(S)) return R;
			if(tries.contains(S) && tries.contains(P)) return S;
		}
		return "error";
	}
}
