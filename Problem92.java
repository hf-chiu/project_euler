
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem92 {

    /** Square Digit Chains. */
    public static void main(String[] args) {
        final int num_convergent = 10000000;

        int num_digits = (int) Math.log10(num_convergent - 1) + 1;
        int maximal_sum = num_digits * (int) Math.pow(9, 2);

        int[] results = new int[maximal_sum + 1];
        results[1] = 1;
        results[89] = 89;

        for (int i = 1; i <= maximal_sum; i += 1) {

            ArrayList<Integer> curr_chain = new ArrayList<>();
            int curr = i;

            while (true) {

                if (results[curr] == 1 || curr == 1) {
                    curr_chain.add(curr);
                    for (int c : curr_chain) {
                        results[c] = 1;
                    }
                    break;
                } else if (results[curr] == 89 || curr == 89) {
                    curr_chain.add(curr);
                    for (int c : curr_chain) {
                        results[c] = 89;
                    }
                    break;
                }

                curr_chain.add(curr);

                String[] curr_str = Integer.toString(curr).split("");
                int sum = 0;
                for (String digit : curr_str) {
                    sum += (int) Math.pow(Integer.valueOf(digit), 2);
                }

                curr = sum;

            }

        }

        int count = 0;
        for (int i = 1; i < num_convergent; i += 1) {
            String[] curr_str = Integer.toString(i).split("");
            int sum = 0;
            for (String digit : curr_str) {
                sum += (int) Math.pow(Integer.valueOf(digit), 2);
            }
            if (results[sum] == 89) {
                count += 1;
            }
        }
        System.out.println(count);

    }

}