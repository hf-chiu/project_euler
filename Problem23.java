
import java.util.ArrayList;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem23 {

    /** Find the sum of all numbers which cannot be formed as the sum
     *  of two abundant numbers. ARGS */
    public static void main(String[] args) {
        final int supremum = 28123;
        ArrayList<Integer> abundants = abundants(supremum);
        long sum = 0;

        for (int i = 1; i <= supremum; i += 1) {
            sum += i;
        }

        HashSet<Integer> sums = new HashSet<Integer>();
        for (int abundant1 : abundants) {
            for (int abundant2 : abundants) {
                int temp = abundant1 + abundant2;
                if (temp <= 28123) {
                    sums.add(temp);
                }
            }
        }

        for (int sm : sums) {
            sum -= sm;
        }

        System.out.println(sum);

    }

    /** Returns a list of all amicable numbers under SUPREMUM. */
    private static ArrayList<Integer> abundants(int supremum) {
        ArrayList<Integer> results = new ArrayList<>();
        int[] stored = new int[supremum - 1];

        for (int i = 2; i < supremum; i += 1) {
            if (!results.contains(i)) {

                int dvsum;
                int poss = stored[i - 1];
                if (poss != 0) {
                    dvsum = poss;
                } else {
                    dvsum = divisorsum(i);
                    stored[i - 1] = dvsum;
                }

                if (dvsum > i) {
                    results.add(i);
                }
            }
        }

        return results;
    }

    /** Returns the sum of the divisors of NUMBER. Stored in stored. */
    private static int divisorsum(long number) {

        if (number == 1) {
            return 1;
        } else if (number == 2) {
            return 1;
        }

        long supremum = (long) Math.ceil(Math.sqrt(number));
        int count = 0;
        long divisor = 2;

        while (divisor < supremum) {
            if (number % divisor == 0) {
                count += divisor;
                count += number / divisor;
            }
            divisor += 1;
        }

        count += 1;

        if (Math.pow(divisor, 2) == number) {
            count += divisor;
        }

        return count;
    }
}
