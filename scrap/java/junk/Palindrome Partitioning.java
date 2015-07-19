// Palindrome Partitioning

public class Main
{
    public static void main(String[] er)
    {
        Main obj = new Main();
        System.out.print(obj.findMinCut("ababbbabbababa"));
//        Integer cost = obj.findMinCut("amaa");
//        System.out.print(cost);

    }

    private char[] characters;
    private Boolean[][] dp;
    private Integer findMinCut(String s)
    {
        characters = s.toCharArray();
        dp = new Boolean[s.length()][s.length()];

        return computeFor(0, characters.length-1);
    }

    private Integer computeFor(Integer start, Integer end)
    {
        if (start == end)
        {
            return 1;
        }

        Integer minCost = Integer.MAX_VALUE;
        for (Integer i=start; i<=end; ++i)
        {
            if (i == end && isPalindrome(start, end))
            {
                minCost = 0;
                break;
            }

            if(isPalindrome(start, i))
            {
                minCost = Math.min(minCost, 1 + computeFor(i+1, end));
            }
        }

        return minCost;
    }

    private boolean isPalindrome(Integer start, Integer end)
    {
        if (dp[start][end] != null)
        {
            return dp[start][end];
        }

        if (start == end)
        {
            dp[start][end] = true;
        }
        else
        {
            if ((end-start + 1)%2 == 0)
            {
                dp[start][end] = testEvenLengthPalin(start, end);
            }
            else
            {
                dp[start][end] = testOddLengthPalin(start, end);
            }
        }

        return dp[start][end];
    }

    private Boolean testOddLengthPalin(Integer start, Integer end)
    {
        Integer lowEnd = ((start + end)/2) - 1;
        Integer hiStart = lowEnd + 2;

        while (lowEnd >= start)
        {
            if (characters[lowEnd] != characters[hiStart])
            {
                return false;
            }

            --lowEnd;
            ++hiStart;
        }

        return true;
    }

    private Boolean testEvenLengthPalin(Integer start, Integer end)
    {
        Integer lowEnd = (start + end)/2;
        Integer hiStart = lowEnd + 1;

        while (lowEnd >= start)
        {
            if (characters[lowEnd] != characters[hiStart])
            {
                return false;
            }

            --lowEnd;
            ++hiStart;
        }

        return true;
    }
}