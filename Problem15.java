
/** @author Nicholas Chiu */
public class Problem15 {

    /** Find the number of routes from the top left corner to bottom
     *  right corner of an N x N grid. ARGS*/
    public static void main(String[] args) {
        final int size = 20;
        long[][] stored = new long[size + 1][size + 1];
        long ways = pathways(size, size, stored);

        System.out.println(ways);
    }

    /** Returns the number of ways to an I x J rectangle.
     *  STORED stores the values for corresponding ixj rectangles. */
    private static long pathways(int i, int j, long[][] stored) {

        long possible = stored[i][j];

        if (possible != 0) {
            return possible;
        } else {

            long numberof;

            if (i == 0 && j == 0) {
                numberof = 0;
            } else if (j == 0) {
                numberof = 1;
            } else if (i == j) {
                numberof = (2 * pathways(i, j - 1, stored));
            } else {
                numberof = pathways(i - 1, j, stored)
                        + pathways(i, j - 1, stored);
            }

            stored[i][j] = numberof;
            return numberof;
        }
    }
}
