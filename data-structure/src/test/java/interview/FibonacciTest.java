package interview;

import interview.Fibonacci;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

    @Test
    public void getFibonacci1() {
        assertEquals(Fibonacci.getNthFib(1), new BigDecimal(0));
    }

    @Test
    public void getFibonacci2() {
        assertEquals(Fibonacci.getNthFib(2), new BigDecimal(1));
    }

    @Test
    public void getFibonacci3() {
        assertEquals(Fibonacci.getNthFib(3), new BigDecimal(1));
    }

    @Test
    public void getFibonacci4() {
        assertEquals(Fibonacci.getNthFib(4), new BigDecimal(2));
    }

    @Test
    public void getFibonacci5() {
        assertEquals(Fibonacci.getNthFib(5), new BigDecimal(3));
    }

    @Test
    public void getFibonacci100() {
        assertEquals(Fibonacci.getNthFib(100), new BigDecimal("218922995834555169026"));
    }
}
