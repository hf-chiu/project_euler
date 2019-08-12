

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/** @author Nicholas Chiu */

public class Problem62 {

    /** Cubic Permutations. ARGS */
    public static void main(String[] args) {
        final long enough = 5;

        boolean found = false;
        boolean due_diligence = false;

        ArrayList<ArrayList<Long>> candidates = new ArrayList<>();

        long curr = 1;
        HashMap<ArrayList<Integer>, ArrayList<Long>> cube_digits = new HashMap<>();

        while (!found) {
            long cubed = (long) Math.pow(curr, 3);
            ArrayList<Integer> cubed_expanded = Helpers.converter(cubed);
            cubed_expanded.sort(Comparator.naturalOrder());
            ArrayList<Long> a = cube_digits.get(cubed_expanded);

            if (a == null) {
                ArrayList<Long> n = new ArrayList<>();
                n.add(curr);
                cube_digits.put(cubed_expanded, n);
            } else if (a.size() >= enough - 1) {
                a.add(curr);
                found = true;
                candidates.add(a);
            } else {
                a.add(curr);
            }

            curr += 1;
        }

        int ceil = (int) Math.pow(10, (int) Math.log10(curr) + 1);

        while (curr < ceil) {
            long cubed = (long) Math.pow(curr, 3);
            ArrayList<Integer> cubed_expanded = Helpers.converter(cubed);
            cubed_expanded.sort(Comparator.naturalOrder());
            ArrayList<Long> a = cube_digits.get(cubed_expanded);

            if (a == null) {
                ArrayList<Long> n = new ArrayList<>();
                n.add(curr);
                cube_digits.put(cubed_expanded, n);
            } else if (a.size() >= enough - 1) {
                a.add(curr);
                candidates.add(a);
            } else {
                a.add(curr);
            }

            curr += 1;
        }

        long min = Long.MAX_VALUE;
        for (ArrayList<Long> c : candidates) {
            if (c.get(0) < min) {
                min = c.get(0);
            }
        }

        System.out.println(Long.toString((long) Math.pow(min, 3)));
    }









}


