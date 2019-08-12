
import java.math.BigInteger;
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem65 {

    /** Convergents of e. */
    public static void main(String[] args) {
        final int num_convergent = 100;

        String convergent = e_numerator(num_convergent).toString();
        String[] conv_split = convergent.split("");

        int sum = 0;
        for (String digit : conv_split) {
            sum += Integer.valueOf(digit);
        }
        System.out.println(sum);

    }

    /** Returns the numerator of the Nth convergent of e as a continued fraction. */
    private static BigInteger e_numerator(int n) {

        int mod = n / 3;
        int remainder = n % 3;

        int count = 1;
        ArrayList<Long> convergents = new ArrayList<>();
        for (int i = 0; i < mod; i += 1) {
            convergents.add((long) 1);
            convergents.add((long) count * 2);
            convergents.add((long) 1);
            count += 1;
        }

        if (remainder == 1) {
            convergents.add((long) 1);
        } else if (remainder == 2) {
            convergents.add((long) 1);
            convergents.add((long) count * 2);
        }

        BigInteger prev = BigInteger.ONE;
        BigInteger curr = BigInteger.valueOf(2);

        for (long c : convergents) {
            BigInteger next = (curr.multiply(BigInteger.valueOf(c))).add(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }
}