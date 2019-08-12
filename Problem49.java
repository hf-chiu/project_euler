
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/** @author Nicholas Chiu */
public class Problem49 {

    /** Prime Permutations. ARGS. */
    public static void main(String[] args) {
        final int magic_number = 4;
        ArrayList<ArrayList<Integer>> families = prime_permutations(4, 3);



        ArrayList<int[]> triplets = new ArrayList<>();
        for (ArrayList<Integer> family : families) {
            ArrayList<Integer> diffs = new ArrayList<>();
            for (int i = 1; i < family.size(); i += 1) {
                diffs.add(family.get(i) - family.get(0));
            }

            boolean exhausted = false;
            int comp_index = 0;
            int d_size = diffs.size();

            /** 0 index start loop. */
            for (int i = comp_index + 1; i < d_size; i += 1) {
                for (int j = i + 1; j < d_size; j += 1) {
                    if (diffs.get(j) == 2 * diffs.get(i)) {
                        int[] n = new int[]{family.get(0), family.get(i + 1),
                                family.get(j + 1)};
                        triplets.add(n);
                    }
                }
            }
            /** Other index start loops. */
            while (!exhausted) {
                int offset = diffs.get(comp_index);
                for (int i = comp_index + 1; i < d_size; i += 1) {
                    for (int j = i + 1; j < d_size; j += 1) {
                        if (diffs.get(j) - offset == 2 * (diffs.get(i) - offset)) {
                            int[] n = new int[]{family.get(comp_index + 1), family.get(i + 1),
                                    family.get(j + 1)};
                            triplets.add(n);
                        }
                    }
                }
                comp_index += 1;
                if (comp_index == d_size - 2) {
                    exhausted = true;
                }
            }
        }

        for (int[] n : triplets) {
            String n0 = Integer.toString(n[0]);
            String n1 = Integer.toString(n[1]);
            String n2 = Integer.toString(n[2]);
            n0 += n1;
            n0 += n2;
            System.out.println(n0);
        }

    }


    /** Returns a list of possible prime permutation families with DIGITS digits
     *  and with at least N members. */
    private static ArrayList<ArrayList<Integer>> prime_permutations(int digits, int n) {
        int floor = (int) Math.pow(10, digits - 1);
        int ceil = (int) Math.pow(10, digits);

        ArrayList<Integer> possibilities = new ArrayList<>();
        for (int i = floor; i < ceil; i += 1) {
            possibilities.add(i);
        }

        Iterator<Integer> poss_iter = possibilities.iterator();

        ArrayList<ArrayList<Integer>> families = new ArrayList<>();
        while (poss_iter.hasNext()) {
            int poss = poss_iter.next();
            if (Helpers.isprime(poss)) {
                int count = 0;
                ArrayList<Integer> local_family = Helpers.lexicographic_permuter(poss);
                Iterator<Integer> lf_iter = local_family.iterator();
                while (lf_iter.hasNext()) {
                    int member = lf_iter.next();
                    if (member > floor) {
                        if (Helpers.isprime(member)) {
                            count += 1;
                        } else {
                            lf_iter.remove();
                        }
                        possibilities.remove(Integer.valueOf(member));
                    } else {
                        lf_iter.remove();
                    }
                }
                if (count > n) {
                    local_family.sort(Comparator.naturalOrder());
                    families.add(local_family);
                }
                poss_iter = possibilities.iterator();
            }
        }

        return families;
    }







}


