
import java.util.ArrayList;
import java.util.Arrays;

/** @author Nicholas Chiu */
public class Problem45 {

    /** Triangular, Pentagonal, and Hexagonal. ARGS */
    public static void main(String[] args) {
        final int match = 2;

        int tri_index = 2;
        int pent_index = 2;
        int hex_index = 2;
        int tri = 3;
        int pent = 5;
        int hex = 6;
        int curr_max = 6;

        int count = 0;
        ArrayList<int[]> matches = new ArrayList<>();

        while (count != match) {
            if (tri < curr_max) {
                while (tri < curr_max) {
                    tri += (tri_index + 1);
                    tri_index += 1;
                }
            }
            if (pent < curr_max) {
                while (pent < curr_max) {
                    pent += ((3 * pent_index) + 1);
                    pent_index += 1;
                }
            }
            if (hex < curr_max) {
                while (hex < curr_max) {
                    hex += ((4 * hex_index) + 1);
                    hex_index += 1;
                }
            }
            if (tri == pent && tri == hex) {
                count += 1;
                int[] m = new int[]{tri_index, pent_index, hex_index, tri};
                matches.add(m);
                tri += (tri_index + 1);
                tri_index += 1;
                pent += ((3 * pent_index) + 1);
                pent_index += 1;
                hex += ((4 * hex_index) + 1);
                hex_index += 1;

            }
            curr_max = Math.max(Math.max(tri, pent), hex);
        }

        for (int[] m : matches) {
            System.out.println(Arrays.toString(m));
        }

    }







}


