package array;

import java.util.Arrays;
import java.util.List;

/**
 * Given two non-empty arrays of integers, write a function that determines
 * whether the second array is a subsequence of the first one.
 * <p>
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent
 * in the array but that are in the same order as they appear in the array. For
 * instance, the numbers [1,2,3] form a subsequence of the array [1,2,3,4], and so do the numbers [2,4].
 * <b>Note</b>
 * that a single number in an array and the array itself are both valid
 * subsequences of the array.
 */
public class ValidSubsequence {

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int seqIndex = 0;
        for (Integer element : array) {
            if (seqIndex == sequence.size()) {
                break;
            }

            if (element.equals(sequence.get(seqIndex))) {
                seqIndex++;
            }
        }
        return seqIndex == sequence.size();
    }


    public static void main(String[] args) throws Exception {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);

        if (!isValidSubsequence(array, sequence)) {
            throw new Exception("This is Sparta.");
        }
    }
}
