
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


/** @author Nicholas Chiu */
public class Problem33 {

    /** Digit Cancelling Fractions */
    public static void main(String[] args) {

        ArrayList<int[]> non_triv = new ArrayList<>();

        for (int num = 10; num < 100; num += 1) {
            if (num % 10 != 0 && num % 11 != 0) {
                int num_ten = num / 10;
                int num_one = num % 10;

                ArrayList<Integer> poss = new ArrayList<>();

                for (int ones = num_one + 1; ones < 10; ones += 1) {
                    poss.add(Helpers.concatenate(num_ten, ones));
                }
                if (num_one > num_ten) {
                    for (int ones = 1; ones < 10; ones += 1) {
                        if (ones != num_ten) {
                            poss.add(Helpers.concatenate(num_one, ones));
                        }
                    }
                }
                for (int tens = num_ten + 1; tens < 10; tens += 1) {
                    if (tens != num_one) {
                        poss.add(Helpers.concatenate(tens, num_ten));
                    }
                    poss.add(Helpers.concatenate(tens, num_one));
                }

                for (int p : poss) {
                    int[] cancelled = canceller(num, p);
                    int[] c_simp = Helpers.simplifier(cancelled[0], cancelled[1]);
                    int[] simp = Helpers.simplifier(num, p);
                    if (c_simp[0] == simp[0] && c_simp[1] == simp[1]) {
                        non_triv.add(new int[]{num, p});
                    }
                }
            }
        }

        int num = 1;
        int denom = 1;
        for (int[] fraction : non_triv) {
            num *= fraction[0];
            denom *= fraction[1];
        }

        System.out.println(Helpers.simplifier(num, denom)[1]);

    }

    /** Cancels a shared digit from NUM and DENOM. Returns as int[]
     *  of length 2. int[0] is num, int[1] is denom. */
    private static int[] canceller(int num, int denom) {
        ArrayList<Integer> num_lst = Helpers.converter(num);
        ArrayList<Integer> denom_lst = Helpers.converter(denom);

        int to_rem = 0;
        for (int digit : num_lst) {
            if (denom_lst.remove(Integer.valueOf(digit))) {
                to_rem = digit;
            }
        }
        num_lst.remove(Integer.valueOf(to_rem));

        return new int[]{num_lst.get(0), denom_lst.get(0)};
    }


}
