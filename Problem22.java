import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** @author Nicholas Chiu */
public class Problem22 {

    /** Find the name scores of a given list. ARGS */
    public static void main(String[] args) {
        ArrayList<String> names = input();
        names = parser(names, ",");
        names = dequote(names);
        Collections.sort(names);
        long sum = 0;
        long count = 1;

        for (String name : names) {
            sum += (namescore(name) * count);
            count += 1;
        }

        System.out.println(sum);
    }

    /** Returns the namescore of NAME. */
    private static int namescore(String name) {
        String[] letters = name.split("");
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
        System.out.println("enter names: ");

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

    /** Dequotes each item in LST and returns it. */
    private static ArrayList<String> dequote(ArrayList<String> lst) {

        ArrayList<String> dequoted = new ArrayList<String>();

        for (String item : lst) {
            dequoted.add(item.substring(1, item.length() - 1));
        }

        return dequoted;
    }

}
