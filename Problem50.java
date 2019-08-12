
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem50 {

    /** Consecutive Prime Sums. ARGS */
    public static void main(String[] args) {
        final int supremum = 1000000;

        ArrayList<Integer> primes = max_len_chain(supremum);
        int chain_length = primes.size();
        int end_ind = chain_length - 1;
        int ret = 0;

        while (ret == 0) {

            int num_chains = end_ind - chain_length + 1;
            for (int i = num_chains; i >= 0; i -= 1) {

                int sum = 0;
                for (int ind = 0; ind < chain_length; ind += 1) {
                    sum += primes.get(i + ind);
                }
                if (Helpers.isprime(sum)) {
                    ret = sum;
                }
            }

            int sum = 0;
            int due_diligence = num_chains + 1;
            for (int ind = 0; ind < chain_length - 1; ind += 1) {
                sum += primes.get(due_diligence + ind);
            }
            int next = next_prime(primes.get(primes.size() - 1));
            sum += next;
            if (sum < supremum) {
                primes.add(next);
                end_ind += 1;
                if (Helpers.isprime(sum)) {
                    ret = sum;
                }
            }

            chain_length -= 1;
        }
        chain_length += 1;

        System.out.println(chain_length);
        System.out.println(ret);


    }

    /** Returns the next prime after N. */
    private static int next_prime(int n) {
        while (true) {
            if (Helpers.isprime(n += 1)) {
                break;
            }
        }
        return n;
    }

    /** Returns the list of primes that form a maximum length of a chain
     *  of primes that sum to under supremum. */
    private static ArrayList<Integer> max_len_chain(int supremum) {
        int sum = 0;
        int curr = 1;
        ArrayList<Integer> ret = new ArrayList<>();

        while (sum < supremum) {
            if (Helpers.isprime(curr)) {
                sum += curr;
                ret.add(curr);
            }
            curr += 1;
        }

        ret.remove(ret.size() - 1);

        return ret;
    }







}


