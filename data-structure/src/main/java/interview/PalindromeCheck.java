package interview;

public class PalindromeCheck {

//    public static boolean isPalindrome(String str) {
//        String reverseString = "";
//
//        for (int i = str.length() - 1; i >= 0; i--) {
//            reverseString += str.charAt(i);
//        }
//        return str.equals(reverseString);
//    }

//    public static boolean isPalindrome(String str) {
//        StringBuilder reverseString = new StringBuilder();
//
//        for (int i = str.length() - 1; i >= 0; i--) {
//            reverseString.append(str.charAt(i));
//        }
//        return str.equals(reverseString.toString());
//    }

//    public static boolean isPalindrome(String str) {
//        return isPalindrome(str, 0);
//    }
//
//    private static boolean isPalindrome(String str, int i) {
//        int j = str.length() - 1 - i;
//        return i >= j || str.charAt(i) == str.charAt(j) && isPalindrome(str, i + 1);
//    }

    public static boolean isPalindrome(String str) {
        int leftIdx = 0;
        int rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                return false;
            }
            leftIdx++;
            rightIdx--;
        }
        return true;
    }

}
