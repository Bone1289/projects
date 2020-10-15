package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. The function should find all quadruplets in
 * the array that sum up to the target sum and return a two-dimensional array of
 * all these quadruplets in no particular order.
 * <p>
 * If no four numbers sum up to the target sum, the function should return an
 * empty array.
 */
public class FourNumberSum {

    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        Map<Integer, List<Integer[]>> allPairSum = new HashMap<>();
        List<Integer[]> quadruplets = new ArrayList<>();
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int currentSum = array[i] + array[j];
                int diff = targetSum - currentSum;
                if (allPairSum.containsKey(diff)) {
                    for (Integer[] pair : allPairSum.get(diff)) {
                        Integer[] newQuadruplet = {pair[0], pair[1], array[i], array[j]};
                        quadruplets.add(newQuadruplet);
                    }
                }
            }
            for (int k = 0; k < i; k++) {
                int currentSum = array[i] + array[k];
                Integer[] pair = {array[k], array[i]};
                if (!allPairSum.containsKey(currentSum)) {
                    List<Integer[]> pairGroup = new ArrayList<>();
                    pairGroup.add(pair);
                    allPairSum.put(currentSum, pairGroup);
                } else {
                    allPairSum.get(currentSum).add(pair);
                }
            }
        }

        return quadruplets;
    }
}
