// discontinous LCS
// code question 52

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        LCS lcs = new LCS();
        System.out.print(lcs.find(s1,s2));
    }
}

class LCS
{
    private Character[] str1, str2;
    private List<Character> chars;
    final private Integer INVALID = -1;
    private Integer[][] dp;

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

        chars = new ArrayList<>();

        resetDp();

        Integer len = findLCS(str1.length-1, str2.length-1);

        chars.stream().forEach(x -> System.out.print(x));

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

        if (dp[end1][end2] != INVALID)
        {
            return dp[end1][end2];
        }

        if (str1[end1] == str2[end2])
        {
            chars.add(str1[end1]);
            dp[end1][end2] = 1 + findLCS(end1-1, end2-1);
        }
        else
        {
            dp[end1][end2] = Math.max(
                findLCS(end1 - 1, end2),
                findLCS(end1, end2 - 1)
            );
        }

        return dp[end1][end2];
    }
}