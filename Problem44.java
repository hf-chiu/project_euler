
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem44 {

    /** Pentagon numbers. ARGS */
    public static void main(String[] args) {
        boolean found = false;

        int k = 2;

        while (!found) {

            int p_k = (k * ((3*k) - 1)) / 2;

            for (int j = 1; j < k; j += 1) {
                int p_j = (j * ((3*j) - 1)) / 2;
                int diff = p_k - p_j;
                double d_n = ((double) 1 + Math.sqrt(1 + (24 * diff)))/ (double) 6;

                if ((double) (int) d_n == d_n) {
                    int sum = p_k + p_j;
                    double s_n = ((double) 1 + Math.sqrt(1 + (24 * sum)))/ (double) 6;
                    if ((double) (int) s_n == s_n) {
                        System.out.println("k: " + k + ", j: " + j + ", pk: " +
                                p_k + ", pj: " + p_j + ", diff: " + diff);
                        found = true;
                    }
                }
            }
            k += 1;

        }
    }







}


