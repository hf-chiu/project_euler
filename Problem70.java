
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem70 {

    /** Totient Permutation. */
    public static void main(String[] args) {
        final int supremum = 10000000;

        ArrayList<int[]> totient_permutations = new ArrayList<>();

        for (int i = 2; i < supremum; i += 1) {
            System.out.println(i);
            if (!Helpers.isprime(i)) {
                int phi_i = phi(i);
                String[] i_str = Integer.toString(i).split("");
                ArrayList<String> i_arr = new ArrayList<>(Arrays.asList(i_str));
                i_arr.sort(Comparator.naturalOrder());
                String[] phi_i_str = Integer.toString(phi_i).split("");
                ArrayList<String> phi_i_arr = new ArrayList<>(Arrays.asList(phi_i_str));
                phi_i_arr.sort(Comparator.naturalOrder());
                if (phi_i_arr.equals(i_arr)) {
                    int[] to_store = new int[]{i, phi_i};
                    totient_permutations.add(to_store);
                }
            }
        }

        for (int[] t :totient_permutations) {
            System.out.println(Arrays.toString(t));
        }
        System.out.println(totient_permutations.size());


    }


    /** Generates the totient values for N. */
    private static int phi(int n) {

        ArrayList<Integer> curr_divisors = Helpers.decomposer(n);
        HashSet<Integer> curr_div_set = new HashSet<>(curr_divisors);
        float coprime = n;

        for (int div : curr_div_set) {
            coprime *= (1 - ((float) 1 / (float) div));
        }

        return (int) Math.ceil(coprime);

    }
}