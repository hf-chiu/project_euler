
import java.util.ArrayList;

/** @author Nicholas Chiu */
public class Problem31 {

    /** Coin Sums. */
    public static void main(String[] args) {
        final int N = 200;

        ArrayList<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(2);
        coins.add(5);
        coins.add(10);
        coins.add(20);
        coins.add(50);
        coins.add(100);
        coins.add(200);
        System.out.println(number_of_ways(N, coins));

    }

    /** Returns number of ways to make AMOUNT with only coins in COINS. */
    private static int number_of_ways(int amount, ArrayList<Integer> coins) {
        int[] ways = new int[amount + 1];

        ways[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i += 1) {
                if (i - coin >= 0) {
                    ways[i] += ways[i - coin];
                }
            }
        }


        return ways[amount];
    }


}
