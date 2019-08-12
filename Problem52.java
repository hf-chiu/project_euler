

import java.util.ArrayList;
import java.util.Comparator;

/** @author Nicholas Chiu */

public class Problem52 {

    /** Permuted Multiples. ARGS */
    public static void main(String[] args) {

        boolean found = false;

        int end = 0;

        while (!found) {

            long x = Helpers.concatenate(1, end);
            long two_x = 2 * x;
            long three_x = 3 * x;
            long four_x = 4 * x;
            long five_x = 5 * x;
            long six_x = 6 * x;

            ArrayList<Integer> x_conv = Helpers.converter(x);
            ArrayList<Integer> two_x_conv = Helpers.converter(two_x);
            ArrayList<Integer> three_x_conv = Helpers.converter(three_x);
            ArrayList<Integer> four_x_conv = Helpers.converter(four_x);
            ArrayList<Integer> five_x_conv = Helpers.converter(five_x);
            ArrayList<Integer> six_x_conv = Helpers.converter(six_x);

            x_conv.sort(Comparator.naturalOrder());
            two_x_conv.sort(Comparator.naturalOrder());

            if (x_conv.equals(two_x_conv)) {
                three_x_conv.sort(Comparator.naturalOrder());
                if (x_conv.equals(three_x_conv)) {
                    four_x_conv.sort(Comparator.naturalOrder());
                    if (x_conv.equals(four_x_conv)) {
                        five_x_conv.sort(Comparator.naturalOrder());
                        if (x_conv.equals(five_x_conv)) {
                            six_x_conv.sort(Comparator.naturalOrder());
                            if (x_conv.equals(six_x_conv)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            end += 1;
        }

        System.out.println(Helpers.concatenate(1, end - 1));

    }









}


