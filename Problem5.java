
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/** @author Nicholas Chiu */
public class Problem5 {

    /** Find the smallest number evenly divisible by all the numbers
     *  from 1 to SUPREMUM. ARGS */
    public static void main(String[] args) {
        int result = 1;
        final int supremum = 20;

        HashMap<Integer, Integer> allfactors = new HashMap<>();

        for (int i = 2; i <= supremum; i += 1) {
            ArrayList<Integer> currfactors = decomposer(i);
            int index = 0;
            int count = 0;
            int length = currfactors.size();
            int currvalue = currfactors.get(0);

            while (index < length) {
                int value = currfactors.get(index);

                if (value == currvalue) {

                    count += 1;

                } else {

                    if (!allfactors.containsKey(currvalue)) {
                        allfactors.put(currvalue, count);
                    } else if (count > allfactors.get(currvalue)) {
                        allfactors.put(currvalue, count);
                    }

                    count = 0;
                    currvalue = value;
                }

                index += 1;

                if (index == length) {
                    if (!allfactors.containsKey(currvalue)) {
                        allfactors.put(currvalue, count);
                    } else if (count > allfactors.get(currvalue)) {
                        allfactors.put(currvalue, count);
                    }

                }
            }
        }

        Set<Integer> keys = allfactors.keySet();

        for (int key : keys) {
            result *= Math.pow(key, allfactors.get(key));
        }

        System.out.println(result);
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
