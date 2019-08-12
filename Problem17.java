
import java.util.HashMap;


/** @author Nicholas Chiu */
public class Problem17 {

    /** Find the number of letters used to write out the numbers 1
     *  through to N. ARGS */
    public static void main(String[] args) {
        System.out.println(lettercount(1000));
    }

    /** Returns the number of letters used to write out all the numbers up to N. */
    /** Caps out at 10000. */
    private static int lettercount(int n) {
        HashMap<Integer, Integer> letters = new HashMap<>();
        letters.put(1, 3);
        letters.put(2, 3);
        letters.put(3, 5);
        letters.put(4, 4);
        letters.put(5, 4);
        letters.put(6, 3);
        letters.put(7, 5);
        letters.put(8, 5);
        letters.put(9, 4);
        letters.put(10, 3);
        letters.put(11, 6);
        letters.put(12, 6);
        letters.put(13, 8);
        letters.put(14, 8);
        letters.put(15, 7);
        letters.put(16, 7);
        letters.put(17, 9);
        letters.put(18, 8);
        letters.put(19, 8);
        letters.put(20, 6);
        letters.put(30, 6);
        letters.put(40, 5);
        letters.put(50, 5);
        letters.put(60, 5);
        letters.put(70, 7);
        letters.put(80, 6);
        letters.put(90, 6);
        letters.put(100, 7);
        letters.put(1000, 11);

        int count = 0;
        for (int i = 1; i <= 1000; i += 1) {
            if (letters.containsKey(i)) {
                count += letters.get(i);
            } else {

                int temp = 0;

                if (i < 30) {
                    temp = letters.get(20) + letters.get(i - 20);
                } else if (i < 40) {
                    temp = letters.get(30) + letters.get(i - 30);
                } else if (i < 50) {
                    temp = letters.get(40) + letters.get(i - 40);
                } else if (i < 60) {
                    temp = letters.get(50) + letters.get(i - 50);
                } else if (i < 70) {
                    temp = letters.get(60) + letters.get(i - 60);
                } else if (i < 80) {
                    temp = letters.get(70) + letters.get(i - 70);
                } else if (i < 90) {
                    temp = letters.get(80) + letters.get(i - 80);
                } else if (i < 100) {
                    temp = letters.get(90) + letters.get(i - 90);
                } else if (i < 200) {
                    temp = 3 + letters.get(100) + 3 + letters.get(i - 100);
                } else if (i == 200) {
                    temp = letters.get(2) + letters.get(100);
                } else if (i < 300) {
                    temp = letters.get(2) + letters.get(100) + 3 + letters.get(i - 200);
                }  else if (i == 300) {
                    temp = letters.get(3) + letters.get(100);
                } else if (i < 400) {
                    temp = letters.get(3) + letters.get(100) + 3 + letters.get(i - 300);
                }  else if (i == 400) {
                    temp = letters.get(4) + letters.get(100);
                } else if (i < 500) {
                    temp = letters.get(4) + letters.get(100) + 3 + letters.get(i - 400);
                }  else if (i == 500) {
                    temp = letters.get(5) + letters.get(100);
                } else if (i < 600) {
                    temp = letters.get(5) + letters.get(100) + 3 + letters.get(i - 500);
                } else if (i == 600) {
                    temp = letters.get(6) + letters.get(100);
                } else if (i < 700) {
                    temp = letters.get(6) + letters.get(100) + 3 + letters.get(i - 600);
                }  else if (i == 700) {
                    temp = letters.get(7) + letters.get(100);
                } else if (i < 800) {
                    temp = letters.get(7) + letters.get(100) + 3 + letters.get(i - 700);
                }  else if (i == 800) {
                    temp = letters.get(8) + letters.get(100);
                } else if (i < 900) {
                    temp = letters.get(8) + letters.get(100) + 3 + letters.get(i - 800);
                }  else if (i == 900) {
                    temp = letters.get(9) + letters.get(100);
                } else if (i < 1000) {
                    temp = letters.get(9) + letters.get(100) + 3 + letters.get(i - 900);
                }

                count += temp;
                letters.put(i, temp);

            }
        }

        return count + 3;
    }
}
