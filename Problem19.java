
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem19 {

    /** Find the number of letters used to write out the numbers 1
     * through to N. ARGS */
    public static void main(String[] args) {
        final int start = 1901;
        final int end = 2000;
        int weeklength = 7;
        int offset = 6;

        ArrayList<Integer> days = firsts(start, end);
        int count = 0;

        for (int day : days) {
            if (day % weeklength == offset) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    /** Returns a list of the daycount of the first of each month from
     * the years START to END. */
    private static ArrayList<Integer> firsts(int start, int end) {
        ArrayList<Integer> days = new ArrayList<Integer>();
        int daycount = 1;

        for (int year = start; year <= end; year += 1) {
            int temp;
            boolean leapyear = leapyear(year);

            for (int month = 1; month <= 12; month += 1) {
                days.add(daycount);
                switch (month) {
                case 1: temp = 31;
                        break;
                case 2:
                    if (leapyear) {
                        temp  = 29;
                    } else {
                        temp = 28;
                    }
                    break;
                case 3: temp = 31;
                    break;
                case 4: temp = 30;
                    break;
                case 5: temp = 31;
                    break;
                case 6: temp = 30;
                        break;
                case 7: temp = 31;
                    break;
                case 8: temp = 31;
                    break;
                case 9: temp = 30;
                    break;
                case 10: temp = 31;
                    break;
                case 11: temp = 30;
                    break;
                case 12: temp = 31;
                    break;
                default: temp = 0;
                    break;
                }
                daycount += temp;
            }
        }
        return days;
    }

    /** Returns true if YEAR is a leapyear. */
    private static boolean leapyear(int year) {
        boolean is;

        if (year % 4 == 0) {
            if (year % 100 != 0) {
                is = true;
            } else {
                is = year % 400 == 0;
            }
        } else {
            is  = false;
        }

        return is;
    }
}
