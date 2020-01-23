package interview;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

//    public static BigDecimal getNthFib(double n) {
//        if (n == 2) {
//            return new BigDecimal(1);
//        } else if (n == 1) {
//            return new BigDecimal(0);
//        } else {
//            return getNthFib(n - 1).add(getNthFib(n - 2));
//        }
//    }

//    public static BigDecimal getNthFib(int n) {
//        Map<Integer, BigDecimal> mem = new HashMap<>();
//
//        mem.put(1, new BigDecimal(0));
//        mem.put(2, new BigDecimal(1));
//        return getNthFib(n, mem);
//    }
//
//    private static BigDecimal getNthFib(int n, Map<Integer, BigDecimal> mem) {
//        if (!mem.containsKey(n)) {
//            mem.put(n, getNthFib(n - 1, mem).add(getNthFib(n - 2, mem)));
//        }
//        return mem.get(n);
//    }

    public static BigDecimal getNthFib(int n) {
        BigDecimal[] lastTwo = {new BigDecimal(0), new BigDecimal(1)};
        int counter = 3;

        while (counter <= n) {
            BigDecimal nexFib = lastTwo[0].add(lastTwo[1]);
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = nexFib;
            counter++;
        }
        return n > 1 ? lastTwo[1] : lastTwo[0];
    }
}
