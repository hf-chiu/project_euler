
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem46 {

    /** Goldbach's Other Conjecture. ARGS */
    public static void main(String[] args) {
        int current =  3;
        boolean found = false;

        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        int pmax_checked = 2;
        ArrayList<Boolean> doubled_squares = new ArrayList<>();
        doubled_squares.add(true);
        doubled_squares.add(false);
        doubled_squares.add(true);
        int dsmax_checked = 2;
        int dsmax_ind = 1;

        while (!found) {
            while (pmax_checked < current) {
                pmax_checked += 1;
                if (Helpers.isprime(pmax_checked)) {
                    primes.add(pmax_checked);
                }
            }
            while (dsmax_checked < current) {
                dsmax_ind += 1;
                int ds_next = 2 * (dsmax_ind * dsmax_ind);
                for (int i = dsmax_checked + 1; i < ds_next; i += 1) {
                    doubled_squares.add(false);
                }
                doubled_squares.add(true);
                dsmax_checked = ds_next;
            }
            boolean skip = false;
            for (int prime : primes) {
                if (doubled_squares.get(current - prime)) {
                    skip = true;
                    break;
                }
            }
            if (!skip) {
                found = true;
                System.out.print(current);
            }

            current += 2;
        }




    }







}


