
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem56 {

    /** Powerful Digit Sum. ARGS */
    public static void main(String[] args) {

        int max = 0;

        for (int a = 2; a <= 99; a += 1) {
            System.out.print(a + "| ");
            for (int b = 1; b <= 100; b += 1) {
                ArrayList<Integer> ab_digits = Helpers.exp(a, b);

                int sum = 0;
                for (int i : ab_digits) {
                    sum += i;
                }

                if (sum > max) {
                    max = sum;
                }

            }
            System.out.print("current maximum: " + max);
            System.out.println();
        }

        System.out.println(max);
    }

}


