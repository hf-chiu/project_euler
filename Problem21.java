
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem21 {

    /** Find the sum of all amicable numbers under 10000. ARGS */
    public static void main(String[] args) {
        final int supremum = 10000;
        ArrayList<Integer> amicables = amicables(supremum);
        int sum = 0;

        for (int amicable : amicables) {
            sum += amicable;
        }

        System.out.println(sum);
    }

    /** Returns a list of all amicable numbers under SUPREMUM. */
    private static ArrayList<Integer> amicables(int supremum) {
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

                int oppsum;
                int oposs;
                if (dvsum < supremum) {
                    oposs = stored[dvsum - 1];
                    if (oposs != 0) {
                        oppsum = oposs;
                    } else {
                        oppsum = divisorsum(dvsum);
                        stored[dvsum - 1] = oppsum;
                    }
                } else {
                    oppsum = 0;
                }

                if (oppsum == i) {
                    if (dvsum != i) {
                        results.add(dvsum);
                        results.add(i);
                    }
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
