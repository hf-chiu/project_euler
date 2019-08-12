
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem38 {

    /** Pandigital Multiples */
    public static void main(String[] args) {
        int maximum = 918273645;

        ArrayList<Integer> possibilities = new ArrayList<>();
        for (int i = 9000; i < 10000; i += 1) {
            int curr = concatenate(i, (i * 2));
            if (is_pandigital(curr)) {
                possibilities.add(curr);
            }
        }

        for (int poss : possibilities) {
            if (poss > maximum) {
                maximum = poss;
            }
        }

        System.out.println(maximum);
    }

    /* Concantenates N1 and N2. */
    private static int concatenate(int n1, int n2) {
        String n1_string = Integer.toString(n1);
        String n2_string = Integer.toString(n2);

        String concatenated = n1_string + n2_string;

        return Integer.valueOf(concatenated);
    }

    /* Returns true if N is pandigital. */
    private static boolean is_pandigital(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 10; i += 1) {
            digits.add(i);
        }

        String[] n_string = Integer.toString(n).split("");

        if (n_string.length != 9) {
            return false;
        }

        ArrayList<Integer> n_split = new ArrayList<>();
        for (String n_digit : n_string) {
            n_split.add(Integer.valueOf(n_digit));
        }
        n_split.sort(Comparator.naturalOrder());

        for (int i = 0; i < 9; i += 1) {
            if (!n_split.get(i).equals(digits.get(i))) {
                return false;
            }
        }

        return true;
    }

}
