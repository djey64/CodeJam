package cryptopangrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box.Filler;

public class primes {


	public static void main(String[] args) {
		int[] s = {9767, 9767, 9767, 9767, 9769,9781,9787,9791,9803,9811,9817,9829,9833,9839,9851,9857,9859,9871,9883,9887,9901,9907,9923,9929,9931,9941,9949,9967,9973,9973};
		List<Integer> cripted = new ArrayList<>();
		for(int i = 0; i < s.length - 1; i++) {
			cripted.add( s[i] * s[i + 1]);
		}
		System.out.println(cripted.toString().replaceAll(",", ""));
		System.out.println(cripted.size());
	}

}
