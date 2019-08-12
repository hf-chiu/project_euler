
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem16 {

    /** Find the sum of the digits of 2 to the power of EXPONENT. ARGS */
    public static void main(String[] args) {
        final int exponent = 1000;
        String num = safeexp(exponent);
        int sum = 0;

        String[] expanded = num.split("");
        for (String digit : expanded) {
            sum += Integer.parseInt(digit);
        }

        System.out.println(sum);
    }

    /** Returns 2 to the power of EXPONENT. */
    private static String safeexp(int exponent) {
        int count = 1;
        ArrayList<String> tosum = new ArrayList<String>();
        tosum.add("2"); tosum.add("2");
        String mult = fullsum(1, tosum);
        String num;
        String[] res;
        int length;

        while (count < exponent - 1) {
            res = mult.split(" ");
            num = remover(res[0]);
            length = len(num);
            tosum.clear();
            tosum.add(num); tosum.add(num);
            mult = fullsum(length, tosum);
            count += 1;
        }

        res = mult.split(" ");
        num = remover(res[0]);

        return num;
    }

    /** Returns an ArrayList containing the digits of the sum of a list STRNUMS
     *  of number with NUMDIGITS digits. */
    private static String fullsum(int numdigits,
                                              ArrayList<String> strnums) {
        int exp = (int) Math.log10(strnums.size()) + 1;
        ArrayList<int[]> numbers = expander(strnums, numdigits);
        ArrayList<Integer> sum = new ArrayList<>();
        int[] holder = new int[exp + 1];

        for (int pos = numdigits - 1; pos >= 0; pos -= 1) {
            int[] curr = digitsum(pos, numbers, holder);
            sum.add(curr[exp]);
            holder = shifter(curr);
        }

        int ind = 0;
        while (holder[ind] == 0 && ind < exp) {
            ind += 1;
        }

        for (int index = holder.length - 1; index >= ind; index -= 1) {
            sum.add(holder[index]);
        }

        sum = reverser(sum);
        ArrayList<String> strdigits = new ArrayList<>();
        for (int digit : sum) {
            strdigits.add(Integer.toString(digit));
        }

        int length = strdigits.size();
        StringBuilder number = new StringBuilder();
        for (String digit : strdigits) {
            number.append(digit);
        }
        number.append(" ");
        number.append(Integer.toString(length));

        return number.toString();
    }

    /** Expands every number in NUMBERS into an array of digits. Returns
     *  the expanded list. Each number is of length LENGTH. */
    private static ArrayList<int[]> expander(ArrayList<String> numbers,
                                             int length) {

        ArrayList<int[]> expanded = new ArrayList<int[]>();

        for (String number : numbers) {
            String[] split = number.split("");
            int[] temp = new int[length];

            for (int i = 0; i < length; i += 1) {
                temp[i] = Integer.parseInt(split[i]);
            }

            expanded.add(temp);
        }

        return expanded;
    }

    /** Sums the digit at index POS for all numbers in LST.
     *  Takes in a holder int[] HOLDER. Return the sum as an int[]. */
    private static int[] digitsum(int pos, ArrayList<int[]> lst,
                                  int[] holder) {

        int sum = 0;
        for (int[] number : lst) {
            sum += number[pos];
        }

        int length = holder.length - 1;
        int held = 0;
        for (int index = length; index >= 0; index -= 1) {
            held += Math.pow(10, length - index) * holder[index];
        }
        sum += held;

        for (int index = length; index >= 0; index -= 1) {
            holder[index] = Math.floorMod(sum, 10);
            sum = Math.floorDiv(sum, 10);
        }

        return holder;
    }

    /** Shifts every value in an LST over one place to the right and
     *  returns the array. */
    private static int[] shifter(int[] lst) {
        int length = lst.length;

        if (length == 1) {
            return new int[1];
        } else {

            for (int i = length - 1; i > 0; i -= 1) {
                lst[i] = lst[i - 1];
            }

            lst[0] = 0;
            return lst;

        }
    }

    /** Returns a reversed version of LST. */
    private static ArrayList<Integer> reverser(ArrayList<Integer> lst) {
        ArrayList<Integer> reversed = new ArrayList<Integer>();

        for (int i = lst.size() - 1; i >= 0; i -= 1) {
            reversed.add(lst.get(i));
        }

        return reversed;
    }

    /** Returns the NUM with extra zeros in the front removed. */
    private static String remover(String num) {
        String[] expanded = num.split("");
        int len = expanded.length;
        StringBuilder result = new StringBuilder();
        int count = 0;

        while (count < len && expanded[count].equals("0")) {
            count += 1;
        }

        while (count < len) {
            result.append(expanded[count]);
            count += 1;
        }

        return result.toString();
    }

    /** Returns the number of digits in num. */
    private static int len(String num) {
        String[] expanded = num.split("");
        return expanded.length;
    }
}
