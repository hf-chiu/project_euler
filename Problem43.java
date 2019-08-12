
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem43 {

    /** Substring Divisibility */
    public static void main(String[] args) {

        ArrayList<Long> pandigitals = reformat_lst_of_lsts(pandigitals());

        long sum = 0;
        for (long n : pandigitals) {
            sum += n;
        }

        System.out.println(pandigitals);
        System.out.println(sum);
    }

    /** Creates the list of possible pandigitals for this problem. */
    private static ArrayList<ArrayList<Integer>> pandigitals() {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i += 1) {
            digits.add(i);
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        for (int i = 1; i < 10; i += 1) {
            digits.remove(Integer.valueOf(i));
            ArrayList<ArrayList<Integer>> temp_perms = lexicographic_permuter(9, digits);
            for (ArrayList<Integer> perm : temp_perms) {

                int d4 = perm.get(2);
                boolean div2 = (perm.get(2) % 2 == 0);
                if (!div2) {
                    continue;
                }

                int d6 = perm.get(4);
                boolean div5 = (d6 == 0 || d6 == 5);
                if (!div5) {
                    continue;
                }

                int d3 = perm.get(1);
                int d5 = perm.get(3);
                boolean div3 = ((d3 + d4 + d5) % 3 == 0);
                if (!div3) {
                    continue;
                }

                int d7 = perm.get(5);
                int d8 = perm.get(6);
                boolean div11 = ((d6 - d7 + d8) % 11 == 0);
                if (!div11) {
                    continue;
                }

                boolean div7 = (((d5 * 100) + (d6 * 10) + d7) % 7 == 0);
                if (!div7) {
                    continue;
                }

                int d9 = perm.get(7);
                boolean div13 = (((d7 * 100) + (d8 * 10) + d9) % 13 == 0);
                if (!div13) {
                    continue;
                }

                int d10 = perm.get(8);
                boolean div17 = (((d8 * 100) + (d9 * 10) + d10) % 17 == 0);
                if (!div17) {
                    continue;
                }

                perm.add(0, i);
                ret.add(perm);
            }
            digits.add(i);
        }

        return ret;
    }



    /* Concantenates N1 and N2 iff they have an overlap of OVERLAP digits*/
    private static long concatenate_if_overlap(long n1, long n2, int overlap) {
        String n1_string = Long.toString(n1);
        String n2_string = Long.toString(n2);

        String concatenated;
        if (n1_string.substring(n1_string.length() - overlap).equals(n2_string.substring(0, overlap))) {
            concatenated = n1_string + n2_string.substring(overlap);
        } else {
            concatenated = "-1";
        }

        return Long.valueOf(concatenated);
    }


    /* Concantenates N1 and N2. */
    private static long concatenate(int n1, int n2) {
        String n1_string = Integer.toString(n1);
        String n2_string = Integer.toString(n2);

        String concatenated = n1_string + n2_string;

        return Long.valueOf(concatenated);
    }



    /** Reformats an arraylist of arraylist of integers LST into just an
     * arraylist of integers. */
    private static ArrayList<Long> reformat_lst_of_lsts(ArrayList<ArrayList<Integer>> lst) {
        ArrayList<Long> ret = new ArrayList<>();
        for (ArrayList<Integer> sublst : lst) {
            ret.add(converter_toint(sublst));
        }
        return ret;
    }


    /* Returns true if N is pandigital. */
    private static boolean is_pandigital(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 10; i += 1) {
            digits.add(i);
        }

        String[] n_string = Integer.toString(n).split("");

        if (n_string.length != 9) {
            return false;
        }

        ArrayList<Integer> n_split = new ArrayList<>();
        for (String n_digit : n_string) {
            n_split.add(Integer.valueOf(n_digit));
        }
        n_split.sort(Comparator.naturalOrder());

        for (int i = 0; i < 9; i += 1) {
            if (!n_split.get(i).equals(digits.get(i))) {
                return false;
            }
        }

        return true;
    }


    /** Creates a list of lexicographic permutations of length LEN from list LST. */
    private static ArrayList<ArrayList<Integer>> lexicographic_permuter(int len, ArrayList<Integer> lst) {

        if (len > lst.size()) {
            return null;
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (len == 1) {


            for (int elem : lst) {
                ArrayList<Integer> elem_arrayed = new ArrayList<>();
                elem_arrayed.add(elem);
                ret.add(elem_arrayed);
            }


        } else {


            for (int elem : lst) {
                ArrayList<Integer> rec_lst = new ArrayList<>();
                rec_lst.addAll(lst);
                rec_lst.remove(Integer.valueOf(elem));
                ArrayList<ArrayList<Integer>> rec_ret = lexicographic_permuter(len - 1, rec_lst);

                for (ArrayList<Integer> remainder : rec_ret) {
                    ArrayList<Integer> perm = new ArrayList<>();
                    perm.add(elem);
                    perm.addAll(remainder);
                    ret.add(perm);
                }
            }


        }
        return ret;
    }

    /**  Converts a number N from a list of digits into an int. */
    private static long converter_toint(ArrayList<Integer> N) {
        StringBuilder num_sb = new StringBuilder();
        for (int n : N) {
            num_sb.append(n);
        }
        return Long.valueOf(num_sb.toString());
    }



}



