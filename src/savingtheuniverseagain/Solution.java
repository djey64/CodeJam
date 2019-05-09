package savingtheuniverseagain;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	private static final String SPACE = " ";
	private static final char CHARGE = 'C';
	private static final char SHOOT = 'S';
	
	private static List<Case> cases = new ArrayList<>();

	public static void main(String[] args) {
		readInput(System.in);
		for(int i = 0; i < cases.size(); i++) {
			long swapCount = resolveCase(cases.get(i));
			if(swapCount == -1) 
				System.out.println("Case #"+cases.get(i).getCaseNumber()+": "+"IMPOSSIBLE");
			else 
				System.out.println("Case #"+cases.get(i).getCaseNumber()+": "+swapCount);
		}
	}
	
	private static long resolveCase(Case ccase) {
		long effectiveDamage = computeEffectiveDamage(ccase.getProgram());
		long chargeCount = ccase.getProgram().chars().filter(c -> c == CHARGE).count();
		long maximumStrength = (long) Math.pow(2, chargeCount);
		long currentStrength = maximumStrength;
		char[] chars = ccase.getProgram().toCharArray();
		long swapCount = 0;
		if(effectiveDamage <= ccase.getDamage()) return 0;
		for(int i = chars.length - 1; i >= 0; i--) {
			if(chars[i] == CHARGE) {
				currentStrength = currentStrength / 2;
				continue;
			}
			if(i > 0 && chars[i - 1] == CHARGE) {
				int j = i - 1;
				while(j < chars.length - 1 && chars[j + 1] == SHOOT) {
					char temp = chars[j];
					chars[j] = chars [j + 1];
					chars[j + 1] = temp;
					swapCount++;
					effectiveDamage -= currentStrength / 2;
					if(effectiveDamage <= ccase.getDamage()){
						return swapCount;
					}
					j++;
				}
				currentStrength = currentStrength / 2;
			}
		}
		return -1;
	}


	private static long computeEffectiveDamage(String program) {
		long damage = 0;
		long strength = 1;
		
		for(char c : program.toCharArray()) {
			if(c == SHOOT) damage += strength;
			else if(c == CHARGE) strength *= 2;
		}
		return damage;
	}

	private static void readInput(InputStream input) {
	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(input)));
	    int caseNb = in.nextInt();
	    in.nextLine();
		for(int i = 1; i < caseNb + 1; i++) {
			cases.add(new Case(in.nextLine(), i));
		}
	}
	
	private static class Case {
		
		private long damage = 0;
		private String program = "";
		private long caseNumber = 0;
		
		public Case(String arg, int caseNumber) {
			String[] split = arg.split(SPACE);
			this.damage = Integer.parseInt(split[0]);
			this.program = split[1];
			this.caseNumber = caseNumber;
		}
		
		public long getDamage() {
			return damage;
		}
		
		public String getProgram() {
			return program;
		}
		
		public long getCaseNumber() {
			return caseNumber;
		}
	}
}
