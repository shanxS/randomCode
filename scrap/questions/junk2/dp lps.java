// dp lps

public class Main
{
    public static void main(String[] er)
    {
        String s = "BBABCBCAB";
        System.out.print((new Palin()).maxLen(s.toCharArray()));
    }
}

class Palin
{
    private char[] chars;
    private int[][] dp;
    public Integer maxLen(char[] s)
    {
        chars = s;
        dp = new int[s.length][s.length];

        Integer max = Integer.MIN_VALUE;
        for (Integer i=0; i<chars.length; ++i)
        {
            Integer even = 0;
            Integer j=i-1, k=i;
            while(j >= 0 && k < chars.length && chars[j] == chars[k])
            {
                even += 2;
                --j;
                ++k;
            }
            if (j >= 0 && k < chars.length && chars[j] != chars[k])
            {
                even += Math.max(contemplate(j-1, k), contemplate(j, k+1));
            }

            Integer odd = 1;
            j = i-1; k = i+1;
            while(j >= 0 && k < chars.length && chars[j] == chars[k])
            {
                odd += 2;
                --j;
                ++k;
            }
            if (j >= 0 && k < chars.length && chars[j] != chars[k])
            {
                odd += Math.max(contemplate(j-1, k), contemplate(j, k+1));
            }

            max = Math.max(max, Math.max(odd, even));
        }

        return max;
    }

    private Integer contemplate(Integer j, Integer k)
    {
        if (j < 0 || k >= chars.length)
        {
            return 0;
        }

        Integer count = 0;
        while(j >= 0 && k < chars.length && chars[j] == chars[k])
        {
            count += 2;
            --j;
            ++k;
        }
        if (j >= 0 && k < chars.length && chars[j] != chars[k])
        {
            count += Math.max(contemplate(j-1, k), contemplate(j, k+1));
        }

        return count;
    }
}