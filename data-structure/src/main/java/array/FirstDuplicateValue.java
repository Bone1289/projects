package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers between 1 and n, inclusive, where n is the length of the array, write a function that
 * returns the first integer that appears more than once (when the array is read from left to right).
 * <p>
 * In other words, out of all the integers that might occur more than once in the input array, your function should
 * return the one whose first duplicate value has the minimum index.
 * <p>
 * If no integer appears more than once, your function should return -1.
 *
 * <p>Note that you're allowed to mutate the input array.</p>
 * <h3>Sample Input #1</h3>
 * array = [2, 1, 5, 2, 3, 3, 4]
 *
 * <h3>Sample Output #1</h3>
 * 2 // 2 is the first integer that appears more than once. // 3 also appears more than once, but the second 3 appears
 * after the second 2.
 *
 * <h3>Sample Input #2</h3>
 * array = [2, 1, 5, 3, 3, 2, 4]
 * <h3>Sample Output #2</h3>
 * 3 // 3 is the first integer that appears more than once. // 2 also appears more than once, but the second 2 appears
 * after the second 3.
 */
public class FirstDuplicateValue {
	//	public int firstDuplicateValue(int[] array) {
	//		Set<Integer> seen = new HashSet<>();
	//
	//		for (int value : array) {
	//			if (seen.contains(value)) return value;
	//			seen.add(value);
	//		}
	//		return -1;
	//	}

	public int firstDuplicateValue(int[] array) {
		for (Integer value : array) {
			int absValue = Math.abs(value);
			if (array[absValue - 1] < 0) {
				return absValue;
			} else {
				array[absValue - 1] *= -1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		FirstDuplicateValue firstDuplicateValue = new FirstDuplicateValue();

		int i = firstDuplicateValue.firstDuplicateValue(new int[] { 1, 2, 1, 2 });
		System.out.println("Fist Duplicate is " + i);

	}
}
