
import java.util.ArrayList;
import java.util.Scanner;

/** @author Nicholas Chiu */
public class Problem11 {

    /** Find the greatest product of adjacent numbers in an N x N grid. ARGS*/
    public static void main(String[] args) {
        final int N = 20;
        final int length = 4;
        ArrayList<String> given = input();
        int[][] numbers = expander(given, N);
        int horizontal = horizontal(length, numbers);
        int vertical = vertical(length, numbers);
        int leftdiag = leftdiag(length, numbers);
        int rightdiag = rightdiag(length, numbers);
        int max = Math.max(horizontal, vertical);
        max = Math.max(max, leftdiag);
        max = Math.max(max, rightdiag);

        System.out.println(max);
    }

    /** Returns the input numbers as a list. */
    private static ArrayList<String> input() {
        Scanner reader = new Scanner(System.in);
        System.out.println("enter numbers: ");

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

    /** Expands every number in NUMBERS into an array of digits. Returns
     *  the expanded list. Each "number" has LENGTH numbers. */
    private static int[][] expander(ArrayList<String> numbers,
                                             int length) {

        int[][] expanded = new int[length][length];

        for (int index = 0; index < length; index += 1) {
            String number = numbers.get(index);
            String[] split = number.split(" ");
            int[] temp = new int[length];

            for (int i = 0; i < length; i += 1) {
                temp[i] = Integer.parseInt(split[i]);
            }

            expanded[index] = temp;
        }

        return expanded;
    }

    /** Returns the largest product of N horizontally adjacent numbers
     *  in GRID. */
    private static int horizontal(int n, int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i += 1) {
            for (int j = 0; j < grid.length - n; j += 1) {
                int product = 1;
                for (int k = j; k < j + n; k += 1) {
                    product *= grid[i][k];
                }
                if (product > max) {
                    max = product;
                }
            }
        }

        return max;
    }

    /** Returns the largest product of N vertically adjacent numbers
     *  in GRID. */
    private static int vertical(int n, int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i += 1) {
            for (int j = 0; j < grid.length - n; j += 1) {
                int product = 1;
                for (int k = j; k < j + n; k += 1) {
                    product *= grid[k][i];
                }
                if (product > max) {
                    max = product;
                }
            }
        }

        return max;
    }

    /** Returns the largest product of N left diagonally adjacent
     *  numbers in GRID. */
    private static int leftdiag(int n, int[][] grid) {
        int max = 0;

        for (int j = n - 1; j < grid.length; j += 1) {
            int count = (j + 1 - n + 1);
            for (int i = 0; i < count; i += 1) {
                int jindex = j - i;
                int product = 1;
                for (int k = 0; k < n; k += 1) {
                    product *= grid[i + k][jindex - k];
                }
                if (product > max) {
                    max = product;
                }
            }
        }

        for (int i = 1; i <= grid.length - n; i += 1) {
            int count = (grid.length - i - n + 1);
            int adjusted = grid.length - 1;
            for (int j = adjusted; j > adjusted - count; j -= 1) {
                int iindex = i + grid.length - j - 1;
                int product = 1;
                for (int k = 0; k < n; k += 1) {
                    product *= grid[iindex + k][j - k];
                }
                if (product > max) {
                    max = product;
                }
            }
        }

        return max;
    }

    /** Returns the largest product of N right diagonally adjacent
     *  numbers in GRID. */
    private static int rightdiag(int n, int[][] grid) {
        int max = 0;

        for (int j = 0; j <= grid.length - n; j += 1) {
            int count = (grid.length - n - j + 1);
            for (int i = 0; i < count; i += 1) {
                int jindex = i + j;
                int product = 1;
                for (int k = 0; k < n; k += 1) {
                    product *= grid[i + k][jindex + k];
                }
                if (product > max) {
                    max = product;
                }
            }
        }

        for (int i = 1; i <= grid.length - n; i += 1) {
            int count = (grid.length - n - i + 1);
            for (int j = 0; j < count; j += 1) {
                int iindex = i + j;
                int product = 1;
                for (int k = 0; k < n; k += 1) {
                    product *= grid[iindex + k][j + k];
                }
                if (product > max) {
                    max = product;
                }
            }

        }

        return max;
    }
}
