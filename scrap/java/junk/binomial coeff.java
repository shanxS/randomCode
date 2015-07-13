// binomial coeff

public class Main
{
    public static void main(String[] er)
    {
        BinomialCoeff bc = new BinomialCoeff();
        System.out.print(bc.find(5,2));
    }
}

class BinomialCoeff
{
    private Integer[][] dp;

    public Integer find(Integer n, Integer k)
    {
        dp = new Integer[n+1][k+1];

        return contemplate(n - 1, k - 1) + contemplate(n - 1, k);
    }

    private Integer contemplate(int n, int k)
    {
        if (n<0 || k<0)
        {
            return 0;
        }

        if (dp[n][k] != null)
        {
            return dp[n][k];
        }
        else if (n==0 || n==k)
        {
            dp[n][k] = 1;
            return dp[n][k];
        }

        dp[n][k] = contemplate(n - 1, k - 1) + contemplate(n - 1, k);
        return dp[n][k];
    }
}