
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem25 {

    /** Find the first Fibonacci number with N digits. ARGS */
    public static void main(String[] args) {
        final int N = 1000;
        int count = 2;
        int digilen = 1;
        ArrayList<String> toadd = new ArrayList<>();
        String holdera = "1";
        String holderb = "1";

        while (digilen < N) {
            String temp = holderb;
            toadd.clear();
            toadd.add(holdera); toadd.add(holderb);
            holderb = remover(fullsum(digilen, converter(digilen, toadd)));
            digilen = len(holderb);
            holdera = temp;
            count += 1;
        }

        System.out.println(count);
        System.out.println(holderb);
    }

    /** Returns an ArrayList containing the digits of the sum of a list STRNUMS
     *  of number with at most NUMDIGITS digits. */
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

        StringBuilder number = new StringBuilder();
        for (String digit : strdigits) {
            number.append(digit);
        }

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

    /** Returns the NUM with TOADD zeros in the front removed. */
    private static String adder(String num, int toadd) {
        String[] expanded = num.split("");
        int len = expanded.length;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < toadd; i += 1) {
            result.append(0);
        }

        for (String digit : expanded) {
            result.append(digit);
        }

        return result.toString();
    }

    /** Returns the number of digits in NUM. */
    private static int len(String num) {
        String[] expanded = num.split("");
        return expanded.length;
    }

    /** Returns STRNUMS as a list of numbers with the same
     *  NUM number of digits. */
    private static ArrayList<String> converter(int num,
                                               ArrayList<String> strnums) {

        ArrayList<String> results = new ArrayList<String>();

        for (String str : strnums) {
            String temp;
            int curr = len(str);
            if (curr < num) {
                temp = adder(str, num - curr);
            } else {
                temp = str;
            }

            results.add(temp);
        }

        return results;
    }

    /** Returns a compressed version of NUM. */
    private static String compresser(ArrayList<String> num) {
        StringBuilder res = new StringBuilder();
        for (String d : num) {
            res.append(d);
        }
        return res.toString();
    }
}
