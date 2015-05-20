// minimum number of coins required to make change
// online q1, set 24

public class Main
{
    public static void main(String[] er)
    {
        Integer[] coins = {3,5};
        MinCount mc = new MinCount(coins);
        System.out.print(mc.findMinCoins(11));
    }
}

class MinCount
{
    private Integer[] coins;

    public MinCount(Integer[] coins)
    {
        this.coins = coins;
    }

    public Integer findMinCoins(Integer sum)
    {
        Integer[] dp = new Integer[sum+1];
        for(Integer i=0; i<dp.length; ++i)
        {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for (Integer dpCursor=1; dpCursor<dp.length; ++dpCursor)
        {
            for (Integer coinCursor=0; coinCursor<coins.length && coins[coinCursor] <= dpCursor; ++coinCursor)
            {
                Integer thisValue = dp[dpCursor - coins[coinCursor]] + 1;
                if (thisValue > 0 && thisValue < dp[dpCursor])
                {
                    dp[dpCursor] = dp[dpCursor - coins[coinCursor]] + 1;
                }
            }
        }

        return dp[sum];
    }
}