
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem76 {

    /** Counting Summations. */
    public static void main(String[] args) {
        final int n = 5;
        System.out.println(partitions(n) - 1);

    }

    /** Returns p(N). */
    private static int partitions(int n) {

        ArrayList<Integer> pentagonals = new ArrayList<>();
        int pentagonal_index = 1;
        int curr_pentagonal = 1;

        ArrayList<Integer> signs = new ArrayList<>();
        int alt = 1;

        ArrayList<Integer> p_n = new ArrayList<>();
        p_n.add(1);
        int curr_n = 1;

        while (curr_n <= n) {

            while (curr_pentagonal <= curr_n) {
                curr_pentagonal = ((3 * pentagonal_index * pentagonal_index) - pentagonal_index) / 2;
                if (pentagonal_index > 0) {
                    pentagonal_index = - pentagonal_index;
                } else {
                    pentagonal_index = 1 - pentagonal_index;
                    signs.add(alt);
                    signs.add(alt);
                    if (alt == 1) {
                        alt = -1;
                    } else {
                        alt = 1;
                    }
                }
                pentagonals.add(curr_pentagonal);
            }

            int size = pentagonals.size() - 1;
            int sum = 0;
            for (int i = 0; i < size; i += 1) {
                sum += signs.get(i) * p_n.get(curr_n - pentagonals.get(i));
            }

            p_n.add(sum);
            curr_n += 1;

        }

        return p_n.get(curr_n - 1);

    }


}
