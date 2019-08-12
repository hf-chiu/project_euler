
/** @author Nicholas Chiu */
public class Problem6 {

    /** Find the difference the sum of squares and the square of sums
     *  up to SUPREMUM. ARGS */
    public static void main(String[] args) {
        final int supremum = 100;
        int sidelength = 1;
        int difference = 0;

        for (int i = 2; i <= supremum; i += 1) {
            difference += 2 * sidelength * i;
            sidelength += i;
        }

        System.out.println(difference);
    }
}
