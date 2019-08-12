
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem37 {

    /** Truncatable Primes. */
    public static void main(String[] args) {

        ArrayList<Long> truncatable = truncatable();

        long sum = 0;
        for (long n : truncatable) {
            sum += n;
        }

        System.out.println(sum);
    }

    /** Returns all the left truncatable primes indexed by number of digits. */
    private static ArrayList<Long> truncatable() {

        ArrayList<ArrayList<Long>> left_trunc = new ArrayList<>();
        ArrayList<ArrayList<Long>> right_trunc = new ArrayList<>();
        ArrayList<Long> blank = new ArrayList<>();
        blank.add((long) -1);
        left_trunc.add(blank);
        right_trunc.add(blank);

        boolean exists_more = true;
        int num_digits = 1;
        int overlap = 0;

        ArrayList<Long> possibilities = new ArrayList<>();

        while (exists_more) {

            ArrayList<Long> prev_ltrunc_primes = left_trunc.get(num_digits - 1);
            ArrayList<Long> curr_ltrunc_primes = new ArrayList<>();
            for (long prev_prime : prev_ltrunc_primes) {
                for (long i = 1; i < 10; i += 1) {
                    long poss = concatenate(i, prev_prime);
                    if (isprime(poss)) {
                        curr_ltrunc_primes.add(poss);
                    }
                }
            }
            left_trunc.add(curr_ltrunc_primes);

            ArrayList<Long> prev_rtrunc_primes = right_trunc.get(num_digits - 1);
            ArrayList<Long> curr_rtrunc_primes = new ArrayList<>();
            for (long prev_prime : prev_rtrunc_primes) {
                for (long i = 1; i < 10; i += 1) {
                    long poss = concatenate(prev_prime, i);
                    if (isprime(poss)) {
                        curr_rtrunc_primes.add(poss);
                    }
                }
            }
            right_trunc.add(curr_rtrunc_primes);


            ArrayList<Long> curr_possibilities = new ArrayList<>();
            for (long left : curr_ltrunc_primes) {
                for (long right : curr_rtrunc_primes) {
                    long temp = concatenate_if_overlap(right, left, overlap);
                    if (temp != -1 && isprime(temp)) {
                        curr_possibilities.add(temp);
                    }
                }
            }


            if (curr_ltrunc_primes.size() == 0 || curr_rtrunc_primes.size() == 0) {
                exists_more = false;
            } else {
                possibilities.addAll(curr_possibilities);
            }

            num_digits += 1;
            overlap += 1;
        }

        return possibilities;
    }



    /* Concantenates N1 and N2 iff they have an overlap of OVERLAP digits*/
    private static long concatenate_if_overlap(long n1, long n2, int overlap) {
        String n1_string = Long.toString(n1);
        String n2_string = Long.toString(n2);

        String concatenated;
        if (n1_string.substring(n1_string.length() - overlap).equals(n2_string.substring(0, overlap))) {
            concatenated = n1_string + n2_string.substring(overlap);
        } else {
            concatenated = "-1";
        }

        return Long.valueOf(concatenated);
    }


    /* Concantenates N1 and N2. */
    private static long concatenate(long n1, long n2) {
        if (n1 < 0) {
            return n2;
        } else if (n2 < 0) {
            return n1;
        }

        String n1_string = Long.toString(n1);
        String n2_string = Long.toString(n2);

        String concatenated = n1_string + n2_string;

        return Long.valueOf(concatenated);
    }


    /** Returns true if N is a prime. */
    private static boolean isprime(long n) {

        if (n == 1) {
            return false;
        } else if (n == 2) {
            return true;
        } else {
            long factor = smallestfactor(n);
            return factor == -1;
        }
    }

    /** Returns the smallest factor of N. */
    private static long smallestfactor(long n) {

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
