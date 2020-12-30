package bts;

import bts.closevalue.CloseValueIterative;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CloseValueTest {
    public BST test;

    public CloseValueTest() {
        this.test = new BST(100, new CloseValueIterative());
        this.test
                .insert(5)
                .insert(15)
                .insert(5)
                .insert(2)
                .insert(1)
                .insert(22)
                .insert(1)
                .insert(1)
                .insert(3)
                .insert(1)
                .insert(1)
                .insert(502)
                .insert(55000)
                .insert(204)
                .insert(205)
                .insert(207)
                .insert(206)
                .insert(208)
                .insert(203)
                .insert(-51)
                .insert(-403)
                .insert(1001)
                .insert(57)
                .insert(60)
                .insert(4500);
    }

    @Test
    public void TestCase1() {
        assertTrue(this.test.findClosestValueInBst(100) == 100);
    }

    @Test
    public void TestCase2() {
        assertTrue(this.test.findClosestValueInBst(208) == 208);
    }

    @Test
    public void TestCase3() {
        assertTrue(this.test.findClosestValueInBst(4500) == 4500);
    }

    @Test
    public void TestCase4() {
        assertTrue(this.test.findClosestValueInBst(4501) == 4500);
    }

    @Test
    public void TestCase5() {
        assertTrue(this.test.findClosestValueInBst(-70) == -51);
    }

    @Test
    public void TestCase6() {
        assertTrue(this.test.findClosestValueInBst(2000) == 1001);
    }

    @Test
    public void TestCase7() {
        assertTrue(this.test.findClosestValueInBst(6) == 5);
    }

    @Test
    public void TestCase8() {
        assertTrue(this.test.findClosestValueInBst(30000) == 55000);
    }

    @Test
    public void TestCase9() {
        assertTrue(this.test.findClosestValueInBst(-1) == 1);
    }

    @Test
    public void TestCase10() {
        assertTrue(this.test.findClosestValueInBst(29751) == 55000);
    }

    @Test
    public void TestCase11() {
        assertTrue(this.test.findClosestValueInBst(29749) == 4500);
    }

}
