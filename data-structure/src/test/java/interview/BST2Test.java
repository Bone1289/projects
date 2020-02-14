package interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BST2Test {
    private static BSTV2 test1;
    private static BSTV2 test2;
    private static BSTV2 test3;
    private static BSTV2 test4;

    static {
        test1 = new BSTV2(10);
        test1.insert(5).insert(15).insert(5).insert(2).insert(14).insert(22);
        test2 = new BSTV2(10);
        test2.insert(15).insert(11).insert(22).remove(10);
        test3 = new BSTV2(10);
        test3.insert(5).insert(7).insert(2).remove(10);
        test4 = new BSTV2(10);
        test4
                .insert(5)
                .insert(15)
                .insert(22)
                .insert(17)
                .insert(34)
                .insert(7)
                .insert(2)
                .insert(5)
                .insert(1)
                .insert(35)
                .insert(27)
                .insert(16)
                .insert(30)
                .remove(22)
                .remove(17);
    }

    private void inOrderTraverse(BSTV2 tree, List<Integer> array) {
        if (tree.left != null) {
            this.inOrderTraverse(tree.left, array);
        }
        array.add(tree.value);
        if (tree.right != null) {
            this.inOrderTraverse(tree.right, array);
        }
    }

    private boolean compare(List<Integer> array1, List<Integer> array2) {
        if (array1.size() != array2.size()) {
            return false;
        }
        for (int i = 0; i < array1.size(); i++) {
            if (!array1.get(i).equals(array2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void TestCase1() {
        assertEquals(5, test1.left.value);
    }

    @Test
    public void TestCase2() {
        assertEquals(22, test1.right.right.value);
    }

    @Test
    public void TestCase3() {
        assertEquals(14, test1.right.left.value);
    }

    @Test
    public void TestCase4() {
        assertEquals(5, test1.left.right.value);
    }

    @Test
    public void TestCase5() {
        assertEquals(2, test1.left.left.value);
    }

    @Test
    public void TestCase6() {
        assertNull(test1.left.left.left);
    }

    @Test
    public void TestCase7() {
        assertNull(test1.right.right.right);
    }

    @Test
    public void TestCase8() {
        assertTrue(test1.contains(15));
    }

    @Test
    public void TestCase9() {
        assertTrue(test1.contains(2));
    }

    @Test
    public void TestCase10() {
        assertTrue(test1.contains(5));
    }

    @Test
    public void TestCase11() {
        assertTrue(test1.contains(10));
    }

    @Test
    public void TestCase12() {
        assertTrue(test1.contains(22));
    }

    @Test
    public void TestCase13() {
        assertFalse(test1.contains(23));
    }

    @Test
    public void TestCase14() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(11, 15, 22));
        List<Integer> output = new ArrayList<>();
        this.inOrderTraverse(test2, output);
        assertTrue(this.compare(output, expected));
    }

    @Test
    public void TestCase15() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 5, 7));
        List<Integer> output = new ArrayList<>();
        this.inOrderTraverse(test3, output);
        assertTrue(this.compare(output, expected));
    }

    @Test
    public void TestCase16() {
        List<Integer> expected =
                new ArrayList<>(Arrays.asList(1, 2, 5, 5, 7, 10, 15, 16, 27, 30, 34, 35));
        List<Integer> output = new ArrayList<>();
        this.inOrderTraverse(test4, output);
        assertTrue(this.compare(output, expected));
    }

    @Test
    public void TestCase17() {
        assertEquals(27, test4.right.right.value);
    }

    @Test
    public void TestCase18() {
        assertEquals(16, test4.right.right.left.value);
    }

}