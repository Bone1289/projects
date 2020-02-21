package interview;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeCheckTest {
    @Test
    public void TestCase1() {
        assertTrue(PalindromeCheck.isPalindrome("a"));
    }

    @Test
    public void TestCase2() {
        assertFalse(PalindromeCheck.isPalindrome("ab"));
    }

    @Test
    public void TestCase3() {
        assertTrue(PalindromeCheck.isPalindrome("aba"));
    }

    @Test
    public void TestCase4() {
        assertFalse(PalindromeCheck.isPalindrome("abb"));
    }

    @Test
    public void TestCase5() {
        assertTrue(PalindromeCheck.isPalindrome("abba"));
    }

    @Test
    public void TestCase6() {
        assertTrue(PalindromeCheck.isPalindrome("abcdcba"));
    }

    @Test
    public void TestCase7() {
        assertTrue(PalindromeCheck.isPalindrome("abcdefghhgfedcba"));
    }

    @Test
    public void TestCase8() {
        assertTrue(PalindromeCheck.isPalindrome("abcdefghihgfedcba"));
    }

    @Test
    public void TestCase9() {
        assertFalse(PalindromeCheck.isPalindrome("abcdefghihgfeddcba"));
    }
}