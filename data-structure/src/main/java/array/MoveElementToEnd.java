package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoveElementToEnd {

    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int i = 0;
        int j = array.size() - 1;

        while (i < j) {
            while (i < j && array.get(j) == toMove) j--;
            if (array.get(i) == toMove) swap(i, j, array);
            i++;
        }
        return array;
    }

    private static void swap(int i, int j, List<Integer> array) {
        int temp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, temp);
    }

    public static void main(String[] args) throws Exception {
        List<Integer> array = new ArrayList<>(Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2));
        int toMove = 2;

        List<Integer> expectedStart = new ArrayList<>(Arrays.asList(1, 3, 4));
        List<Integer> expectedEnd = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2));

        List<Integer> output = moveElementToEnd(array, toMove);
        List<Integer> outputStart = output.subList(0, 3);

        outputStart.sort(Comparator.naturalOrder());
        List<Integer> outputEnd = output.subList(3, output.size());
        if (!outputStart.equals(expectedStart)) {
            throw new Exception("No good!");
        }
        if (!outputEnd.equals(expectedEnd)) {
            throw new Exception("No good!");
        }
    }
}
