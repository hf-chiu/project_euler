
import java.lang.reflect.Array;

/** @author Nicholas Chiu */
public class Problem47 {

    /** Distinct Primes Factors. ARGS. */
    public static void main(String[] args) {
        final int magic_number = 4;

        int counter = 0;
        int curr = 1;

        while (counter != magic_number) {
            int num_dist_factors = Helpers.distinct_decomposer(curr).size();
            if (num_dist_factors == magic_number) {
                counter += 1;
            } else {
                counter = 0;
            }
            curr += 1;
        }

        System.out.println(curr - magic_number);
    }







}


