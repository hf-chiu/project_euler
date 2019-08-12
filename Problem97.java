
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem97 {

    /** Large Non-Mersenne Prime. */
    public static void main(String[] args) {

        int exp = 7830457;

        String[] exp_str = Integer.toBinaryString(exp).split("");
        int length = exp_str.length;

        ArrayList<ArrayList<Integer>> powers = new ArrayList<>();
        ArrayList<Integer> curr = Helpers.converter(2);
        powers.add(curr);
        for (int i = 1; i < length; i += 1) {
            ArrayList<Integer> next = Helpers.shortsighted_mult(curr, curr, 10);
            powers.add(next);
            curr = next;
        }

        ArrayList<Integer> start = new ArrayList<>();
        start.add(1);
        for (int i = length - 1; i >= 0; i -= 1) {
            if (exp_str[i].equals("1")) {
                start = Helpers.shortsighted_mult(start, powers.get(length - 1 - i), 10);
            }
        }
        ArrayList<Integer> c = Helpers.converter(28433);
        start = Helpers.shortsighted_mult(c, start, 10);

        long res = Helpers.converter_toint(start) + 1;
        System.out.println(res);
    }

}
