
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem32 {

    /** Pandigital Products. */
    public static void main(String[] args) {

        HashSet<Integer> products_1_4 = pandigital_prods(1, 4);
        HashSet<Integer> products_2_3 = pandigital_prods(2, 3);

        int sum = 0;
        for (int product : products_1_4) {
            sum += product;
        }
        for (int product : products_2_3) {
            sum += product;
        }

        System.out.println(sum);
    }

    /* Find pandigital products with multiplicand of length LEN1 and MULTIPLIER of length LEN2. */
    private static HashSet<Integer> pandigital_prods(int len1, int len2) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 10; i += 1) {
            digits.add(i);
        }

        HashSet<Integer> products = new HashSet<Integer>();

        ArrayList<ArrayList<Integer>> multiplicands = lexicographic_permuter(len1, digits);
        for (ArrayList<Integer> multiplicand : multiplicands) {
            ArrayList<Integer> digits_copy = new ArrayList<>();
            digits_copy.addAll(digits);
            digits_copy.removeAll(multiplicand);

            ArrayList<ArrayList<Integer>> multipliers = lexicographic_permuter(len2, digits_copy);

            for (ArrayList<Integer> multiplier : multipliers) {
                ArrayList<Integer> product = mult(multiplicand, multiplier);


                ArrayList<Integer> product_compare = new ArrayList<>();
                product_compare.addAll(product);
                product_compare.sort(Comparator.naturalOrder());
                ArrayList<Integer> leftovers = new ArrayList<>();
                leftovers.addAll(digits_copy);
                leftovers.removeAll(multiplier);

                if (product_compare.equals(leftovers)) {
                    products.add(deconverter(product));
                }
            }
        }

        return products;
    }

    /* Creates a list of lexicographic permutations of length LEN from list LST. */
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

    /** Multiplies NUM1 and NUM2 together. */
    private static ArrayList<Integer> mult(ArrayList<Integer> num1, ArrayList<Integer> num2) {

        ArrayList<Integer> num2_rev = reverser(num2);
        ArrayList<Integer> result = single_mult(num1, num2_rev.get(0));
        ArrayList<Integer> intermediate;

        for (int i = 1; i < num2.size(); i += 1) {
            intermediate = single_mult(num1, num2_rev.get(i));
            for (int j = 0; j < i; j += 1) {
                intermediate.add(0);
            }
            result = add(result, intermediate);
        }

        return result;
    }


    /** Reverses LST. */
    private static ArrayList<Integer> reverser(ArrayList<Integer> lst) {
        ArrayList<Integer> new_lst = new ArrayList<Integer>();
        for (int i = lst.size() - 1; i >= 0; i -= 1) {
            new_lst.add(lst.get(i));
        }
        return new_lst;
    }

    /** Adds two NUM1 and NUM2 together. */
    private static ArrayList<Integer> add(ArrayList<Integer> num1, ArrayList<Integer> num2) {
        num1 = reverser(num1);
        num2 = reverser(num2);

        int num1_len = num1.size();
        int num2_len = num2.size();
        int diff;
        int min;
        ArrayList<Integer> longer;

        if (num1_len >= num2_len) {
            diff = num1_len - num2_len;
            min = num2_len;
            longer = num1;
        } else {
            diff = num2_len - num1_len;
            min = num1_len;
            longer =  num2;
        }

        int holder = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < min; i += 1) {
            int temp = num1.get(i) + num2.get(i) + holder;
            ArrayList<Integer> temp_conv = converter(temp);
            if (temp_conv.size() == 1) {
                holder = 0;
                result.add(0, temp_conv.get(0));
            } else {
                holder = temp_conv.get(0);
                result.add(0, temp_conv.get(1));
            }
        }

        if (holder == 0) {
            for (int i = 0; i < diff; i += 1) {
                result.add(0, longer.get(min + i) );
            }
        } else {
            for (int i = 0; i < diff; i += 1) {
                int temp = longer.get(min + i) + holder;
                ArrayList<Integer> temp_conv = converter(temp);
                if (temp_conv.size() == 1) {
                    holder = 0;
                    result.add(0, temp_conv.get(0));
                } else {
                    holder = temp_conv.get(0);
                    result.add(0, temp_conv.get(1));
                }
            }
        }

        if (holder != 0) {
            result.add(0, holder);
        }

        return result;
    }

    /** Multiplies a given NUMBER (as arraylist) by a single DIGIT */
    private static ArrayList<Integer> single_mult(ArrayList<Integer> numbers, int  digit) {
        ArrayList<Integer> result = new ArrayList<>();

        int holder = 0;
        for (int i = numbers.size() - 1; i >= 0 ; i -= 1) {
            int temp = numbers.get(i) * digit + holder;
            ArrayList<Integer> temp_conv = converter(temp);
            if (temp_conv.size() == 1) {
                holder = 0;
                result.add(0, temp_conv.get(0));
            } else {
                holder = temp_conv.get(0);
                result.add(0, temp_conv.get(1));
            }
        }
        if (holder != 0) {
            result.add(0, holder);
        }

        return result;
    }

    /** Converts a number N into list of digits. */
    private static ArrayList<Integer> converter(long n) {
        String str = Long.toString(n);
        String[] split = str.split("");

        ArrayList<Integer> result = new ArrayList<>();

        for (String digit : split) {
            result.add(Integer.parseInt(digit));
        }

        return result;
    }

    /** Converts a list of digits DIGITS into a number */
    private static int deconverter(ArrayList<Integer> digits) {
        StringBuilder num = new StringBuilder();
        for (int digit : digits) {
            num.append(Integer.toString(digit));
        }
        return Integer.parseInt(num.toString());
    }

}
