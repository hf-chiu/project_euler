
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/** @author Nicholas Chiu */

public class Problem51 {

    /** Prime Digit Replacements. ARGS */
    public static void main(String[] args) {
        final int enough = 8;

        int num = 2;

        ArrayList<String> f;
        while (true) {
            f = families(num, enough);
            if (f.size() > 0) {
                break;
            } else {
                num += 1;
            }
        }

        int min = Integer.MAX_VALUE;
        for (String candidate : f) {
            ArrayList<String> candidate_str =
                    new ArrayList<>(Arrays.asList(candidate.split("")));

            if (candidate_str.get(0).equals("*")) {

                for (int i = 1; i < 10; i += 1) {
                    StringBuilder sb = new StringBuilder();
                    for (String digit : candidate_str) {
                        if (digit.equals("*")) {
                            sb.append(i);
                        } else {
                            sb.append(digit);
                        }
                    }
                    int formed = Integer.valueOf(sb.toString());
                    if (formed < min) {
                        min = formed;
                        break;
                    }
                }

            } else {

                for (int i = 0; i < 10; i += 1) {
                    StringBuilder sb = new StringBuilder();
                    for (String digit : candidate_str) {
                        if (digit.equals("*")) {
                            sb.append(i);
                        } else {
                            sb.append(digit);
                        }
                    }
                    int formed = Integer.valueOf(sb.toString());
                    if (formed < min) {
                        min = formed;
                        break;
                    }
                }

            }

        }
        System.out.println(min);

    }


    /** Prints all families with with NUM_DIGITS and ENOUGH primes. */
    private static ArrayList<String> families(int num_digits, int enough) {

        ArrayList<String> ret = new ArrayList<>();

        int floor = (int) Math.pow(10, num_digits - 1);
        int floor_copy = floor;
        int ceil = (int) Math.pow(10, num_digits);
        boolean[] is_prime = new boolean[ceil - floor];

        for (int i = floor; i < ceil; i += 1) {
            is_prime[i - floor] = Helpers.isprime(i);
        }

        floor = floor / 10;
        ceil = ceil / 10;

        for (int curr = floor; curr < ceil; curr += 1) {
            int count = 0;
            for (int digit = 1; digit < 10; digit += 1) {
                int temp = Helpers.concatenate(digit, curr);
                if (is_prime[temp - floor_copy]) {
                    count += 1;
                }
                if (9 < digit + enough - count) {
                    break;
                }
            }
            if (count == enough) {
                ret.add("*" + curr);
            }

            for (int i = 1; i < num_digits; i += 1) {
                count = 0;
                for (int digit = 0; digit < 10; digit += 1) {
                    ArrayList<Integer> curr_lst = Helpers.converter(curr);
                    curr_lst.add(i, digit);
                    int temp = (int) Helpers.converter_toint(curr_lst);
                    if (is_prime[temp - floor_copy]) {
                        count += 1;
                    }
                    if (10 < digit + enough - count) {
                        break;
                    }
                }
                if (count == enough) {
                    String[] s = Integer.toString(curr).split("");
                    StringBuilder sb = new StringBuilder();
                    for (int ind = 0; ind < i; ind += 1) {
                        sb.append(s[ind]);
                    }
                    sb.append("*");
                    for (int ind = i; ind < s.length; ind += 1) {
                        sb.append(s[ind]);
                    }
                    ret.add(sb.toString());
                }
            }
        }


        ArrayList<Integer> places = new ArrayList<>();
        for (int i = 1; i < num_digits; i += 1) {
            places.add(i);
        }
        for (int num_stars = 2; num_stars < num_digits; num_stars += 1) {

            floor = floor / 10;
            ceil = ceil / 10;

            for (int curr = floor; curr < ceil; curr += 1) {

                ArrayList<ArrayList<Integer>> stars = star_permuter(num_stars-1, places);

                for (ArrayList<Integer> s : stars) {
                    int count = 0;
                    for (int digit = 1; digit < 10; digit += 1) {
                        ArrayList<Integer> curr_lst = Helpers.converter(curr);
                        curr_lst.add(0, digit);
                        for (int s_ind : s) {
                            curr_lst.add(s_ind, digit);
                        }
                        int temp = (int) Helpers.converter_toint(curr_lst);
                        if (is_prime[temp - floor_copy]) {
                            count += 1;
                        }
                        if (9 < digit + enough - count) {
                            break;
                        }
                    }
                    if (count == enough) {
                        String[] str = Integer.toString(curr).split("");
                        ArrayList<String> str_arr = new ArrayList<>(Arrays.asList(str));
                        str_arr.add(0, "*");
                        for (int s_ind : s) {
                            str_arr.add(s_ind, "*");
                        }
                        StringBuilder sb = new StringBuilder();
                        for (String str_el : str_arr) {
                            sb.append(str_el);
                        }
                        ret.add(sb.toString());
                    }
                }


                stars = star_permuter(num_stars, places);

                for (ArrayList<Integer> s : stars) {
                    int count = 0;
                    for (int digit = 0; digit < 10; digit += 1) {
                        ArrayList<Integer> curr_lst = Helpers.converter(curr);
                        for (int s_ind : s) {
                            curr_lst.add(s_ind, digit);
                        }
                        int temp = (int) Helpers.converter_toint(curr_lst);
                        if (is_prime[temp - floor_copy]) {
                            count += 1;
                        }
                        if (10 < digit + enough - count) {
                            break;
                        }
                    }
                    if (count == enough) {
                        String[] str = Integer.toString(curr).split("");
                        ArrayList<String> str_arr = new ArrayList<>(Arrays.asList(str));
                        for (int s_ind : s) {
                            str_arr.add(s_ind, "*");
                        }
                        StringBuilder sb = new StringBuilder();
                        for (String str_el : str_arr) {
                            sb.append(str_el);
                        }
                        ret.add(sb.toString());
                    }
                }
            }
        }

        return ret;
    }


    /** Returns a list of possible permutations for the stars given there are
     *  NUM_STARS and PLACES to choose from. NUM_STARS must be at most the same
     *  as the size as PLACES. */
    private static ArrayList<ArrayList<Integer>> star_permuter(int num_stars,
                                                               ArrayList<Integer> places) {

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        if (num_stars == 1) {
            for (int p : places) {
                ArrayList<Integer> to_add = new ArrayList<>();
                to_add.add(p);
                ret.add(to_add);
            }
        } else if (num_stars == places.size()) {
            ret.add(places);
        } else {

            int max_ind = places.size() - num_stars;

            for (int ind = 0; ind < max_ind; ind += 1) {
                int el = places.get(ind);

                ArrayList<Integer> places_copy = new ArrayList<>(places);
                for (int i = -1; i < ind; i += 1) {
                    places_copy.remove(0);
                }
                ArrayList<ArrayList<Integer>> recursive = star_permuter(num_stars - 1, places_copy);

                for (ArrayList<Integer> r : recursive) {
                    r.add(0, el);
                    ret.add(r);
                }
            }


            ArrayList<Integer> places_copy = new ArrayList<>(places);
            places_copy.remove(0);
            ArrayList<ArrayList<Integer>> recursive = star_permuter(num_stars, places_copy);
            ret.addAll(recursive);
        }

        return ret;
    }







}


