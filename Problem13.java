
import java.util.ArrayList;
import java.util.Scanner;

/** @author Nicholas Chiu */
public class Problem13 {

    /** Find the first REQUIRED digits in the sum of the given list of numbers.
     *  ARGS */
    public static void main(String[] args) {
        final int required = 10;
        final int numdigits = 50;
        ArrayList<Integer> digits = fullsum(numdigits);
        String result = "";

        for (int i = 0; i < required; i += 1) {
            result += Integer.toString(digits.get(i));
        }

        System.out.println(result);
    }

    /** Returns an ArrayList containing the digits of the sum of a list of
     *  number with NUMDIGITS digits. */
    private static ArrayList<Integer> fullsum(int numdigits) {
        ArrayList<String> strnums = input();
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

        return reverser(sum);
    }

    /** Returns the input numbers as a list. */
    private static ArrayList<String> input() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter numbers: ");

        ArrayList<String> results = new ArrayList<>();
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line == null || line.isEmpty()) {
                break;
            } else {
                results.add(line);
            }
        }
        reader.close();

        return results;
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
}
