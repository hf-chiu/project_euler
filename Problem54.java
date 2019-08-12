
import java.lang.reflect.Array;
import java.util.*;


/** @author Nicholas Chiu */
public class Problem54 {


    /** Poker Hands.*/
    public static void main(String[] args){
        ArrayList<ArrayList<String>> hands = inputter();
        ArrayList<ArrayList<String>> p1 = new ArrayList<>();
        ArrayList<ArrayList<String>> p2 = new ArrayList<>();

        for (int i = 0; i < hands.size(); i += 2) {
            p1.add(hands.get(i));
            p2.add(hands.get(i + 1));
        }

        int count = 0;
        for (int i = 0; i < p1.size(); i += 1) {
            System.out.println(p1.get(i) + " || " + p2.get(i));
            if (scorer(p1.get(i), p2.get(i)) == -1) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    /** Takes in HAND1 and HAND2 and returns -1 if player one wins, 1 if player 2 wins
     *  and 0 if they tie. */
    private static int scorer(ArrayList<String> hand1, ArrayList<String> hand2) {
        HashMap<String, Integer> converter = new HashMap<>();
        converter.put("2", 2);
        converter.put("3", 3);
        converter.put("4", 4);
        converter.put("5", 5);
        converter.put("6", 6);
        converter.put("7", 7);
        converter.put("8", 8);
        converter.put("9", 9);
        converter.put("T", 10);
        converter.put("J", 11);
        converter.put("Q", 12);
        converter.put("K", 13);
        converter.put("A", 14);


        HashMap<Integer, Integer> hand1_vals = new HashMap<>();
        HashMap<String, Integer> hand1_suits = new HashMap<>();
        HashMap<Integer, Integer> hand2_vals = new HashMap<>();
        HashMap<String, Integer> hand2_suits = new HashMap<>();
        int h1_max = 0;
        int h2_max = 0;

        for (String card : hand1) {
            String[] card_arr = card.split("");
            Integer num = converter.get(card_arr[0]);
            String suit = card_arr[1];

            if (!hand1_vals.containsKey(num)) {
                hand1_vals.put(num, 1);
            } else {
                int temp = hand1_vals.get(num) + 1;
                if (temp > h1_max) {
                    h1_max = temp ;
                }
                hand1_vals.put(num, temp);
            }

            if (!hand1_suits.containsKey(suit)) {
                hand1_suits.put(suit, 1);
            } else {
                hand1_suits.put(suit, hand1_suits.get(suit) + 1);
            }
        }

        for (String card : hand2) {
            String[] card_arr = card.split("");
            Integer num = converter.get(card_arr[0]);
            String suit = card_arr[1];

            if (!hand2_vals.containsKey(num)) {
                hand2_vals.put(num, 1);
            } else {
                int temp = hand2_vals.get(num) + 1;
                if (temp > h2_max) {
                    h2_max = temp ;
                }
                hand2_vals.put(num, temp);
            }

            if (!hand2_suits.containsKey(suit)) {
                hand2_suits.put(suit, 1);
            } else {
                hand2_suits.put(suit, hand2_suits.get(suit) + 1);
            }
        }

        ArrayList<Integer> h1_vals = new ArrayList<>();
        h1_vals.addAll(hand1_vals.keySet());
        h1_vals.sort(Comparator.naturalOrder());
        int h1_num_keys = h1_vals.size();
        ArrayList<Integer> h2_vals = new ArrayList<>();
        h2_vals.addAll(hand2_vals.keySet());
        h2_vals.sort(Comparator.naturalOrder());
        int h2_num_keys = h2_vals.size();

        boolean h1_flush = hand1_suits.keySet().size() == 1;
        boolean h2_flush = hand2_suits.keySet().size() == 1;

        int h1_score = 0;
        int h2_score = 0;

        if (h1_num_keys == 5) {

            int start = h1_vals.get(0);
            boolean is_straight = false;
            int count = 0;
            for (int i = 1; i < 5; i += 1) {
                if (h1_vals.get(i) - start == i) {
                    count += 1;
                }
            }
            if (count == 4) {
                is_straight = true;
            }
            if (is_straight && h1_flush) {

                if (start == 10) {
                    h1_score = 9;
                } else {
                    h1_score = 8;
                }

            } else if (h1_flush) {
                h1_score = 5;
            } else if (is_straight) {
                h1_score = 4;
            }

        } else if (h1_num_keys == 4) {

            if (h1_flush) {
                h1_score = 5;
            } else if (h1_max == 2) {
                h1_score = 1;
            }

        } else if (h1_num_keys == 3) {

            if (h1_flush) {
                h1_score = 5;
            } else if (h1_max == 3) {
                h1_score = 3;
            } else if (h1_max == 2) {
                h1_score = 2;
            }

        } else {

            if (h1_max == 4) {
                h1_score = 7;
            } else if (h1_max == 3) {
                h1_score = 6;
            }

        }
        if (h2_num_keys == 5) {

            int start = h2_vals.get(0);
            boolean is_straight = false;
            int count = 0;
            for (int i = 1; i < 5; i += 1) {
                if (h2_vals.get(i) - start == i) {
                    count += 1;
                }
            }
            if (count == 4) {
                is_straight = true;
            }
            if (is_straight && h2_flush) {

                if (start == 10) {
                    h2_score = 9;
                } else {
                    h2_score = 8;
                }

            } else if (h2_flush) {
                h2_score = 5;
            } else if (is_straight) {
                h2_score = 4;
            }

        } else if (h2_num_keys == 4) {

            if (h2_flush) {
                h2_score = 5;
            } else if (h2_max == 2) {
                h2_score = 1;
            }

        } else if (h2_num_keys == 3) {

            if (h2_flush) {
                h2_score = 5;
            } else if (h2_max == 3) {
                h2_score = 3;
            } else if (h2_max == 2) {
                h2_score = 2;
            }

        } else {

            if (h2_max == 4) {
                h2_score = 7;
            } else if (h2_max == 3) {
                h2_score = 6;
            }

        }

        System.out.println(h1_score + " ||  " + h2_score);
        System.out.println();

        if (h1_score > h2_score) {
            return -1;
        } else if (h1_score < h2_score) {
            return 1;
        } else {

            int winner = 0;

            if (h1_score == 0) {


                for (int i = 4; i >= 0; i -= 1) {
                    if (h1_vals.get(i) > h2_vals.get(i)) {
                        winner = -1;
                        break;
                    } else if (h1_vals.get(i) < h2_vals.get(i)) {
                        winner = 1;
                        break;
                    }
                }


            } else if (h1_score == 1) {


                ArrayList<Integer> h1_singles = new ArrayList<>();
                int h1_pair = 0;
                for (int i: h1_vals) {
                    int num = hand1_vals.get(i);
                    if (num == 2) {
                        h1_pair = i;
                    } else {
                        h1_singles.add(i);
                    }
                }
                ArrayList<Integer> h2_singles = new ArrayList<>();
                int h2_pair = 0;
                for (int i: h2_vals) {
                    int num = hand2_vals.get(i);
                    if (num == 2) {
                        h2_pair = i;
                    } else {
                        h2_singles.add(i);
                    }
                }

                if (h1_pair > h2_pair) {
                    winner = -1;
                } else if (h1_pair < h2_pair) {
                    winner = 1;
                } else {

                    for (int i = 2; i >= 0; i -= 1) {
                        int h1_single = h1_singles.get(i);
                        int h2_single = h2_singles.get(i);
                        if (h1_single > h2_single) {
                            winner = -1;
                            break;
                        } else if (h1_single < h2_single) {
                            winner = 1;
                            break;
                        }
                    }

                }


            } else if (h1_score == 2) {


                ArrayList<Integer> h1_pairs = new ArrayList<>();
                int h1_single = 0;
                for (int i: h1_vals) {
                    int num = hand1_vals.get(i);
                    if (num == 2) {
                        h1_pairs.add(i);
                    } else {
                        h1_single = i;
                    }
                }
                ArrayList<Integer> h2_pairs = new ArrayList<>();
                int h2_single = 0;
                for (int i: h2_vals) {
                    int num = hand2_vals.get(i);
                    if (num == 2) {
                        h2_pairs.add(i);
                    } else {
                        h2_single = i;
                    }
                }
                for (int i = 1; i >= 0; i -= 1) {
                    int h1_pair = h1_pairs.get(i);
                    int h2_pair = h2_pairs.get(i);
                    if (h1_pair > h2_pair) {
                        winner = -1;
                        break;
                    } else if (h1_pair < h2_pair) {
                        winner = 1;
                        break;
                    }
                }
                if (winner == 0) {

                    if (h1_single > h2_single) {
                        winner = -1;
                    } else if (h1_single < h2_single) {
                        winner = 1;
                    }

                }


            } else if (h1_score == 3) {


                int h1_triple = 0;
                for (int i: h1_vals) {
                    int num = hand1_vals.get(i);
                    if (num == 3) {
                        h1_triple = i;
                    }
                }
                int h2_triple = 0;
                for (int i: h2_vals) {
                    int num = hand2_vals.get(i);
                    if (num == 3) {
                        h2_triple = i;
                    }
                }

                if (h1_triple > h2_triple) {
                    winner = -1;
                } else if (h1_triple < h2_triple) {
                    winner = 1;
                }


            } else if (h1_score == 4) {


                if (h1_vals.get(0) > h2_vals.get(0)) {
                    winner = -1;
                } else if (h1_vals.get(0) < h2_vals.get(0)) {
                    winner = 1;
                }


            } else if (h1_score == 5) {


                for (int i = 4; i >= 0; i -= 1) {
                    if (h1_vals.get(i) > h2_vals.get(i)) {
                        winner = -1;
                        break;
                    } else if (h1_vals.get(i) < h2_vals.get(i)) {
                        winner = 1;
                        break;
                    }
                }


            } else if (h1_score == 6) {


                int h1_triple = 0;
                for (int i: h1_vals) {
                    int num = hand1_vals.get(i);
                    if (num == 3) {
                        h1_triple = i;
                    }
                }
                int h2_triple = 0;
                for (int i: h2_vals) {
                    int num = hand2_vals.get(i);
                    if (num == 3) {
                        h2_triple = i;
                    }
                }
                if (h1_triple > h2_triple) {
                    winner = -1;
                } else if (h1_triple < h2_triple) {
                    winner = 1;
                }


            } else if (h1_score == 7) {


                int h1_quad = 0;
                for (int i: h1_vals) {
                    int num = hand1_vals.get(i);
                    if (num == 4) {
                        h1_quad = i;
                    }
                }
                int h2_quad = 0;
                for (int i: h2_vals) {
                    int num = hand2_vals.get(i);
                    if (num == 4) {
                        h2_quad = i;
                    }
                }
                if (h1_quad > h2_quad) {
                    winner = -1;
                } else if (h1_quad < h2_quad) {
                    winner = 1;
                }


            } else if (h1_score == 8) {


                if (h1_vals.get(0) > h2_vals.get(0)) {
                    winner = -1;
                } else if (h1_vals.get(0) < h2_vals.get(0)) {
                    winner = 1;
                }


            } else {

                HashMap<String, Integer> suit_converter = new HashMap<>();
                suit_converter.put("D", 1);
                suit_converter.put("C", 2);
                suit_converter.put("H", 3);
                suit_converter.put("S", 4);

                ArrayList<String> h1_suits = new ArrayList<>();
                h1_suits.addAll(hand1_suits.keySet());
                int h1_suit = suit_converter.get(h1_suits.get(0));
                ArrayList<String> h2_suits = new ArrayList<>();
                h2_suits.addAll(hand2_suits.keySet());
                int h2_suit = suit_converter.get(h2_suits.get(0));

                if (h1_suit > h2_suit) {
                    winner = -1;
                } else if (h1_suit < h2_suit) {
                    winner = 1;
                }

            }

            return winner;
        }
    }

    private static ArrayList<ArrayList<String>> inputter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter hands: ");

        ArrayList<ArrayList<String>> text = new ArrayList<>();
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line == null || line.isEmpty()) {
                break;
            } else {
                ArrayList<String> pline_1 = new ArrayList<>();
                ArrayList<String> pline_2 = new ArrayList<>();
                String[] split = line.split(" ");
                int len = split.length / 2;
                for (int i = 0; i < len; i += 1) {
                    pline_1.add(split[i]);
                    pline_2.add(split[i + len]);
                }
                text.add(pline_1);
                text.add(pline_2);
            }
        }
        reader.close();

        return text;
    }

}
