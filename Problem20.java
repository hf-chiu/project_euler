
import java.lang.reflect.Array;
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem20 {

    /** Find the sum of the digits of 100!. ARGS */
    public static void main(String[] args) {
        final int N = 100;
        ArrayList<Integer> res = converter(1);

        for (int i = 2; i <= N; i += 1) {
            res = mult(res, converter(i));
        }

        int count = 0;
        for (int digit : res) {
            count += digit;
        }
        System.out.println(count);
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


}


