
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem9 {

    /** Prints out the product of Pythagorean triplets whose sum is N.
     *  ARGS */
    public static void main(String[] args) {
        final int N = 1000;
        ArrayList<int[]> results = pytriplet(N);
        int product;

        if (!results.isEmpty()) {
            for (int[] triplet : results) {
                product = 1;
                for (int number : triplet) {
                    product *= number;
                }
                System.out.println(product);
            }
        } else {
            System.out.println("No such triplet exists.");
        }
    }

    /** Returns Pythagorean triplets which equal N. */
    private static ArrayList<int[]> pytriplet(int n) {
        ArrayList<int[]> results = new ArrayList<>();
        int a2b2;
        int c;
        int c2;

        for (int a = 1; a < (n - 2); a += 1) {
            for (int b = (n - 2); b > a; b -= 1) {
                c = n - a - b;
                a2b2 = (int) (Math.pow(a, 2) + Math.pow(b, 2));
                c2 = (int) Math.pow(c, 2);

                if (a2b2 == c2) {
                    int[] temp = new int[] {a, b, c};
                    results.add(temp);
                }

            }
        }

        return results;
    }
}
