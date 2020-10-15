package array;

/**
 * Write a function that takes in an array of integers and returns the length of
 * the longest peak in the array.
 * <p>
 * <p>
 * A peak is defined as adjacent integers in the array that are <b>strictly</b>
 * increasing until they reach a tip (the highest value in the peak), at which
 * point they become <b>strictly</b> decreasing. At least three integers are required to
 * form a peak.
 * </p>
 * For example, the integers <span>1, 4, 10, 2</span> form a peak, but the
 * integers <span>4, 0, 10</span> don't and neither do the integers
 * <span>1, 2, 2, 0</span>. Similarly, the integers <span>1, 2, 3</span> don't
 * form a peak because there aren't any strictly decreasing integers after the
 * <span>3</span>.
 */
public class LongestPeek {

    public static int longestPeak(int[] array) {
        int longestPeakLength = 0;

        int i = 1;

        while (i < array.length - 1) {
            boolean isPeak = array[i - 1] < array[i] && array[i] > array[i + 1];

            if (!isPeak) {
                i++;
                continue;
            }

            int leftIndex = i - 2;
            while (leftIndex >= 0 && array[leftIndex] < array[leftIndex + 1]) {
                leftIndex -= 1;
            }

            int rightIndex = i + 2;
            while (rightIndex < array.length && array[rightIndex] < array[rightIndex - 1]) {
                rightIndex += 1;
            }

            int currentPeakLenght = rightIndex - leftIndex - 1;
            if (currentPeakLenght > longestPeakLength) {
                longestPeakLength = currentPeakLenght;
            }
            i = rightIndex;
        }
        return longestPeakLength;
    }

    public static void main(String[] args) throws Exception {
        int[] input = new int[]{1, 2, 3, 4, 5, 1};
        int expected = 6;
        int actual = longestPeak(input);
        if (expected != actual) {
            throw new Exception("NOOOO!");
        }
    }
}
