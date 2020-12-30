package array;

import java.util.Arrays;

/**
 * Write a function that takes in two non-empty arrays of integers, finds the
 * pair of numbers (one from each array) whose absolute difference is closest to
 * zero, and returns an array containing these two numbers, with the number from
 * the first array in the first position.
 * <p>
 * Note that the absolute difference of two integers is the distance between
 * them on the real number line. For example, the absolute difference of -5 and 5
 * is 10, and the absolute difference of -5 and -4 is 1.
 * <p>
 * You can assume that there will only be one pair of numbers with the smallest
 * difference.
 */
public class SmallerDifference {
    public static int[] smallerDifference(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int idxOne = 0;
        int idxSecond = 0;

        int smallest = Integer.MAX_VALUE;
        int currentValue;

        int[] smallestPair = new int[2];

        while (idxOne < arrayOne.length && idxSecond < arrayTwo.length) {
            int firstNum = arrayOne[idxOne];
            int secondNum = arrayTwo[idxSecond];

            if (firstNum < secondNum) {
                currentValue = secondNum - firstNum;
                idxOne++;
            } else if (secondNum < firstNum) {
                currentValue = firstNum - secondNum;
                idxSecond++;
            } else {
                return new int[]{
                        firstNum, secondNum
                };
            }

            if (smallest > currentValue) {
                smallest = currentValue;
                smallestPair = new int[]{firstNum, secondNum};
            }
        }
        return smallestPair;
    }

    public static void main(String[] args) {
        int[] expected = {28, 26};
        int[] actualResult = smallerDifference(new int[]{-1, 5, 10, 20, 28, 3}, new int[]{26, 134, 135, 15, 17});
        Arrays.equals(expected, actualResult);
    }
}
