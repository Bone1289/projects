package array;

import java.util.Arrays;
import java.util.List;

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

        if(!isValidSubsequence(array, sequence)){
            throw new Exception("This is Sparta.");
        }
    }
}
