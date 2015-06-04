// input string can be segmented into a space-separated sequence of dictionary words
// r3, q2, set185
// code question 45

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public  static void main(String[] er)
    {
        String[] dic = new String[] {"mobile","samsung","sam","man","mango",
                "icecream","and","go","i","like","ice","cream"};
        List<String> dicl = new ArrayList<>();
        for (String s : dic)
        {
            dicl.add(s);
        }
        StringFinder sf = new StringFinder();
        System.out.print(sf.find(dicl, "ilikeicecreamsamsamsung"));
    }
}

class StringFinder
{
    private final Integer INVALID = -1;
    private final Integer FOUND = 1;
    private final Integer NOTFOUND = 0;
    private Integer[][] dp;

    private char[] str;
    private List<String> dictionary;

    public Boolean find(List<String> dictionary, String s)
    {
        this.dp = new Integer[s.length()][s.length()];
        this.str = s.toCharArray();
        this.dictionary = dictionary;

        reset(dp);

        return find(0);
    }

    private void reset(Integer[][] dp)
    {
        for (Integer r=0; r<dp.length; ++r)
        {
            for (Integer c=0; c<dp[0].length; ++c)
            {
                dp[r][c] = INVALID;
            }
        }
    }

    private Boolean find(Integer startIndex)
    {
        if (startIndex >= str.length)
        {
            return true;
        }

        Integer cursor = startIndex;
        String thisString = "";
        while (!dictionary.contains(thisString) && cursor < str.length)
        {
            thisString += str[cursor];
            ++cursor;
        }

        while(!find(cursor) && cursor < str.length)
        {
            while(cursor < str.length)
            {
                thisString += str[cursor];
                ++cursor;

                if (dictionary.contains(thisString))
                {
                    break;
                }
            }
        }

        if (!dictionary.contains(thisString))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}