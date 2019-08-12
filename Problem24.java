
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem24 {

    /** Find Nth lexicographical permutation of 0-9. ARGS */
    public static void main(String[] args) {
        final int N = 1000000;
        final int upto = 9;
        ArrayList<String> inrotation = new ArrayList<>();

        for (int i = 0; i <= upto ; i += 1) {
            inrotation.add(Integer.toString(i));
        }

        System.out.println(lexi_order(1000000, inrotation));


    }

    /** Finds lexicographical order of INDEX given LST. */
    public static String lexi_order(int index, ArrayList<String> lst) {
        index -= 1;
        if (factorial(lst.size()) < index) {
            return "-1";
        }

        StringBuilder result = new StringBuilder();
        int curr_fact;
        int curr_ind;
        while (lst.size() > 0) {
            curr_fact = factorial(lst.size() - 1);
            curr_ind = index / curr_fact;
            result.append(lst.remove(curr_ind));
            index -= (curr_ind * curr_fact);
        }
        return result.toString();
    }


    /** Factorial of N. */
    public static int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i += 1) {
            res *= i;
        }
        return res;
    }



}
