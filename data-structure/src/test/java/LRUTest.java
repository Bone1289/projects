import interview.LRUCache_algo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.Map;

import static junit.framework.TestCase.*;

public class LRUTest {
    Map<String, Integer> letterMap = new HashMap<>();
    String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    @Before
    public void setup() {
        letterMap.put("a", 0);
        letterMap.put("b", 1);
        letterMap.put("c", 2);
        letterMap.put("d", 3);
        letterMap.put("e", 4);
        letterMap.put("f", 5);
        letterMap.put("g", 6);
        letterMap.put("h", 7);
        letterMap.put("i", 8);
        letterMap.put("j", 9);
    }

    @Test
    public void TestCase1() {
        testLruSize(1);
    }

    @Test
    public void TestCase2() {
        testLruSize(2);
    }

    @Test
    public void TestCase3() {
        testLruSize(3);
    }

    @Test
    public void TestCase4() {
        testLruSize(4);
    }

    public void testLruSize(int lruSize) {
        LRUCache_algo.LRUCache lru = new LRUCache_algo.LRUCache(lruSize);
        Assert.assertFalse(lru.getValueFromKey("a").isFound());
        lru.insertKeyValuePair("a", 99);
        assertSame("a", lru.getMostRecentKey());
        assertSame(lru.getValueFromKey("a").getValue(), 99);

        lru.insertKeyValuePair("a", 0);
        assertSame("a", lru.getMostRecentKey());
        assertSame(lru.getValueFromKey("a").getValue(), 0);

        for (int i = 1; i < lruSize; i++) {
            String mostRecentLetter = letters[i - 1];
            assertSame(lru.getMostRecentKey(), mostRecentLetter);

            for (int j = 0; j < i; j++) {
                String letter = letters[j];
                assertSame(lru.getValueFromKey(letter).getValue(), letterMap.get(letter));
                assertSame(lru.getMostRecentKey(), letter);
            }

            String currentLetter = letters[i];
            Assert.assertFalse(lru.getValueFromKey(currentLetter).isFound());
            lru.insertKeyValuePair(currentLetter, letterMap.get(currentLetter));
            assertSame(lru.getMostRecentKey(), currentLetter);
            assertSame(lru.getValueFromKey(currentLetter).getValue(), letterMap.get(currentLetter));
        }

        for (int i = lruSize; i < letters.length; i++) {
            String mostRecentLetter = letters[i - 1];
            assertSame(lru.getMostRecentKey(), mostRecentLetter);

            for (int j = i - lruSize; j < i; j++) {
                String letter = letters[j];
                assertSame(lru.getValueFromKey(letter).getValue(), letterMap.get(letter));
                assertSame(lru.getMostRecentKey(), letter);
            }

            String leastRecentLetter = letters[i - lruSize];
            String currentLetter = letters[i];

            assertFalse(lru.getValueFromKey(currentLetter).isFound());
            lru.insertKeyValuePair(currentLetter, letterMap.get(currentLetter));
            assertSame(lru.getMostRecentKey(), currentLetter);
            assertSame(lru.getValueFromKey(currentLetter).getValue(), letterMap.get(currentLetter));
            assertFalse(lru.getValueFromKey(leastRecentLetter).isFound());
        }

        for (int i = letters.length - lruSize; i < letters.length; i++) {
            String currentLetter = letters[i];
            assertSame(lru.getValueFromKey(currentLetter).getValue(), letterMap.get(currentLetter));

            int newValue = (letterMap.get(currentLetter) + 1) * 100;
            lru.insertKeyValuePair(currentLetter, newValue);
            assertEquals(lru.getValueFromKey(currentLetter).getValue(), newValue);
        }
    }
}
