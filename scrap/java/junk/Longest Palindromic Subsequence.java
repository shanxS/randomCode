// Longest Palindromic Subsequence

public class Main
{
    public static void main(String[] er)
    {
        String s = "BBABCBCAB";
        LongestPalin lp = new LongestPalin();
        System.out.print(lp.find(s.toCharArray()));
    }
}

class LongestPalin
{
    private char[] s;
    private Integer[][] dp;

    public Integer find(char[] s)
    {
        this.s = s;
        dp = new Integer[s.length][s.length];

        Integer max = Integer.MIN_VALUE;
        for (Integer i=1; i<s.length; ++i)
        {
            Integer evenMax = contemplate(i-1, i);
            Integer oddMax = 1 + contemplate(i-1, i+1);

            Integer thisMax = Math.max(evenMax, oddMax);
            max = Math.max(thisMax, max);
        }

        return max;
    }

    private Integer contemplate(int start, Integer end)
    {
        if (!validPosition(start, end))
        {
            return Integer.MIN_VALUE;
        }
        else if (dp[start][end] != null)
        {
            return dp[start][end];
        }
        else if (s[start] != s[end])
        {
            dp[start][end] = Math.max(contemplate(start-1, end),
                    contemplate(start, end+1));
            return dp[start][end];
        }

        dp[start][end] = 2 + Math.max(0, contemplate(start-1, end+1));
        return dp[start][end];
    }

    private boolean validPosition(int start, Integer end)
    {
        return start>=0 && end<s.length;
    }
}