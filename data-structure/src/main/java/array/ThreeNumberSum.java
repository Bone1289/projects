package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. The function should find all triplets in
 * the array that sum up to the target sum and return a two-dimensional array of
 * all these triplets. The numbers in each triplet should be ordered in ascending
 * order, and the triplets themselves should be ordered in ascending order with
 * respect to the numbers they hold.
 */
public class ThreeNumberSum {
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        List<Integer[]> triplets = new ArrayList<>();
        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;

            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    triplets.add(new Integer[]{
                            array[i], array[left], array[right]
                    });
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return triplets;
    }
}
