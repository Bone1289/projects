import linkedlist.DoublyLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoublyLinkedListTest {

    static final int LOOPS = 10000;
    static final int TEST_SZ = 40;
    static final int NUM_NULLS = TEST_SZ / 5;
    static final int MAX_RAND_NUM = 250;

    DoublyLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testEmptyList() {
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

//    @Test(expected = Exception.class)
//    public void testRemoveFirstOfEmpty() {
//        list.removeFirst();
//    }
//
//    @Test(expected = Exception.class)
//    public void testRemoveLastOfEmpty() {
//        list.removeLast();
//    }

    @Test
    public void testAddFirst() {
        list.addFirst(3);
        assertEquals(list.size(), 1);
        list.addFirst(5);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testAddLast() {
        list.addLast(3);
        assertEquals(list.size(), 1);
        list.addLast(5);
        assertEquals(list.size(), 2);
    }
}
