
import java.math.BigInteger;

/** @author Nicholas Chiu */

public class Problem63 {

    /** Self powers. ARGS */
    public static void main(String[] args) {

        int count = 0;
        BigInteger max = BigInteger.valueOf(9);

        for (BigInteger i = BigInteger.ONE; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
            int n = 1;
            while (true) {
                BigInteger a = i.pow(n);
                BigInteger a_copy = a;
                BigInteger num = BigInteger.ONE;
                while (a.compareTo(BigInteger.TEN) >= 0) {
                    num = num.add(BigInteger.ONE);
                    a = a.divide(BigInteger.TEN);
                }
                if (num.equals(BigInteger.valueOf(n))) {
                    count += 1;
                } else if (num.compareTo(BigInteger.valueOf(n)) < 0) {
                    break;
                }
                n += 1;
            }
        }

        System.out.println(count);

    }







}


