
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem4 {

    /** Find the largest palindrome formed from the product of
     *  two N digit numbers. ARGS */
    public static void main(String[] args) {
        final int digits = 3;
        int max = (int) Math.pow(10, digits);
        int largest = 0;

        int list1;
        int list2;
        list1 = list2 = (max - 1);

        while (list1 > 1) {
            int curr = list1 * list2;

            if (curr <= largest || list2 == 1) {
                list1 -= 1;
                list2 = list1;
            } else if (ispalindrome(curr)) {
                largest = curr;
                list2 -= 1;
            } else {
                list2 -= 1;
            }
        }

        System.out.println(largest);
    }

    /** Returns true if N is a palindrome. */
    private static boolean ispalindrome(int n) {
        if (n < 10) {
            return true;
        }

        ArrayList<Integer> digits = new ArrayList<Integer>();

        while (n > 10) {
            digits.add(Math.floorMod(n, 10));
            n = Math.floorDiv(n, 10);
        }
        digits.add(n);

        int length = digits.size();
        int halflength = Math.floorDiv(length, 2);

        for (int i = 0; i < halflength; i += 1) {
            int start = digits.get(i);
            int end = digits.get(length - i - 1);

            if (start != end) {
                return false;
            }
        }

        return true;
    }
}
