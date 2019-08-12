import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** @author Nicholas Chiu */
public class Problem42 {

    /** Coded triangle numbers ARGS */
    public static void main(String[] args) {
        final int largest_letter_score = 26;

        ArrayList<String> words = input();
        words = dequote_and_longest(parser(words, ","));

        int longest = Integer.valueOf(words.get(0));
        int maximum_score = largest_letter_score * longest;

        ArrayList<Integer> tri_nums = triangle_numbers_upto(maximum_score);

        int max_number = tri_nums.get(tri_nums.size() - 1);
        int[] is_triangle = new int[max_number + 1];

        for (int i : tri_nums) {
            is_triangle[i] = 1;
        }

        int count = 0;
        for (String word : words) {
            int score = wordcore(word);
            if (is_triangle[score] == 1) {
                count += 1;
            }
        }
        System.out.println(count);
    }


    /** Returns a list of the triangle numbers until the first triangle
     *  number passing MAXIMUM. */
    private static ArrayList<Integer> triangle_numbers_upto(int maximum) {
        int curr_index = 1;
        int curr = 1;
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(curr);

        while (curr <= maximum) {
            curr_index += 1;
            curr = (int) (curr_index * (curr_index + 1) * 0.5);
            ret.add(curr);
        }

        return ret;
    }


    /** Returns the wordscore of WORD. */
    private static int wordcore(String word) {
        String[] letters = word.split("");
        int score = 0;

        for (String letter : letters) {
            score += letterscore(letter);
        }

        return score;
    }

    /** Returns the letterscore of a LETTER. */
    private static int letterscore(String letter) {
        int score; letter = letter.toUpperCase();
        switch (letter) {
            case "A": score = 1;
                break;
            case "B": score = 2;
                break;
            case "C": score = 3;
                break;
            case "D": score = 4;
                break;
            case "E": score = 5;
                break;
            case "F": score = 6;
                break;
            case "G": score = 7;
                break;
            case "H": score = 8;
                break;
            case "I": score = 9;
                break;
            case "J": score = 10;
                break;
            case "K": score = 11;
                break;
            case "L": score = 12;
                break;
            case "M": score = 13;
                break;
            case "N": score = 14;
                break;
            case "O": score = 15;
                break;
            case "P": score = 16;
                break;
            case "Q": score = 17;
                break;
            case "R": score = 18;
                break;
            case "S": score = 19;
                break;
            case "T": score = 20;
                break;
            case "U": score = 21;
                break;
            case "V": score = 22;
                break;
            case "W": score = 23;
                break;
            case "X": score = 24;
                break;
            case "Y": score = 25;
                break;
            case "Z": score = 26;
                break;
            default: score = 0;
                break;
        }
        return score;
    }

    /** Returns the input numbers as a list. */
    private static ArrayList<String> input() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter words: ");

        ArrayList<String> results = new ArrayList<>();
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();

            if (line == null || line.isEmpty()) {
                break;
            } else {
                results.add(line);
            }
        }
        reader.close();

        return results;
    }

    /** Parses LST. Returns an arraylist of the contents.
     *  Each item is delimited by DELIMITER. */
    private static ArrayList<String> parser(ArrayList<String> lst,
                                            String delimiter) {

        ArrayList<String> parsed = new ArrayList<>();

        for (String item : lst) {
            String[] split = item.split(delimiter);
            Collections.addAll(parsed, split);
        }

        return parsed;
    }

    /** Dequotes each item in LST and returns it. Small modification: first element
     *  is a number containing the length of the longest word. */
    private static ArrayList<String> dequote_and_longest(ArrayList<String> lst) {

        ArrayList<String> dequoted = new ArrayList<String>();

        int longest = 0;
        for (String item : lst) {

            int len = item.length();
            if (len - 2 > longest) {
                longest = len - 2;
            }

            dequoted.add(item.substring(1, len - 1));
        }

        dequoted.add(0, Integer.toString(longest));

        return dequoted;
    }

}
