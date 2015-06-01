// convert given string to palindrome with least insertions
// r3, q1, set37

public class Main
{
    private static Integer[][] dp;
    static char[] chars = "abcda".toCharArray();
    private static Integer INVALID = -1;

    public static void main(String[] er)
    {

        dp = new Integer[chars.length][chars.length];

        resetDp();

        Integer forwardCursor = 0;
        Integer reverseCursor = chars.length-1;
        Integer count = 0;

        while(forwardCursor < reverseCursor)
        {
            if (chars[forwardCursor] == chars[reverseCursor])
            {
                ++forwardCursor;
                --reverseCursor;
            }
            else if (reverseCursor-forwardCursor == 1)
            {
                ++count;
                break;
            }
            else
            {
                if (evaluate (forwardCursor+1, reverseCursor) < evaluate(forwardCursor, reverseCursor-1))
                {
                    count += evaluate (forwardCursor+1, reverseCursor);
                    setDP(reverseCursor, forwardCursor, count);
                    ++forwardCursor;
                }
                else
                {
                    count += evaluate(forwardCursor, reverseCursor-1);
                    setDP(reverseCursor, forwardCursor, count);
                    --reverseCursor;
                }
            }
        }

        System.out.print(count);
    }

    private static Integer evaluate(Integer forwardCursor, Integer reverseCursor)
    {
        if (dp[forwardCursor][reverseCursor] != INVALID)
        {
            return dp[forwardCursor][reverseCursor];
        }

        Integer count = 0;
        while(forwardCursor < reverseCursor)
        {
            if (chars[forwardCursor] == chars[reverseCursor])
            {
                ++forwardCursor;
                --reverseCursor;
            }
            if (reverseCursor-forwardCursor == 1)
            {
                ++count;
                break;
            }
            else
            {
                if (evaluate (forwardCursor+1, reverseCursor) < evaluate(forwardCursor, reverseCursor-1))
                {
                    count += evaluate (forwardCursor+1, reverseCursor);
                    setDP(reverseCursor, forwardCursor, count);
                    ++forwardCursor;
                }
                else
                {
                    count += evaluate(forwardCursor, reverseCursor-1);
                    setDP(reverseCursor, forwardCursor, count);
                    --reverseCursor;
                }
            }
        }

        return count;
    }

    private static void setDP(Integer reverseCursor, Integer forwardCursor, Integer count)
    {
        dp[reverseCursor][forwardCursor] = count;
        dp[forwardCursor][reverseCursor] = count;
    }

    private static void resetDp()
    {
        for (int r=0; r<dp.length; ++r)
        {
            for (int c=0; c<dp[0].length; ++c)
            {
                dp[r][c] = INVALID;
            }
        }
    }
}