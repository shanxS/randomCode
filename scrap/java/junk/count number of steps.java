// count number of steps

public class Main
{
    public static void main(String[] er)
    {
        Finder f = new Finder();
        System.out.print(f.find(4));
    }
}

class Finder
{
    private Integer[] dp;

    public Integer find(Integer n)
    {
        dp = new Integer[n+1];

        return count(n);
    }

    private Integer count(Integer n)
    {
        if (n<0)
        {
            return 0;
        }
        else if (dp[n] != null)
        {
            return dp[n];
        }
        else if (n == 0)
        {
            dp[n] = 1;
            return dp[n];
        }

        dp[n] = count(n-1) + count(n-2);
        return dp[n];
    }
}