
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem36 {

    /** Find the sum of all numbers under one million which are palindromes
     *  in base 10 AND base 2. */
    public static void main(String[] args) {
        final int supremum = 1000000;

        ArrayList<Integer> base10_palin = new ArrayList<>();
        for (int i = 1; i < supremum; i += 1) {
            if (is_palindrome(i)) {
                base10_palin.add(i);
            }
        }

        ArrayList<Integer> double_palin = new ArrayList<>();
        for (int palin : base10_palin) {
            if (is_bin_palindrome(palin)) {
                double_palin.add(palin);
            }
        }

        int sum = 0;
        for (int i : double_palin) {
            sum += i;
        }

        System.out.println(sum);
    }


    /** Returns true if N is a palindrome as binary string. Leading zeroes not allowed. */
    private static boolean is_bin_palindrome(int n) {
        String[] as_binary = Integer.toBinaryString(n).split("");
        int length = as_binary.length;
        if (as_binary[0].equals("0") || as_binary[length - 1].equals("0")) {
            return false;
        } else {

            int halflength = Math.floorDiv(length, 2);

            for (int i = 0; i < halflength; i += 1) {
                String start = as_binary[i];
                String end = as_binary[length - i - 1];

                if (!start.equals(end)) {
                    return false;
                }
            }



        }
        return true;
    }


    /** Returns true if N is a palindrome. */
    private static boolean is_palindrome(int n) {
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
