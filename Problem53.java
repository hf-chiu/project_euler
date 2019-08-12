
import java.lang.reflect.Array;
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem53 {

    /** Combinatoric Selections. ARGS */
    public static void main(String[] args) {
        final int max_line = 100;
        final int infimum = 1000000;

        ArrayList<Integer> curr_line = new ArrayList<>();
        curr_line.add(1);
        System.out.println(curr_line);

        int count = 0;
        for (int line = 1; line <= max_line; line += 1) {
            boolean first_mil = false;
            boolean last_mil = false;
            ArrayList<Integer> prev_line = curr_line;
            ArrayList<Integer> temp = next(prev_line, infimum);
            curr_line = temp;
            for (int n : curr_line) {
                if (n > infimum || n == 0) {
                    count += 1;
                }
            }
        }

        System.out.println(count);
    }

    /** Returns the next line in Pascal's triangle given PREV but with some problem
     * specific modifications. Firstly any value INFIMUM is put down as 0 except
     * for the first and last values which are retained as integer values. */
    private static ArrayList<Integer> next(ArrayList<Integer> prev, int infimum) {
        ArrayList<Integer> next = new ArrayList<>();

        next.add(1);
        int size = prev.size() - 1;
        for (int i = 0; i < size; i += 1) {
            int curr_ind = (prev.get(i) + prev.get(i + 1));
            next.add(curr_ind);
        }
        next.add(1);

        boolean found_first = false;
        int count = 0;
        while (!found_first) {
            for (int i : next) {
                count += 1;
                if (i > infimum) {
                    found_first = true;
                    break;
                }
            }
            if (found_first) {
                int end = next.size() - count;
                for (int i = count; i < end; i += 1) {
                    next.set(i, 0);
                }
            }
            found_first = true;
        }

        return next;
    }







}


