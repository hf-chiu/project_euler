
import java.util.ArrayList;
import java.util.Arrays;


/** @author Nicholas Chiu */
public class Problem39 {

    /** Find the value of p for which the number of integral right
     *  angle triangles whose perimter is p has a maximal number of
     *  solutions. */
    public static void main(String[] args) {
        final int max = 1000;

        int maximum_sols = 0;
        int maximum_p = 0;
        for (int p = 1; p <= max; p += 1) {
            int p_sols = num_sols(p);
            if (p_sols > maximum_sols) {
                maximum_sols = p_sols;
                maximum_p = p;
            }
        }

        System.out.println(maximum_p);
    }


    /* Given p, returns the number of integral right angle triangles with perimeter p. */
    private static int num_sols(int p) {
        int upper_bound = (int) Math.ceil(p / (2 + Math.sqrt(2)));
        int count = 0;

        for (int a = 2; a <= upper_bound; a += 1) {
            float fa = (float) a;
            float fp = (float) p;
            float b = (fp * (2*fa - fp))/(2*(fa - fp));

            if (b == Math.round(b)) {
                count += 1;
            }

        }

        return count;
    }



}
