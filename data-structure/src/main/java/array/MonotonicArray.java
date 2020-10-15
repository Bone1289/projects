package array;

/**
 * Write a function that takes in an array of integers and returns a boolean
 * representing whether the array is monotonic.
 * <p>
 * An array is said to be monotonic if its elements, from left to right, are
 * entirely non-increasing or entirely non-decreasing.
 * <p>
 * Non-increasing elements aren't necessarily exclusively decreasing; they simply
 * don't increase. Similarly, non-decreasing elements aren't necessarily
 * exclusively increasing; they simply don't decrease.
 * <p>
 * Note that empty arrays and arrays of one element are monotonic.
 */
public class MonotonicArray {

    public static boolean isMonotonic(int[] array) {
        boolean isNonDecresing = true;
        boolean isNonIncressing = true;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                isNonDecresing = false;
            }
            if (array[i] > array[i - 1])
                isNonIncressing = false;
        }
        return isNonDecresing || isNonIncressing;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        boolean actual = isMonotonic(array);
        if (!actual) {
            throw new Exception("Exception");
        }
    }
}
