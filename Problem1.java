
/** @author Nicholas Chiu */
public class Problem1 {

    /* Find the sum of all multiples of 3 and 5 under SUPREMUM. ARGS */
    public static void main(String[] args) {
        int count = 0;
        int sum = 0;
        final int supremum = 1000;

        while (count < supremum) {
            if ((count % 3) == 0 || (count % 5) == 0) {
                sum += count;
            }
            count += 1;
        }
        System.out.println(sum);
    }
}
