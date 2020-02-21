package interview;

public class CaesarCrypherEncryptor {

//    public static String caesarCypherEncryptor(String str, int key) {
//        char[] newLetters = new char[str.length()];
//        int newKey = key % 26;
//
//        for (int i = 0; i < str.length(); i++) {
//            newLetters[i] = getNewLetter(str.charAt(i), newKey);
//        }
//        return new String(newLetters);
//    }
//
//    private static char getNewLetter(char letter, int key) {
//        int newLetterCode = letter + key;
//        return newLetterCode <= 122 ? (char) newLetterCode : (char) (96 + newLetterCode % 122);
//    }


    public static String caesarCypherEncryptor(String str, int key) {
        char[] newLetters = new char[str.length()];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int newKey = key % alphabet.length();
        for (int i = 0; i < str.length(); i++) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey, alphabet);
        }
        return new String(newLetters);
    }

    private static char getNewLetter(char letter, int key, String alphabet) {
        int newLetterCode = alphabet.indexOf(letter) + key;
        int size = alphabet.length() - 1;
        return newLetterCode <= size ? alphabet.charAt(newLetterCode) : alphabet.charAt(-1 + newLetterCode % size);
    }
}
