package foregonesolution;

import java.util.*;
import java.io.*;
public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
	in.nextLine();
    for (int i = 1; i <= t; ++i) {
    	String n = in.nextLine();
    	char[] a = new char[n.length()];
    	char[] b = new char[n.length()];
    	for (int j = 0; j < n.length(); j++) {
    		if (n.charAt(j) == '4') {
    			a[j] = '3';
    			b[j] = '1';
    		} else {
    			a[j] = n.charAt(j);
    			b[j] = '0';
    		}
    	}
    	String bString = String.valueOf(b).replaceFirst("^0+(?!$)", "");
    	System.out.println("Case #" + i + ": " + String.valueOf(a) + " " + bString);
    }
  }
}
