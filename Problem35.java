
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;


/** @author Nicholas Chiu */
public class Problem35 {

    /** Circular  Primes */
    public static void main(String[] args) {
        final int N = 1000000;

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1; i < N ; i += 1) {
            if (isprime(i)) {
                primes.add(i);
            }
        }

        ArrayList<Integer> expanded = shift_expander(primes);
        BitSet exp_bs= new BitSet(N + 1);
        exp_bs.clear();
        for (int num : expanded) {
            exp_bs.set(num);
        }
        BitSet primes_bs = new BitSet(N + 1);
        primes_bs.clear();
        for (int prime : primes) {
            exp_bs.set(prime, false);
            primes_bs.set(prime);
        }
        ArrayList<Integer> pre_extras= new ArrayList<>();
        for (int i = 1; i < exp_bs.size(); i += 1) {
            if (exp_bs.get(i)) {
                pre_extras.add(i);
            }
        }
        ArrayList<Integer> extras = shift_expander(pre_extras);
        for (int extra : extras) {
            primes_bs.set(extra, false);
        }

        System.out.println(primes_bs.cardinality());
    }



    /** Shift expands a list of numbers. */
    private static ArrayList<Integer> shift_expander(ArrayList<Integer> lst) {
        HashSet<Integer> temp = new HashSet<>();

        for (int elem : lst) {
            ArrayList<Integer> elem_c = converter(elem);
            temp.add(elem);
            for (int i = 0; i < elem_c.size()-1; i += 1) {
                elem_c.add(elem_c.remove(0));
                if (elem_c.get(0) != 0) {
                    temp.add(converter_toint(elem_c));
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(temp);
        result.sort(Comparator.naturalOrder());
        return result;
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

    /** Converts a number N into list of digits. */
    private static ArrayList<Integer> converter(long n) {
        String str = Long.toString(n);
        String[] split = str.split("");

        ArrayList<Integer> result = new ArrayList<>();

        for (String digit : split) {
            result.add(Integer.parseInt(digit));
        }

        return result;
    }

    /**  Converts a number N from a list of digits into an int. */
    private static int converter_toint(ArrayList<Integer> N) {
        StringBuilder num_sb = new StringBuilder();
        for (int n : N) {
            num_sb.append(n);
        }
        return Integer.valueOf(num_sb.toString());
    }

}
