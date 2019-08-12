
import java.util.ArrayList;
import java.util.HashMap;

/** @author Nicholas Chiu */
public class Problem61 {

    /** Cyclical Figurate Numbers. ARGS */
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        ArrayList<HashMap<Integer, ArrayList<Integer>> > all_split = new ArrayList<>();
        all.add(null);
        all.add(null);
        all.add(null);
        all_split.add(null);
        all_split.add(null);
        all_split.add(null);


        int max = 10000;
        int min = 1000;

        for (int constant = 1; constant < 7; constant += 1) {

            int index = 2;
            int val = constant + 2;
            ArrayList<Integer> vals = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> vals_split =  new HashMap<>();

            int first_two;
            int last_two;

            while (val < min) {
                val += ((constant * index) + 1);
                index += 1;
            }
            while (val < max) {
                vals.add(val);
                first_two = val / 100;
                last_two = val % 100;
                if (vals_split.containsKey(first_two)) {
                    vals_split.get(first_two).add(last_two);
                } else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(last_two);
                    vals_split.put(first_two, temp);
                }
                val += ((constant * index) + 1);
                index += 1;
            }

            all.add(vals);
            all_split.add(vals_split);

        }


        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 3; i <= 8; i += 1) {
            indices.add(i);
        }
        ArrayList<ArrayList<Integer>> ind_permuted = Helpers.lexicographic_permuter(6, indices);

        boolean found = false;

        ArrayList<Integer> solution = new ArrayList<Integer>();
        while (!found) {

            for (ArrayList<Integer> curr_perm : ind_permuted) {

                ArrayList<Integer> first_figurates = all.get(curr_perm.get(0));
                HashMap<Integer, ArrayList<Integer>> two = all_split.get(curr_perm.get(1));
                HashMap<Integer, ArrayList<Integer>> three = all_split.get(curr_perm.get(2));
                HashMap<Integer, ArrayList<Integer>> four = all_split.get(curr_perm.get(3));
                HashMap<Integer, ArrayList<Integer>> five = all_split.get(curr_perm.get(4));
                HashMap<Integer, ArrayList<Integer>> six = all_split.get(curr_perm.get(5));

                for (int first : first_figurates) {
                    int first_start = first / 100;
                    int second_start = first % 100;
                    ArrayList<Integer> second_ends = two.get(second_start);
                    if (second_ends != null) {
                        for (int third_start : second_ends) {
                            ArrayList<Integer> third_ends = three.get(third_start);
                            if (third_ends != null) {
                                for (int fourth_start : third_ends) {
                                    ArrayList<Integer> fourth_ends = four.get(fourth_start);
                                    if (fourth_ends != null) {
                                        for (int fifth_start : fourth_ends) {
                                            ArrayList<Integer> fifth_ends = five.get(fifth_start);
                                            if (fifth_ends != null) {
                                                for (int sixth_start : fifth_ends) {
                                                    ArrayList<Integer> sixth_ends = six.get(sixth_start);
                                                    if (sixth_ends != null) {
                                                        for (int sixth_end : sixth_ends) {
                                                            if (sixth_end == first_start) {
                                                                solution.add(first);
                                                                solution.add(Helpers.concatenate(second_start, third_start));
                                                                solution.add(Helpers.concatenate(third_start, fourth_start));
                                                                solution.add(Helpers.concatenate(fourth_start, fifth_start));
                                                                solution.add(Helpers.concatenate(fifth_start, sixth_start));
                                                                solution.add(Helpers.concatenate(sixth_start, sixth_end));
                                                                found = true;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (found) {
                                                        break;
                                                    }
                                                }
                                            }
                                            if (found) {
                                                break;
                                            }
                                        }
                                    }
                                    if (found) {
                                        break;
                                    }
                                }
                            }
                            if (found) {
                                break;
                            }
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
        }

        int sum = 0;
        for (int s : solution) {
            sum += s;
        }
        System.out.println(sum);
    }

}


