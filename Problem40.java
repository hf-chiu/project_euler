
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem40 {

    /** Champernowne's constant. */
    public static void main(String[] args) {
        final int max_exp = 6;

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i <= max_exp; i += 1) {
            indices.add((int) Math.pow(10, i));
        }

        ArrayList<Integer> n = champernowne_digits(indices);

        int product = 1;
        for (int i : n) {
            product *= i;
        }
        System.out.println(product);
    }


    /** Given a non-empty list of sorted INDICES, returns the corresponding digits from
     *  Champernowne's constant. */
    private static ArrayList<Integer> champernowne_digits(ArrayList<Integer> indices) {
        int exp = 0;
        int sup = 10;
        int num_digits = 1;
        int int_count = 0;
        int digit_count = 0;
        int indices_index = 0;
        int indices_length = indices.size();
        ArrayList<Integer> ret = new ArrayList<>();

        while (indices_index < indices_length) {

            int curr_index =  indices.get(indices_index);
            indices_index += 1;

            while (digit_count < curr_index) {
                digit_count += num_digits;
                int_count += 1;
                if (int_count == sup - 1) {
                    sup *= 10;
                    exp += 1;
                    num_digits += 1;
                }
            }

            int diff = curr_index - (digit_count - num_digits + 1);
            if (int_count == (sup / 10) - 1) {
                diff -= 1;
            }

            ArrayList<Integer> converted = Helpers.converter(int_count);
            ret.add(converted.get(diff));
        }

        return ret;
    }



}
