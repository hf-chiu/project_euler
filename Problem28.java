
import java.util.ArrayList;


/** @author Nicholas Chiu */
public class Problem28 {

    /** Number Spiral Diagonals. ARGS */
    public static void main(String[] args) {
        final int N = 1001;
        System.out.println(spiraller(N));
    }

    private static int spiraller(int N) {
        int curr = 1;
        int diff = 0;
        int length = 1;
        ArrayList<Integer> diagonals = new ArrayList<>();

        diagonals.add(curr);

        while (length < N) {
            diff += 2;
            diagonals.add(curr += diff);
            diagonals.add(curr += diff);
            diagonals.add(curr += diff);
            diagonals.add(curr += diff);
            length = diff + 1;
        }

        System.out.println(diagonals);
        int sum = 0;
        for (int i : diagonals) {
            sum += i;
        }
        return sum;
    }

}
