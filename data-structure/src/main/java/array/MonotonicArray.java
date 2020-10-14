package array;

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
