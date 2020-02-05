package interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static interview.BranchSums.BinaryTree;
import static interview.BranchSums.branchSums;
import static org.junit.Assert.assertEquals;

public class BranchSumsTest {

    @Test
    public void TestCase1() {
        BinaryTree tree = new BinaryTree(1);
        List<Integer> expected = new ArrayList<>(Collections.singletonList(1));
        assertEquals(expected, branchSums(tree));
    }

    @Test
    public void TestCase2() {
        BinaryTree tree = new BinaryTree(1).insert(Collections.singletonList(2));
        List<Integer> expected = new ArrayList<>(Collections.singletonList(3));
        assertEquals(expected, branchSums(tree));
    }

    @Test
    public void TestCase3() {
        BinaryTree tree = new BinaryTree(1).insert((Arrays.asList(2, 3)));
        List<Integer> expected = new ArrayList<>(Arrays.asList(3, 4));
        assertEquals(expected, branchSums(tree));
    }

    @Test
    public void TestCase4() {
        BinaryTree tree = new BinaryTree(1).insert(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> expected = new ArrayList<>(Arrays.asList(15, 16, 18, 10, 11));
        assertEquals(expected, branchSums(tree));
    }

    @Test
    public void TestCase6() {
        BinaryTree tree = new BinaryTree(0);
        tree.left = new BinaryTree(1);
        tree.left.left = new BinaryTree(10);
        tree.left.left.left = new BinaryTree(100);
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(111));
        assertEquals(expected, branchSums(tree));
    }
    @Test
    public void TestCase8() {
        BinaryTree tree = new BinaryTree(0);
        tree.right = new BinaryTree(1);
        tree.right.right = new BinaryTree(10);
        tree.right.right.right = new BinaryTree(100);
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(111));
        assertEquals(expected, branchSums(tree));
    }
}
