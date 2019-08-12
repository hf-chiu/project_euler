
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;

/** @author Nicholas Chiu */
public class Problem57 {

    /** Square Root Convergents */
    public static void main(String[] args) {
        final int num_expansions = 1000;

        BigInteger prev_a = BigInteger.ONE;
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        ArrayList<BigInteger[]> expansions = new ArrayList<>();

        for (int i = 1; i <= num_expansions; i += 1) {
            BigInteger temp_a = (a.multiply(BigInteger.valueOf(2))).add(prev_a);
            BigInteger temp_b = a.add(b);
            prev_a = a;
            a = temp_a;
            b  = temp_b;
            BigInteger[] exp = new BigInteger[]{temp_a, temp_b};
            expansions.add(exp);
        }

        int count = 0;
        for (BigInteger[] exp : expansions) {
            if (exp[0].toString().length() > exp[1].toString().length()) {
                count += 1;
            }
        }
        System.out.println(count);
    }


}
