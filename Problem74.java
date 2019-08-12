

/** @author Nicholas Chiu */
public class Problem74 {

    /** Digit Factorial Chains. */
    public static void main(String[] args) {
        final long supremum = 1000000;

        long[] factorial = new long[10];
        for (int i = 0; i < 10; i += 1) {
            if (i == 0) {
                factorial[i] = 1;
            } else {
                long i_factorial = 1;
                for (long j = 1; j <= i; j += 1) {
                    i_factorial *= j;
                }
                factorial[i] = i_factorial;
            }
        }

        long num_chains = 0;
        for (long i = 1; i < supremum; i += 1) {

            long chain_size = 1;
            long curr = i;

            while (true) {

                if (curr == 169 || curr == 363601 || curr == 1454) {
                    chain_size += 2;
                    break;
                } else if (curr == 871 || curr == 45361) {
                    chain_size += 1;
                    break;
                } else if (curr == 872 || curr == 45362) {
                    chain_size += 1;
                    break;
                }

                long next = 0;
                long curr_copy = curr;
                while (curr_copy != 0) {
                    next += factorial[(int) curr_copy % 10];
                    curr_copy /= 10;
                }

                if (next == curr) {
                    break;
                }
                chain_size += 1;
                curr = next;
            }


            if (chain_size == 60) {
                num_chains += 1;
            }
        }

        System.out.println(num_chains);
    }

}
