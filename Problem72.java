
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem72 {

    /** Counting Fractions. */
    public static void main(String[] args) {
        final int supremum = 1000000;

        System.out.println(num_rpf(supremum));


    }


    /** Returns the number of reduced proper fractions with denom <= N. */
    private static long num_rpf(int n) {

        long sum = 0;
        for (int curr = 2; curr <= n; curr += 1) {

            ArrayList<Integer> curr_divisors = Helpers.decomposer(curr);
            HashSet<Integer> curr_div_set = new HashSet<>(curr_divisors);
            int coprime = curr;

            for (int div : curr_div_set) {
                coprime = coprime / div;
                coprime *= (div - 1);
            }

            sum += coprime;
        }

        return sum;
    }
}