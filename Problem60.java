
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem60 {

    /** Prime Pair Sets. */
    public static void main(String[] args) {
        final int num_pair_sets = 5;
        final int guess = 100;

        ArrayList<ArrayList<Long>> primes = pair_set(num_pair_sets, guess);
        for (ArrayList<Long> p : primes) {
            long sum = 0;
            for (long l : p) {
                sum += l;
            }
            System.out.println(sum);
        }


    }

    /** Returns a list of the smallest set (defined by sum) of ENOUGH primes
     *  which fulfill the primal pairwise concatentation quality. To ensure speed
     *  uses SUPREMUM as a best guess for the highest that the lowest prime in the
     *  set will be. */
    private static ArrayList<ArrayList<Long>> pair_set(int enough, int supremum) {

        ArrayList<ArrayList<ArrayList<Long>>> concatenatable = new ArrayList<>();
        ArrayList<ArrayList<Long>> blank_zero = new ArrayList<>();
        ArrayList<ArrayList<Long>> blank_one = new ArrayList<>();
        ArrayList<ArrayList<Long>> blank_two = new ArrayList<>();
        ArrayList<Long> three = new ArrayList<>();
        three.add((long) 3);
        ArrayList<Long> seven = new ArrayList<>();
        seven.add((long) 7);
        blank_one.add(three);
        blank_one.add(seven);
        ArrayList<Long> three_seven = new ArrayList<>();
        three_seven.add((long) 3);
        three_seven.add((long) 7);
        blank_two.add(three_seven);

        concatenatable.add(blank_zero);
        concatenatable.add(blank_one);
        concatenatable.add(blank_two);

        long curr = 11;
        int num_levels = 2;

        while (true) {

            if (Helpers.isprime(curr)) {

                if (curr < supremum) {
                    ArrayList<Long> curr_lst = new ArrayList<>();
                    curr_lst.add(curr);
                    concatenatable.get(1).add(curr_lst);
                }

                System.out.println(curr);

                int size = concatenatable.size();

                ArrayList<ArrayList<ArrayList<Long>>> new_lst = new ArrayList<>();
                new_lst.addAll(concatenatable);

                for (int i = 1; i < size; i += 1) {
                    ArrayList<ArrayList<Long>> curr_level = concatenatable.get(i);

                    for (ArrayList<Long> curr_set : curr_level) {

                        ArrayList<Long> new_set = new ArrayList<>();
                        boolean can_add = true;
                        for (long curr_prime : curr_set) {
                            long l_add = Helpers.concatenate(curr_prime, curr);
                            long r_add = Helpers.concatenate(curr, curr_prime);
                            if (!Helpers.isprime(l_add) || !Helpers.isprime(r_add)) {
                                can_add = false;
                                break;
                            }
                        }

                        if (can_add) {
                            new_set.addAll(curr_set);
                            new_set.add(curr);

                            if (num_levels < i + 1) {
                                ArrayList<ArrayList<Long>> temp = new ArrayList<>();
                                new_lst.add(temp);
                                num_levels += 1;
                            }
                            new_lst.get(i + 1).add(new_set);
                        }

                    }
                }
                concatenatable = new_lst;
            }

            int size = concatenatable.size();

            if (size == enough + 1) {
                break;
            }

            curr += 1;
        }

        return concatenatable.get(concatenatable.size() - 1);
    }

}
