// edit distance of string
// code question: 66

public class Main
{
    public static void main(String[] er)
    {
        String T = "ago";
        String P = "agog";
//        String T = "our";
//        String P = "hour";
//        String T = "shot";
//        String P = "spot";
//        String T = "saturday";
//        String P = "sunday";

        StringDistance sd = new StringDistance();
        System.out.print(sd.findDistance(T, P));
    }
}

class StringDistance
{
    private String T, P;
    private Integer[][] dp;
    final private Integer INVALID = -1;

    public Integer findDistance(String text, String pattern)
    {
        this.T = text;
        this.P = pattern;

        resetDp();

        return calculate(T.length()-1, P.length()-1);
    }

    private void resetDp()
    {
        Integer maxSize = Math.max(T.length(), P.length());
        dp = new Integer[maxSize][maxSize];
        for (Integer i=0; i<maxSize; ++i)
        {
            for (Integer j=0; j<maxSize; ++j)
            {
                dp[i][j] =  INVALID;
            }
        }
    }

    private Integer calculate(Integer textCursor, Integer patternCursor)
    {
        if (textCursor < 0 && patternCursor < 0)
        {
            return 0;
        }

        if (textCursor < 0 && patternCursor >= 0)
        {
            // delete
            return 1 + calculate(textCursor, patternCursor-1);
        }
        else if (textCursor >= 0 && patternCursor < 0)
        {
            // insert
            return 1 + calculate(textCursor-1, patternCursor);
        }
        else
        {
            if (dp[textCursor][patternCursor] != INVALID)
            {
                return dp[textCursor][patternCursor];
            }
            else if (T.charAt(textCursor) == P.charAt(patternCursor))
            {
                dp[textCursor][patternCursor] = calculate(textCursor-1, patternCursor-1);
            }
            else
            {
                Integer deleteCost = calculate(textCursor, patternCursor-1) + 1;
                Integer insertCost = calculate(textCursor-1, patternCursor) + 1;
                Integer replaceCost = calculate(textCursor-1, patternCursor-1) + 1;

                dp[textCursor][patternCursor] = Math.min(deleteCost, Math.min(insertCost, replaceCost));
            }
        }

        return dp[textCursor][patternCursor];
    }
}