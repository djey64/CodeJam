package cryptopangrams;
import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			int l = in.nextInt();
			char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			// Alphabet primes
			SortedSet<Integer> sortedPrimes = new TreeSet<>();
			int[] unCypheredPrimes = new int[l+1];
			int lastPrime = -1;
			int count = 0;
			for(int j = 0; j < l; j++) {
				int current = in.nextInt();
				if(j == 0) {
					// Find the first two prime factors
					for(int k = 2; k <= n; k++) {
						if(isPrime(k) && isInteger(current / (double) k)) {
							sortedPrimes.add(k);
							lastPrime = k;
							unCypheredPrimes[count++] = k;
						}
					}
				} else {
					lastPrime = (int) (current / (double) lastPrime);
					unCypheredPrimes[count++] = lastPrime;
					sortedPrimes.add(lastPrime);
				}
			}
			StringBuilder result = new StringBuilder();
			for(int e : unCypheredPrimes) {
				result.append(alphabet[sortedPrimes.headSet(e).size()]);
			}
			System.out.println("Case #" + i + ": " + result.toString());
		}

	}

	static boolean isPrime(int n) {
		if (n%2==0) return false;
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return false;
		}
		return true;
	}

	private static boolean isInteger(double d) {
		return d == Math.floor(d);
	}
}
