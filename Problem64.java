
import java.util.ArrayList;
import java.util.Arrays;

/** @author Nicholas Chiu */
public class Problem64 {

    /** Odd Period Square Roots. */
    public static void main(String[] args) {
        final int num_convergent = 10000;

        int count = 0;
        for (int i = 2; i <= num_convergent; i += 1) {
            if (is_odd(i)) {
                count += 1;
            }
        }
        System.out.println(count);

    }

    /** Returns whether N has an odd period as a continued fraction. */
    private static boolean is_odd(int n) {
        ArrayList<Integer> rep = cont_frac(n);
        return rep.size() % 2 == 0;
    }

    /** Returns the list of digit reps of the continued fraction of N. */
    private static ArrayList<Integer> cont_frac(int n) {

        boolean is_square = Math.pow((int) Math.sqrt(n), 2) == n;
        ArrayList<Integer> ret = new ArrayList<>();

        if (!is_square) {

            int first = (int) Math.sqrt(n);
            double precise = Math.sqrt(n);
            ret.add(first);

            int[] start = new int[]{1, - first};
            int[] next = new int[]{1, - first};

            double interm = precise + (next[1]);
            int next_digit = (int) ((double) next[0] / interm);
            ret.add(next_digit);

            int num = (n - (int) Math.pow(next[1], 2)) / next[0];
            int denom = (- next[1]) - (next_digit * num);

            next[0] = num;
            next[1] = denom;

            next[0] = num;
            next[1] = denom;

            while (!Arrays.equals(next, start)) {

                interm = precise + (next[1]);
                next_digit = (int) ((double) next[0] / interm);
                ret.add(next_digit);

                num = (n - (int) Math.pow(next[1], 2)) / next[0];
                denom = (- next[1]) - (next_digit * num);

                next[0] = num;
                next[1] = denom;

            }

        } else {
            ret.add((int) Math.sqrt(n));
        }

        return ret;
    }
}