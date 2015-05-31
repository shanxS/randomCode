// minimum insertion to turn string to palindrome
// code question 25

public class Main
{
    public static void main(String[] er)
    {
        //String str = "bcd";
        //String str = "abcd";
        //String str = "aa";
        String str = "azbzczdzez";
        MinInsertionForPalindrome mp = new MinInsertionForPalindrome();
        System.out.print(mp.calculate(str));
    }
}

class MinInsertionForPalindrome
{
    final private Integer INVALID = -1;
    private Integer[][] dp;
    private char[] chars;

    public Integer calculate(String str)
    {
        this.chars = str.toCharArray();
        dp = new Integer[str.length()][str.length()];

        resetDp();

        Integer forwardCursor = 0;
        Integer reverseCursor = str.length()-1;
        Integer count = 0;

        if (reverseCursor - forwardCursor == 1)
        {
            if (chars[reverseCursor] != chars[forwardCursor])
            {
                ++count;
            }
        }
        else
        {
            while (forwardCursor < reverseCursor)
            {
                if (chars[forwardCursor] == chars[reverseCursor])
                {
                    setDp(forwardCursor, reverseCursor, count);
                    ++forwardCursor;
                    --reverseCursor;
                } else
                {
                    if (test(forwardCursor + 1, reverseCursor) < test(forwardCursor, reverseCursor - 1))
                    {
                        setDp(forwardCursor, reverseCursor, count + 1);
                        ++forwardCursor;
                    } else
                    {
                        setDp(forwardCursor, reverseCursor, count + 1);
                        --reverseCursor;
                    }

                    ++count;
                }
            }
        }
        return count;
    }

    private Integer test(Integer forwardCursor, Integer reverseCursor)
    {
        if (dp[forwardCursor][reverseCursor] != INVALID)
        {
            return dp[forwardCursor][reverseCursor];
        }

        Integer count = 0;

        if (reverseCursor - forwardCursor == 1)
        {
            if (chars[reverseCursor] != chars[forwardCursor])
            {
                ++count;
            }
        }
        else
        {
            while (forwardCursor < reverseCursor)
            {
                if (chars[forwardCursor] == chars[reverseCursor])
                {
                    ++forwardCursor;
                    --reverseCursor;
                } else
                {
                    if (test(forwardCursor + 1, reverseCursor) < test(forwardCursor, reverseCursor - 1))
                    {
                        ++forwardCursor;
                    } else
                    {
                        --reverseCursor;
                    }

                    ++count;
                }
            }
        }

        setDp(forwardCursor, reverseCursor, count);
        return count;
    }

    private void setDp(Integer cursor1, Integer cursor2, Integer count)
    {
        dp[cursor1][cursor2] = count;
        dp[cursor2][cursor1] = count;
    }

    private void resetDp()
    {
        for (Integer r=0; r<dp.length; ++r)
        {
            for (Integer c=0; c<dp[0].length; ++c)
            {
                dp[r][c] = INVALID;
            }
        }
    }
}