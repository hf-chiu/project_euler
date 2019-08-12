
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem27 {

    /** Quadratic Primes */
    public static void main(String[] args) {
        final int supremum = 10001;
        int count = 0;

        System.out.println("New Primes Found: Round 0 || Combinations Remaining: 1000000");


        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1; i <= 1000; i += 1) {
            if (isprime(i)) {
                primes.add(i);
                count += 1;
            }
        }
        System.out.println(primes);
        ArrayList<ArrayList<Integer>> candidates = new ArrayList<>();
        for (int i : primes) {
            ArrayList<Integer> temp  = new ArrayList<>();
            for (int j = -1000; j < 1000; j += 1) {
                if (primes.contains(i + j + 1)) {
                    temp.add(j);
                } else if (isprime(i + j + 1)) {
                    temp.add(j);
                }
            }
            candidates.add(temp);
        }
        int c = 0;
        for (ArrayList<Integer> lst : candidates) {
            for (int i : lst) {
                c += 1;
            }
        }
        System.out.println("New Primes Found: Round 1" + " || Combinations Remaining: " + c);

        int count1 = 0;
        int n = 2;
        while (count1 != 1) {

            count = primes.size();
            ArrayList<Integer> to_remove = new ArrayList<>();
            for (int index = 0; index < count; index += 1) {
                int b = primes.get(index);
                ArrayList<Integer> new_temp = new ArrayList<>();
                for (int a : candidates.get(index)) {
                    if (isprime((long) Math.pow(n, 2) + (a * n) + b)) {
                        new_temp.add(a);
                    }
                }
                if (new_temp.size() == 0) {
                    to_remove.add(index);
                }
                candidates.set(index, new_temp);
            }
            System.out.print("New Primes Found: Round " + n);
            for (int i = to_remove.size() - 1; i >= 0; i -= 1) {
                int ind = to_remove.get(i);
                primes.remove(ind);
                candidates.remove(ind);
            }

            count1 = 0;
            for (ArrayList<Integer> lst : candidates) {
                for (int i : lst) {
                    count1 += 1;
                }
            }
            System.out.print(" || Combinations Remaining: " + count1);
            System.out.println();
            n += 1;
        }
        System.out.println("a = " + primes.get(0) + ", b = " + candidates.get(0).get(0) +
                ", product = " + (primes.get(0) * candidates.get(0).get(0)));
    }

    /** Returns true if N is a prime. */
    private static boolean isprime(long n) {

        if (n <= 1) {
            return false;
        } else if (n == 2) {
            return true;
        } else {
            int factor = smallestfactor(n);
            return factor == -1;
        }
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
