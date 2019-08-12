
/** @author Nicholas Chiu */
public class Problem12 {

    /** Find the smallest triangle number with over N divisors. ARGS */
    public static void main(String[] args) {
        final int N = 500;
        long triangle = 0;
        long step = 1;

        while (true) {
            triangle += step;
            step += 1;
            if (divisors(triangle) > N) {
                System.out.println(triangle);
                break;
            }
        }
    }

    /** Returns the numnber of divisors of NUMBER. */
    private static int divisors(long number) {

        if (number == 1) {
            return 1;
        } else if (number == 2) {
            return 2;
        }

        long supremum = (long) Math.ceil(Math.sqrt(number));
        int count = 0;
        long divisor = 2;

        while (divisor < supremum) {
            if (number % divisor == 0) {
                count += 1;
            }
            divisor += 1;
        }

        count *= 2;

        if (Math.pow(divisor, 2) == number) {
            count += 1;
        }

        count += 2;

        return count;
    }
}
