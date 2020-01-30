package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    static final int LOOPS = 100;

    @Before
    public void setup() {
    }

    @Test
    public void testIsEmpty() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testSize() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertSame(tree.size(), 0);

        tree.add("Hello World!");
        assertSame(tree.size(), 1);
    }

    @Test
    public void testHeight() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        // Tree should look like:
        //        M
        //      J  S
        //    B   N Z
        //  A

        // No tree
        assertEquals(tree.height(), 0);

        // Layer One
        tree.add("M");
        assertEquals(tree.height(), 1);

        // Layer Two
        tree.add("J");
        assertEquals(tree.height(), 2);
        tree.add("S");
        assertEquals(tree.height(), 2);

        // Layer Three
        tree.add("B");
        assertEquals(tree.height(), 3);
        tree.add("N");
        assertEquals(tree.height(), 3);
        tree.add("Z");
        assertEquals(tree.height(), 3);

        // Layer 4
        tree.add("A");
        assertEquals(tree.height(), 4);
    }

    @Test
    public void testAdd() {

        // Add element which does not yet exist
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        assertTrue(tree.add('A'));

        // Add duplicate element
        assertFalse(tree.add('A'));

        // Add a second element which is not a duplicate
        assertTrue(tree.add('B'));
    }

    @Test
    public void testRemove() {

        // Try removing an element which doesn't exist
        BinarySearchTree<Character> tree = new BinarySearchTree<>();
        tree.add('A');
        assertEquals(tree.size(), 1);
        assertFalse(tree.remove('B'));
        assertEquals(tree.size(), 1);

        // Try removing an element which does exist
        tree.add('B');
        assertEquals(tree.size(), 2);
        assertTrue(tree.remove('B'));
        assertEquals(tree.size(), 1);
        assertEquals(tree.height(), 1);

        // Try removing the root
        assertTrue(tree.remove('A'));
        assertEquals(tree.size(), 0);
        assertEquals(tree.height(), 0);
    }

    @Test
    public void testContains() {

        // Setup tree
        BinarySearchTree<Character> tree = new BinarySearchTree<>();

        tree.add('B');
        tree.add('A');
        tree.add('C');

        // Try looking for an element which doesn't exist
        assertFalse(tree.contains('D'));

        // Try looking for an element which exists in the root
        assertTrue(tree.contains('B'));

        // Try looking for an element which exists as the left child of the root
        assertTrue(tree.contains('A'));

        // Try looking for an element which exists as the right child of the root
        assertTrue(tree.contains('C'));
    }

    @Test
    public void randomRemoveTests() {

        for (int i = 0; i < LOOPS; i++) {

            int size = i;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            List<Integer> lst = genRandList(size);
            for (Integer value : lst) tree.add(value);

            Collections.shuffle(lst);
            // Remove all the elements we just placed in the tree
            for (int j = 0; j < size; j++) {

                Integer value = lst.get(j);

                assertTrue(tree.remove(value));
                assertFalse(tree.contains(value));
                assertEquals(tree.size(), size - j - 1);
            }

            assertTrue(tree.isEmpty());
        }

    }

    static List<Integer> genRandList(int sz) {
        List<Integer> lst = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) lst.add(i);
        Collections.shuffle(lst);
        return lst;
    }

}
