
/** @author Nicholas Chiu */
public class Problem10 {

    /** Find the sum of all prime numbers less than N. ARGS */
    public static void main(String[] args) {
        final int supremum = 2000000;
        int number = 1;
        long sum = 2;

        while (number < supremum) {

            if (isprime(number)) {
                sum += number;
            }

            number += 2;
        }
        System.out.println(sum);
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
        int supremum = (int) Math.sqrt((double) n) + 1;

        while (true) {
            if (n % factor == 0 && factor != n) {
                return factor;
            } else if (factor > supremum) {
                return -1;
            }

            factor += 1;
        }
    }

}

