
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem58 {

    /** Spiral Primes. ARGS */
    public static void main(String[] args) {

        spiraller(10);
    }

    private static void spiraller(int N) {
        int curr = 1;
        int diff = 0;
        int length = 0;
        int num_diag = 1;
        int num_primes = 0;
        boolean end = false;

        while (!end) {
            diff += 2;
            for (int i = 1; i < 5; i += 1) {
                if (Helpers.isprime(curr += diff)) {
                    num_primes += 1;
                }
            }
            num_diag += 4;
            length = diff + 1;
            end = (num_primes * 10) < num_diag;
        }

        System.out.println(length);

    }

}
