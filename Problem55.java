
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem55 {

    /** Lychrel numbers. ARGS */
    public static void main(String[] args) {
        final int supremum = 10000;

        int count = 0;
        for (int i = 1; i <= supremum; i += 1) {
            if (is_lychrel(i)) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    /** Returns true if NUM is lychrel. */
    private static boolean is_lychrel(int num) {
        int iter = 1;
        ArrayList<Integer> c_num = Helpers.converter(num);

        while (iter < 50) {
            ArrayList<Integer> rc_num = Helpers.reverser(c_num);
            c_num = Helpers.add(c_num, rc_num);
            if (Helpers.is_palindrome(c_num)) {
                return false;
            }
            iter += 1;
        }
        return true;

    }


}


