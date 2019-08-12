
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem29 {

    /** Distinct Powers. */
    public static void main(String[] args) {
        final int N = 100;

        ArrayList<ArrayList<Integer>> decomposed = new ArrayList<>();
        for (int i = 2; i <= N; i += 1) {
            decomposed.add(decomposer(i));
        }

        HashSet<ArrayList<Integer>> powers = new HashSet<>();

        for (ArrayList<Integer> num : decomposed) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(num);
            for (int i = 2; i <= N; i += 1) {
                temp.addAll(num);
                ArrayList<Integer> temp2 = new ArrayList<>();
                temp2.addAll(temp);
                temp2.sort(Comparator.naturalOrder());
                powers.add(temp2);
            }
        }
        System.out.println(powers.size());

    }

    /** Returns an ArrayList of prime factors of N. */
    private static ArrayList<Integer> decomposer(long n) {
        if (n == 1) {
            ArrayList<Integer> i =  new ArrayList<>();
            i.add(1);
            return i;
        }

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
