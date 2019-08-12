
import java.util.ArrayList;
import java.util.HashMap;

/** @author Nicholas Chiu */
public class Problem34 {

    /** Digit factorials */
    public static void main(String[] args) {
        final int max_digit = 9;
        final int supremum = 7 * factorial(max_digit);

        HashMap<Integer, Integer> powers = new HashMap<>();
        for (int i  = 0; i < 10; i += 1) {
            powers.put(i, factorial(i));
        }
        int[] sums = new int[supremum + 1];
        for (int i = 1; i <= supremum; i += 1) {
            int sum = 0;
            for (int j : converter(i)) {
                sum += powers.get(j);
            }
            sums[i] = sum;
        }

        int total = 0;
        for (int i = 3; i <= supremum; i += 1) {
            if (sums[i] == i) {
                System.out.println(i);
                total += i;
            }
        }
        System.out.println();
        System.out.println(total);
    }

    /** Factorial. */
    private static int factorial(int n) {
        int total = 1;
        for (int i = 2; i <= n; i += 1) {
            total *= i;
        }
        return total;
    }


    /** Converts a number N into list of digits. */
    private static ArrayList<Integer> converter(long n) {
        String str = Long.toString(n);
        String[] split = str.split("");

        ArrayList<Integer> result = new ArrayList<>();

        for (String digit : split) {
            result.add(Integer.parseInt(digit));
        }

        return result;
    }


}
