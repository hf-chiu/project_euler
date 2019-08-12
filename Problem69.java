
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem69 {

    /** Totient Maximum. */
    public static void main(String[] args) {
        final int supremum = 1000000;

        System.out.println(totient_generator(supremum));


    }


    /** Generates the totient values from 2 to N and returns the value
     *  with the highest value of n/phi(n). */
     private static int totient_generator(int n) {

         float max_ratio = 0;
         int max_val = 0;
         for (int curr = 2; curr <= n; curr += 1) {

             ArrayList<Integer> curr_divisors = Helpers.decomposer(curr);
             HashSet<Integer> curr_div_set = new HashSet<>(curr_divisors);
             int coprime = curr;

             for (int div : curr_div_set) {
                 coprime = coprime / div;
                 coprime *= (div - 1);
             }

             float ratio = (float) curr / (float) coprime;

             if (ratio > max_ratio) {
                 max_ratio = ratio;
                 max_val = curr;
             }

         }
         System.out.println(max_ratio);
         return max_val;

     }
}