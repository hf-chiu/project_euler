
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** @author Nicholas Chiu */
public class Problem8 {

    /** Find the largest product of N adjacent digits in a given number.
     *  ARGS */
    public static void main(String[] args) {
        final int distance = 13;
        final int numdigits = 1000;
        int dist = distance - 1;
        ArrayList<String> digits = input();
        long max = 0;

        for (int start = 0; start <= numdigits - distance; start += 1) {
            System.out.println(start);
            long  temp = sliceproduct(start, start + dist, digits);
            if (temp > max) {
                max = temp;
            }
        }

        System.out.println(max);

    }

    /** Returns the input number as a list of its digits. */
    private static ArrayList<String> input() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter number: ");

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

        ArrayList<String> digits = new ArrayList<>();
        for (String ln : results) {
            String[] split = ln.split("");
            Collections.addAll(digits, split);
        }

        return digits;
    }

    /** Returns the product of a list slice from I up to J in DIGITS. */
    private static long sliceproduct(int i, int j,
                                   ArrayList<String> digits) {
        long product = 1;
        for (int index = i; index <= j; index += 1) {
            String curr = digits.get(index);
            product *= Integer.parseInt(curr);
        }
        return product;
    }
}
