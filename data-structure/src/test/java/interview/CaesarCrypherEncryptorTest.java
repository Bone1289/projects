package interview;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarCrypherEncryptorTest {

    @Test
    public void TestCase1() {
        assertEquals(CaesarCrypherEncryptor.caesarCypherEncryptor("abc", 0), "abc");
    }

    @Test
    public void TestCase2() {
        assertEquals(CaesarCrypherEncryptor.caesarCypherEncryptor("abc", 3), "def");
    }

    @Test
    public void TestCase3() {
        assertEquals(CaesarCrypherEncryptor.caesarCypherEncryptor("xyz", 2), "zab");
    }

    @Test
    public void TestCase4() {
        assertEquals("cde", CaesarCrypherEncryptor.caesarCypherEncryptor("xyz", 5));
    }

    @Test
    public void TestCase5() {
        assertEquals("abc", CaesarCrypherEncryptor.caesarCypherEncryptor("abc", 26));
    }

    @Test
    public void TestCase6() {
        assertEquals("abc", CaesarCrypherEncryptor.caesarCypherEncryptor("abc", 52));
    }

    @Test
    public void TestCase7() {
        assertEquals("fgh", CaesarCrypherEncryptor.caesarCypherEncryptor("abc", 57));
    }
}