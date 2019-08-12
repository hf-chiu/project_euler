
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem71 {

    /** Ordered Fractions. */
    public static void main(String[] args) {
        final int supremum = 1000000;
        final int ceil_num = 3;
        final int ceil_denom = 7;

        int max_num = 0;
        int max_denom = 1;
        double max_val = (double) max_num / (double) max_denom;
        for (int denom = 2; denom <= supremum; denom += 1) {
            int num = rpf_less_then(denom, ceil_num, ceil_denom);
            double val = (double) num / (double) denom;

            if (val > max_val) {
                max_num = num;
                max_denom = denom;
                max_val = val;
            }
        }

        System.out.println(max_num + " / " + max_denom);

    }


    /** Returns the numerator of the reduced proper fraction with denom N that is
     *  immediately less than CEIL.*/
    private static int rpf_less_then(int n, int ceil_num, int ceil_denom) {

        int ceil = (int) Math.floor((n * ceil_num) / (float) ceil_denom);
        if (n % ceil_denom == 0) {
            ceil -= 1;
        }
        if (ceil != 0) {

            ArrayList<Integer> curr_divisors = Helpers.decomposer(n);
            HashSet<Integer> curr_div_set = new HashSet<>(curr_divisors);

            int num = ceil;
            while (true) {

                boolean found = true;

                ArrayList<Integer> num_divisors = Helpers.decomposer(num);
                for (int d : num_divisors) {
                    if (curr_div_set.contains(d)) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    break;
                }
                num -= 1;
            }

            return num;
        } else {
            return 0;
        }

    }
}