
import java.util.ArrayList;
import java.util.HashMap;

/** @author Nicholas Chiu */
public class Problem30 {

    /** Digit fifth powers */
    public static void main(String[] args) {
        final int supremum = (int) (6 * Math.pow(9, 5));
        final int exp = 5;

        HashMap<Integer, Integer> powers = new HashMap<>();
        for (int i  = 0; i < 10; i += 1) {
            powers.put(i, (int) Math.pow(i, exp));
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
        for (int i = 2; i <= supremum; i += 1) {
            if (sums[i] == i) {
                System.out.println(i);
                total += i;
            }
        }
        System.out.println();
        System.out.println(total);
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
