package interview;

import java.util.Arrays;

public class TwoNumberSum {
    //    public static int[] twoNumberSum(int[] array, int targetSum) {
//        for (int i = 0; i < array.length - 1; i++) {
//            int firstNum = array[i];
//
//            for (int j = i + 1; j < array.length; j++) {
//                int secondNum = array[j];
//                if (firstNum + secondNum == targetSum) {
//                    return new int[]{
//                            firstNum, secondNum
//                    };
//                }
//            }
//        }
//        return new int[0];
//    }
//    public static int[] twoNumberSum(int[] array, int targetSum) {
//        Set<Integer> nums = new HashSet<>();
//        for (int num : array) {
//            int potentialMatch = targetSum - num;
//            if (nums.contains(potentialMatch)) {
//                return new int[]{potentialMatch, num};
//            } else {
//                nums.add(num);
//            }
//        }
//
//        return new int[0];
//    }

    public static int[] twoNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int currentSum = array[left] + array[right];
            if (currentSum == targetSum) {
                return new int[]{array[left], array[right]};
            } else if (currentSum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
