
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem73 {

    /** Counting Fractions In A Range. */
    public static void main(String[] args) {
        final int supremum = 12000;
        final int floor_num = 1;
        final int floor_denom = 3;
        final int ceil_num = 1;
        final int ceil_denom = 2;

        System.out.println(pf_in_range(supremum, floor_num, floor_denom,
                ceil_num, ceil_denom));


    }


    /** Returns the number of proper fractions with denom <= N and
     *  between floor and ceil. Floor and ceil are given as FLOOR_NUM,
     *  FLOOR_DENOM, CEIL_NUM, CEIL_DENOM.*/
    private static long pf_in_range(int n, int floor_num, int floor_denom,
                                    int ceil_num, int ceil_denom) {


        HashMap<Integer, ArrayList<Integer>> factors = new HashMap<>();
        long sum = 0;

        for (int curr = 2; curr <= n; curr += 1) {

            int floor = (int) Math.ceil((curr * floor_num) / (float) floor_denom);
            int ceil = (int) Math.floor((curr * ceil_num) / (float) ceil_denom);
            if (curr % floor_denom == 0) {
                floor += 1;
            }
            if (curr % ceil_denom == 0) {
                ceil -= 1;
            }
            int diff = ceil - floor + 1;

            ArrayList<Integer> curr_divisors = Helpers.decomposer(curr);
            HashSet<Integer> curr_div_set = new HashSet<>(curr_divisors);
            HashSet<Integer> in_range = new HashSet<>();

            for (int div : curr_div_set) {

                if (factors.containsKey(div)) {
                    ArrayList<Integer> div_set = factors.get(div);
                    for (int i : div_set) {
                        if (i >= floor && i <= ceil) {
                            in_range.add(i);
                        }
                    }
                    div_set.add(curr);
                } else {
                    ArrayList<Integer> to_store = new ArrayList<>();
                    to_store.add(curr);
                    factors.put(div, to_store);
                }

            }
            sum += (diff - in_range.size());
        }

        return sum;

    }
}