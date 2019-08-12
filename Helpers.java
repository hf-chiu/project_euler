import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Helpers {


    /* Creates a list of unique lexicographic permutations of from nonempty int LST.  */
    public static ArrayList<Integer> lexicographic_permuter(int lst) {
        ArrayList<Integer> conv_lst = converter(lst);
        ArrayList<ArrayList<Integer>> permutations =  lexicographic_permuter(conv_lst.size(), conv_lst);
        HashSet<Integer> sret = new HashSet<>();
        ArrayList<Integer> ret = new ArrayList<>();

        for (ArrayList<Integer> p : permutations) {
            sret.add((int) converter_toint(p));
        }

        ret.addAll(sret);
        return ret;
    }


    /* Creates a list of lexicographic permutations of length LEN from list LST. */
    public static ArrayList<ArrayList<Integer>> lexicographic_permuter(int len, ArrayList<Integer> lst) {

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


    /** Returns true if N is a palindrome. */
    public static boolean is_palindrome(ArrayList<Integer> n) {
        int size = n.size();
        int halflength = Math.floorDiv(size, 2);

        for (int i = 0; i < halflength; i += 1) {
            int start = n.get(i);
            int end = n.get(size - i - 1);

            if (start != end) {
                return false;
            }
        }

        return true;
    }


    /** Returns true if N is a palindrome. */
    public static boolean is_palindrome(int n) {
        if (n < 10) {
            return true;
        }

        ArrayList<Integer> digits = new ArrayList<Integer>();

        while (n > 10) {
            digits.add(Math.floorMod(n, 10));
            n = Math.floorDiv(n, 10);
        }
        digits.add(n);

        int length = digits.size();
        int halflength = Math.floorDiv(length, 2);

        for (int i = 0; i < halflength; i += 1) {
            int start = digits.get(i);
            int end = digits.get(length - i - 1);

            if (start != end) {
                return false;
            }
        }

        return true;
    }


    /* Simplifies the fraction given by NUM/DENOM. Returns an int[] of length 2
    *  where int[0] is the simplified numerator and int[1] the simp. denom.. */
    public static int[] simplifier(int num, int denom) {
        ArrayList<Integer> num_decomp = Helpers.decomposer(num);
        ArrayList<Integer> denom_decomp = Helpers.decomposer(denom);

        ArrayList<Integer> num_torem = new ArrayList<>();
        for (int factor : num_decomp) {
            if (denom_decomp.remove(Integer.valueOf(factor))) {
                num_torem.add(factor);
            }
        }
        for (int factor : num_torem) {
            num_decomp.remove(Integer.valueOf(factor));
        }

        int num_simp = 1;
        int denom_simp = 1;

        for (int remaining : num_decomp) {
            num_simp *= remaining;
        }
        for (int remaining : denom_decomp) {
            denom_simp *= remaining;
        }

        int[] ret = new int[]{num_simp, denom_simp};

        return ret;
    }

    /** Returns an ArrayList of prime factors of N. */
    public static ArrayList<Integer> decomposer(long n) {
        if (n == 1) {
            ArrayList<Integer> i =  new ArrayList<>();
            i.add(1);
            return i;
        }

        ArrayList<Integer> results = new ArrayList<Integer>();
        int factor = smallestfactor(n);

        while (factor != -1) {
            results.add(factor);
            n = n / factor;
            factor = smallestfactor(n);
        }

        if (n != 1) {
            results.add((int) n);
        }

        return results;
    }


    /** Returns an ArrayList of the distinct prime factors of N. */
    public static ArrayList<Integer> distinct_decomposer(long n) {
        if (n == 1) {
            ArrayList<Integer> i =  new ArrayList<>();
            i.add(1);
            return i;
        }

        HashSet<Integer> results = new HashSet<Integer>();
        int factor = smallestfactor(n);

        while (factor != -1) {
            results.add(factor);
            n = n / factor;
            factor = smallestfactor(n);
        }

        if (n != 1) {
            results.add((int) n);
        }

        ArrayList<Integer> ret = new ArrayList<>();
        ret.addAll(results);

        return ret;
    }


    /** Returns the divisors of NUMBER. */
    public static ArrayList<Long> divisors(long number) {

        ArrayList<Long> ret = new ArrayList<>();

        if (number == 1) {
            ret.add((long) 1);
        } else if (number == 2) {
            ret.add((long) 2);
        } else {

            long supremum = (long) Math.ceil(Math.sqrt(number));
            long divisor = 2;

            while (divisor < supremum) {
                if (number % divisor == 0) {
                    ret.add(divisor);
                    ret.add(number / divisor);
                }
                divisor += 1;
            }


            if (Math.pow(divisor, 2) == number) {
                ret.add(divisor);
            }

        }

        return ret;
    }

    /** Returns true if N is a prime. */
    public static boolean isprime(long n) {

        if (n == 1) {
            return false;
        } else if (n == 2) {
            return true;
        } else {
            int factor = smallestfactor(n);
            return factor == -1;
        }
    }

    /** Returns the smallest factor of N. */
    public static int smallestfactor(long n) {

        int factor = 2;
        int supremum = (int) Math.sqrt((double) n);

        while (true) {
            if (n % factor == 0) {
                return factor;
            } else if (factor > supremum) {
                return -1;
            }

            factor += 1;
        }
    }

    /** Shortsighted exponentiates NUM1, as int, by NUM2, as int, together. */
    public static ArrayList<Integer> shortsighted_exp(int num1, int num2, int visible) {
        ArrayList<Integer> conv_num1 = converter(num1);
        return shortsighted_exp(conv_num1, num2, visible);
    }


    /** Shortsighted exponentiates NUM1, as arraylist, by NUM2, as int, together. */
    public static ArrayList<Integer> shortsighted_exp(ArrayList<Integer> num1,
                                                       int num2, int visible) {

        ArrayList<Integer> ret = num1;

        for (int i = 2; i <= num2; i += 1) {
            ret = shortsighted_mult(ret, num1, visible);
        }

        return ret;
    }


    /** Shortsighted multiplies NUM1 and NUM2 together. */
    public static ArrayList<Integer> shortsighted_mult(ArrayList<Integer> num1,
                                                        ArrayList<Integer> num2, int visible) {

        ArrayList<Integer> multiplied = mult(num1, num2);

        if (visible > multiplied.size()) {
            return multiplied;
        } else {
            ArrayList<Integer> ret = new ArrayList<>();
            List<Integer> cut = multiplied.subList(multiplied.size() - visible, multiplied.size());
            ret.addAll(cut);
            return ret;
        }

    }

    /** Shortsighted adds NUM1 and NUM2 together; ie adds NUM1 and NUM2 together
     *  but only returns the last VISIBLE digits. */
    public static ArrayList<Integer> shortsighted_add(ArrayList<Integer> num1,
                                                       ArrayList<Integer> num2, int visible) {

        ArrayList<Integer> added = add(num1, num2);

        if (visible > added.size()) {
            return added;
        } else {
            ArrayList<Integer> ret = new ArrayList<>();
            List<Integer> cut = added.subList(added.size() - visible, added.size());
            ret.addAll(cut);
            return ret;
        }

    }


    /** Shortsighted exponentiates NUM1, as int, by NUM2, as int, together. */
    public static ArrayList<Integer> exp(int num1, int num2) {
        ArrayList<Integer> conv_num1 = converter(num1);
        return exp(conv_num1, num2);
    }


    /** Shortsighted exponentiates NUM1, as arraylist, by NUM2, as int, together. */
    public static ArrayList<Integer> exp(ArrayList<Integer> num1, int num2) {

        ArrayList<Integer> ret = num1;

        for (int i = 2; i <= num2; i += 1) {
            ret = mult(ret, num1);
        }

        return ret;
    }

    /** Multiplies NUM1 and NUM2 together. */
    public static ArrayList<Integer> mult(ArrayList<Integer> num1, ArrayList<Integer> num2) {

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
    public static ArrayList<Integer> reverser(ArrayList<Integer> lst) {
        ArrayList<Integer> new_lst = new ArrayList<Integer>();
        for (int i = lst.size() - 1; i >= 0; i -= 1) {
            new_lst.add(lst.get(i));
        }
        return new_lst;
    }

    /** Adds two NUM1 and NUM2 together. */
    public static ArrayList<Integer> add(ArrayList<Integer> num1, ArrayList<Integer> num2) {
        if (num1 == null) {
            return num2;
        } else if (num2 == null) {
            return num1;
        }


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
    public static ArrayList<Integer> single_mult(ArrayList<Integer> numbers, int  digit) {
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
    public static ArrayList<Integer> converter(long n) {
        String str = Long.toString(n);
        String[] split = str.split("");

        ArrayList<Integer> result = new ArrayList<>();

        for (String digit : split) {
            result.add(Integer.parseInt(digit));
        }

        return result;
    }

    /**  Converts a number N from a list of digits into an int. */
    public static long converter_toint(ArrayList<Integer> N) {
        StringBuilder num_sb = new StringBuilder();
        for (int n : N) {
            num_sb.append(n);
        }
        return Long.valueOf(num_sb.toString());
    }

    /* Concantenates N1 and N2. */
    public static long concatenate(long n1, long n2) {
        if (n1 < 0) {
            return n2;
        } else if (n2 < 0) {
            return n1;
        }

        String n1_string = Long.toString(n1);
        String n2_string = Long.toString(n2);

        String concatenated = n1_string + n2_string;

        return Long.valueOf(concatenated);
    }

    /* Concantenates N1 and N2. */
    public static int concatenate(int n1, int n2) {
        if (n1 < 0) {
            return n2;
        } else if (n2 < 0) {
            return n1;
        }

        String n1_string = Long.toString(n1);
        String n2_string = Long.toString(n2);

        String concatenated = n1_string + n2_string;

        return Integer.valueOf(concatenated);
    }


}
