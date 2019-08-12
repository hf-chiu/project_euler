
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem26 {

    /** Longest recurring cycle in reciprocal fraction. */
    public static void main(String[] args) {
        final int N = 1000;

        int longest = 0;
        int n = 0;
        for (int i = 1; i <= N; i += 1) {
            int cycle_length = reciprocal_longdivision(i);
            if (cycle_length > longest) {
                longest = cycle_length;
                n = i;
            }
        }

        System.out.println("number: " + n + ", cycle length: " + longest);
    }


    /** Long divides 1 by dividend. */
    private static int reciprocal_longdivision(int dividend) {

        int curr = 1;
        int return_val = 0;
        int first = 0;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> currs = new ArrayList<>();
        currs.add(1);

        while (true) {
            if (dividend > curr) {
                curr *= 10;
                result.add(0);
                currs.add(curr);
            } else {
                int count = curr / dividend;
                int temp = count * dividend;
                curr -= temp;
                if (result.size() != 0) {
                    result.set(result.size() - 1, count);
                } else {
                    result.add(count);
                }


                if (curr == 0) {
                    break;
                } else if (currs.contains(curr * 10)) {
                    return_val = currs.size() - 1 - currs.indexOf(curr);
                    break;
                }
            }


        }
        return return_val;
    }

}
