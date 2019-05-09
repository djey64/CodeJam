package troublesort;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private static final String SPACE = " ";
	private static List<Case> cases = new ArrayList<>();

	public static void main(String[] args) {
		String s = "2\n"+
		"5\n"+
		"5 6 8 4 3\n"+
		"3\n"+
		"8 9 7";
		System.setIn(new BufferedInputStream(new ByteArrayInputStream(s.getBytes())));
		readInput(System.in);
		for(int i = 0; i < cases.size(); i++) {
			String result = resolveCase(cases.get(i));
			System.out.println("Case #"+cases.get(i).getCaseNumber()+": "+result);
		}	
	}
	
	private static String resolveCase(Case ccase) {
		troubleSort(ccase.getArray());
		for(int i = 0; i < ccase.getArray().length -1; i++) 
			if(ccase.getArray()[i] > ccase.getArray()[i + 1]) return ""+i;
		
		return "OK";
	}

	private static void troubleSort(Long[]  array) {
		boolean done = false;
		while(!done) {
			done = true;
			for(int i = 0; i < array.length - 2 ; i++) {
				// Sorted
				if(array[i] > array[i + 2]) {
					done = false;
					Long temp = array[i + 2];
					array[i + 2] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	private static void readInput(InputStream input) {
	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(input)));
	    int caseNb = in.nextInt();
	    in.nextLine();
	    for(int i = 0; i < caseNb; i++) {
		    in.nextLine();
			cases.add(new Case(in.nextLine(), i));
		}
	    in.close();
	}
	
	private static class Case {
		
		private Long[] array = null;
		private long caseNumber = 0;
		
		public Case(String arg, int caseNumber) {
			String[] split = arg.split(SPACE);
			this.array = Arrays.stream(split).map(Long::parseLong).toArray(size -> new Long[size]);
			this.caseNumber = caseNumber;
		}
		
		public Long[] getArray() {
			return array;
		}
		
		public long getCaseNumber() {
			return caseNumber;
		}
	}
}
