
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem66 {

    /** Diophantine Equation. */
    public static void main(String[] args) {
        final long supremum = 1000;

        long max_x = 0;
        long max_d = 0;
        for (long d = 2; d <= supremum; d += 1) {
            long x = min_diophantine(d);
            System.out.println(d + ": " + x);
            if (x > max_x) {
                max_x = x;
                max_d = d;
            }
        }
        System.out.println(max_d);

    }

    /** Returns the minimal x for D. */
    private static long min_diophantine(long d) {

        if (Math.pow((long) Math.sqrt(d), 2) == d) {
            return 0;
        }
        long y = 0;
        long rhs = 1;
        long x;

        while (true) {
            rhs += ((2*d*y) + d);
            if (Math.pow((long) Math.sqrt(rhs), 2) == rhs) {
                x = (long) Math.sqrt(rhs);
                break;
            }
            y += 1;
        }

        return x;
    }

}