// max subseq

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {10, 5, 4, 3};
        (new MZS()).find(ar);
    }
}

class MZS
{
    public void find(int[] ar)
    {
        int[] dp = new int[ar.length];
        int max = 0;
        for (int i=0; i<ar.length; ++i)
        {
            dp[i] = ar[i];
            max = Math.max(max, dp[i]);
        }

        for (int i=1; i<ar.length; ++i)
        {
            for (int j=0; j<i; ++j)
            {
                if (ar[i] > ar[j] && dp[i] < ar[i]+dp[j] )
                {
                    dp[i] = ar[i]+dp[j];
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.print(max);
    }
}