
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/** @author Nicholas Chiu */
public class Problem18 {
    /** Maximum Path Sum I */
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> pyramid = inputter();
        ArrayList<Integer> final_row = dp_pathsum(pyramid);

        int total = 0;
        for (int i : final_row) {
            if (i > total) {
                total = i;
            }
        }
        System.out.println(total);
    }

    private static ArrayList<ArrayList<Integer>> inputter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter pyramid: ");

        ArrayList<ArrayList<Integer>> pyramid = new ArrayList<>();
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line == null || line.isEmpty()) {
                break;
            } else {
                ArrayList<Integer> pline = new ArrayList<>();
                String[] split = line.split(" ");
                for (String s : split) {
                    pline.add(Integer.valueOf(s));
                }
                pyramid.add(pline);
            }
        }
        reader.close();

        return pyramid;
    }

    public static ArrayList<Integer> dp_pathsum(ArrayList<ArrayList<Integer>> pyramid) {
        int levels = pyramid.size();

        for (int i = 1; i < levels; i += 1) {
            ArrayList<Integer> prev_row = pyramid.get(i - 1);
            ArrayList<Integer> old_row = pyramid.get(i);
            ArrayList<Integer> new_row = new ArrayList<>();

            int row_size = old_row.size();
            new_row.add(old_row.get(0) + prev_row.get(0));
            for (int j = 1; j < row_size - 1; j += 1) {
                int poss1 = prev_row.get(j - 1);
                int poss2 = prev_row.get(j);
                int curr = old_row.get(j);
                new_row.add(Math.max(poss1 + curr, poss2 + curr));
            }
            new_row.add(old_row.get(row_size - 1) + prev_row.get(row_size - 2));

            pyramid.set(i, new_row);
        }

        return pyramid.get(levels - 1);
    }
}
