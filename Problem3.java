
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem3 {

    /** Find the largest prime factor of N. ARGS */
    public static void main(String[] args) {
        final long n = 600851475143L;

        ArrayList<Integer> factors = decomposer(n);
        System.out.println(factors.get(factors.size() - 1));
    }

    /** Returns an ArrayList of prime factors of N. */
    private static ArrayList<Integer> decomposer(long n) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        int factor = smallestfactor(n);

        while (factor != -1) {
            results.add(factor);
            n = n / factor;
            factor = smallestfactor(n);
        }

        if (n != 1) {
            results.add((int) n);
        }

        return results;
    }

    /** Returns the smallest factor of N. */
    private static int smallestfactor(long n) {

        int factor = 2;
        int supremum = (int) Math.sqrt((double) n);

        while (true) {
            if (n % factor == 0) {
                return factor;
            } else if (factor > supremum) {
                return -1;
            }

            factor += 1;
        }
    }
}
