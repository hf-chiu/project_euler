
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem41 {

    /** Pandigital Multiples */
    public static void main(String[] args) {

        ArrayList<Integer> digits = new ArrayList<>();
        ArrayList<Integer> pandigitals = new ArrayList<>();
        for (int i = 1; i < 10; i += 1) {
            digits.add(i);
            ArrayList<ArrayList<Integer>> permutations = lexicographic_permuter(i, digits);
            for (ArrayList<Integer> permutation : permutations) {
                pandigitals.add(converter_toint(permutation));
            }
        }

        ArrayList<Integer> prime_pandigitals  = new ArrayList<>();
        for (int pandigital : pandigitals) {
            if (isprime(pandigital)) {
                prime_pandigitals.add(pandigital);
            }
        }

        int max = 0;
        for (int poss: prime_pandigitals) {
            if (poss > max) {
                max = poss;
            }
        }

        System.out.println(max);

    }


    /* Creates a list of lexicographic permutations of length LEN from list LST. */
    private static ArrayList<ArrayList<Integer>> lexicographic_permuter(int len, ArrayList<Integer> lst) {

        if (len > lst.size()) {
            return null;
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (len == 1) {


            for (int elem : lst) {
                ArrayList<Integer> elem_arrayed = new ArrayList<>();
                elem_arrayed.add(elem);
                ret.add(elem_arrayed);
            }


        } else {


            for (int elem : lst) {
                ArrayList<Integer> rec_lst = new ArrayList<>();
                rec_lst.addAll(lst);
                rec_lst.remove(Integer.valueOf(elem));
                ArrayList<ArrayList<Integer>> rec_ret = lexicographic_permuter(len - 1, rec_lst);

                for (ArrayList<Integer> remainder : rec_ret) {
                    ArrayList<Integer> perm = new ArrayList<>();
                    perm.add(elem);
                    perm.addAll(remainder);
                    ret.add(perm);
                }
            }


        }
        return ret;
    }

    /**  Converts a number N from a list of digits into an int. */
    private static int converter_toint(ArrayList<Integer> N) {
        StringBuilder num_sb = new StringBuilder();
        for (int n : N) {
            num_sb.append(n);
        }
        return Integer.valueOf(num_sb.toString());
    }


    /** Returns true if N is a prime. */
    private static boolean isprime(long n) {

        if (n == 1) {
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



