
/** @author Nicholas Chiu */
public class Problem2 {

    /** Find the sum of the even valued Fibonacci sequence terms under
     *  SUPREMUM. ARGS */
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int sum = 0;
        int count = 2;
        final int supremum = 4000000;

        while (b < supremum) {
            if (count == 3) {
                sum += b;
                count = 0;
            }
            int temp = b;
            b += a;
            a = temp;
            count += 1;
        }
        System.out.println(sum);
    }
}
