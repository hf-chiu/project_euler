
/** @author Nicholas Chiu */
public class Problem48 {

    /** Self powers. ARGS */
    public static void main(String[] args) {
        final int last_num_digits = 10;
        final int last_arg = 1000;
        final long mask = (long) Math.pow(10, last_num_digits);


        long ret = 0;
        for (int i = 1; i <= last_arg; i += 1) {
            long current = i;
            for (int exp = 2; exp <= i; exp += 1) {
                current *= i;
                current = current % mask;
            }
            ret += current;
        }
        ret = ret % mask;
        System.out.println(ret);
    }







}


