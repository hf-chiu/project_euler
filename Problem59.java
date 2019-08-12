
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/** @author Nicholas Chiu */
public class Problem59 {


    /** XOR Decryption.*/
    public static void main(String[] args){
        ArrayList<Integer> text = inputter().get(0);
        int size = text.size();
        int mod = size / 3;
        int rem = size - mod * 3;

        int[] a_candidates = new int[]{101};
        int[] b_candidates = new int[]{120};
        int[] c_candidates = new int[]{112};

        for (int a : a_candidates) {
            for (int b : b_candidates) {
                for (int c : c_candidates) {
                    ArrayList<Integer> code = new ArrayList<>();
                    for (int i = 0; i < mod; i += 1) {
                        code.add(a);
                        code.add(b);
                        code.add(c);
                    }
                    if (rem == 1) {
                        code.add(a);
                    } else if (rem == 2) {
                        code.add(a);
                        code.add(b);
                    }
                    int count = 0;
                    for (int i = 0; i < size; i += 1) {
                        count += (text.get(i) ^ code.get(i));
                    }

                    System.out.println(count);

                }
            }
        }

    }

    private static ArrayList<ArrayList<Integer>> inputter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter encrypted text: ");

        ArrayList<ArrayList<Integer>> text = new ArrayList<>();
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line == null || line.isEmpty()) {
                break;
            } else {
                ArrayList<Integer> pline = new ArrayList<>();
                String[] split = line.split(",");
                for (String s : split) {
                    pline.add(Integer.valueOf(s));
                }
                text.add(pline);
            }
        }
        reader.close();

        return text;
    }

}
