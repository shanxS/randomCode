// LCS such that we count seq of 3 char as 1 substring as opposed to 1 char
// code question 52

public class Main
{
    public static void main(String[] er)
    {
        String s1 = "abcshadefsumghinasa";
        String s2 = "jkshalmsumnasanop";

        LCS lcs = new LCS();
        System.out.print(lcs.find(s1,s2));
    }
}

class LCS
{
    private Character[] str1, str2;
    Integer[][] dp;
    private final Integer INVALID = -1;

    public Integer find(String text1, String text2)
    {
        str1 = new Character[text1.length()];
        for (Integer i=0; i<text1.length(); ++i)
        {
            str1[i] = text1.charAt(i);
        }

        str2 = new Character[text2.length()];
        for (Integer i=0; i<text2.length(); ++i)
        {
            str2[i] = text2.charAt(i);
        }

        resetDp();

        Integer len = findLCS(str1.length-1, str2.length-1);

        return len;
    }

    private void resetDp()
    {
        dp = new Integer[str1.length][str2.length];
        for (Integer r=0; r<dp.length; ++r)
        {
            for (Integer c=0; c<dp[0].length; ++c)
            {
                dp[r][c] = INVALID;
            }
        }
    }

    private Integer  findLCS(Integer end1, Integer end2)
    {
        if (end1 < 0 || end2 < 0)
        {
            return 0;
        }

        Integer cachedEnd1 = end1;
        Integer cachedEnd2 = end2;

        if (dp[cachedEnd1][cachedEnd2] != INVALID)
        {
            return dp[cachedEnd1][cachedEnd2];
        }

        Integer count = 0;
        while(end1 >= 0 && end2 >= 0 && str1[end1] == str2[end2] && count < 3)
        {
            --end1;
            --end2;
            ++count;
        }

        if (count == 3)
        {
            dp[cachedEnd1][cachedEnd2] =  1 + findLCS(end1-1, end2-1);
        }
        else
        {
            dp[cachedEnd1][cachedEnd2] = Math.max(
                findLCS(cachedEnd1 - 1, cachedEnd2),
                findLCS(cachedEnd1, cachedEnd2 - 1)
            );
        }

        return dp[cachedEnd1][cachedEnd2];
    }
}