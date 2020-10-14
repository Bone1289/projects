package array;

import java.util.Arrays;

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
