import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer[] dp;

    public static void main(String[] er)
    {
        try
        {
            dp = new Integer[1000];

            Integer T = Integer.parseInt(bufferedReader.readLine());
            Integer N = Integer.parseInt(bufferedReader.readLine());
            String[] X = bufferedReader.readLine().split(" ");

            Integer count = 0;
            for (String x : X)
            {
                Integer num  = Integer.parseInt(x);
                if (!isCoprime(T, num))
                {
                    ++count;
                    dp[num] = 1;
                }
                else
                {
                    dp[num] = 0;
                }
            }

            System.out.print(count);


        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }

    private static boolean isCoprime(Integer T, Integer num)
    {
        if (dp[num] != null)
        {
            return dp[num] == 1;
        }


        // If both numbers are even, then they are not coprime.
        if (((T | num) & 1) == 0) return false;

        // Now at least one number is odd. Eliminate all the factors of 2 from u.
        while ((T & 1) == 0) T >>= 1;

        // One is coprime with everything else by definition.
        if (T == 1) return true;

        do {
            // Eliminate all the factors of 2 from v, because we know that u and v do not have any 2's in common.
            while ((num & 1) == 0) num >>= 1;

            // One is coprime with everything else by definition.
            if (num == 1) return true;

            // Swap if necessary to ensure that v >= u.
            if (T > num) {
                Integer t = num;
                num = T;
                T = t;
            }

            // We know that GCD(u, v) = GCD(u, v - u).
            num -= T;
        } while (num != 0);

        // When we reach here, we have v = 0 and GCD(u, v) = current value of u, which is greater than 1.
        return false;
    }
}