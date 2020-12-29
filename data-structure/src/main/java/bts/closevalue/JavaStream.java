package bts.closevalue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class JavaStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(list.stream()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer > 5;
                    }
                })
                .count());
    }

}
