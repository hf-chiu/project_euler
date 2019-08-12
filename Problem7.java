
/** @author Nicholas Chiu */
public class Problem7 {

    /** Find the Nth prime number. ARGS */
    public static void main(String[] args) {
        final int supremum = 10001;
        int count = 0;
        int number = 1;

        while (count < supremum) {

            if (isprime(number)) {
                count += 1;
            }

            number += 1;
        }
        System.out.println(number - 1);
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
