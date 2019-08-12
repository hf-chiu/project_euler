
import java.util.HashMap;

/** @author Nicholas Chiu */
public class Problem14 {

    /** Finds the starting number of the longest Collatz sequence under N.
     *  ARGS*/
    public static void main(String[] args) {
        final int N = 1000000;
        HashMap<Long, Integer> lengths = new HashMap<>();
        int longest = 0;
        long current = 0;

        for (long i = 1; i < N; i += 1) {
            int length = collatz(i, lengths);
            if (length > longest) {
                longest = length;
                current = i;
            }
        }

        System.out.println(current);
    }

    /** Returns the length of the Collatz sequence starting from N and
     *  adds values to LENGTHS if not already there. */
    private static int collatz(long N,
                              HashMap<Long, Integer> lengths) {

        int length;

        if (lengths.containsKey(N)) {

            length = lengths.get(N);

        } else {

            if (N == 1) {
                length = 1;
            } else if (N % 2 == 0) {
                length = collatz((N / 2), lengths) + 1;
            } else {
                length = collatz(((3 * N) + 1), lengths) + 1;
            }

            lengths.put(N, length);
        }

        return length;
    }

}
