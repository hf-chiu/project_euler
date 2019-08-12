
import java.util.HashSet;

/** @author Nicholas Chiu */
public class Problem77 {

    /** Prime Summations */
    public static void main(String[] args) {
        final int infimum = 5000;

        HashSet<Integer> primes = new HashSet<>();
        int curr_prime = 3;
        primes.add(2);
        primes.add(3);
        int curr_amount = 2;

        while (true) {

            if (curr_amount > curr_prime) {
                while (true) {
                    curr_prime += 1;
                    if (Helpers.isprime(curr_prime)) {
                        break;
                    }
                }
            }

            int ways = number_of_ways(curr_amount, primes);

            if (ways > infimum) {
                break;
            } else {
                curr_amount += 1;
                primes.add(curr_prime);
            }
        }

        System.out.println(curr_amount);

    }

    /** Returns number of ways to make AMOUNT with only coins in COINS. */
    private static int number_of_ways(int amount, HashSet<Integer> coins) {
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
