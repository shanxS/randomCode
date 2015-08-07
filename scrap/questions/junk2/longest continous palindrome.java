// longest continous palindrome

public class Main
{
    public static void main(String[] er)
    {
        String s = "shasummus";
        System.out.print((new Palin()).palindromicSub(s.toCharArray()));
    }
}

class Palin
{
    private char[] chars;
    public Integer palindromicSub(char[] s)
    {
        Integer max = Integer.MIN_VALUE;
        for (Integer i=1; i<s.length; ++i)
        {
            Integer even = 0;
            Integer j = i-1, k=i;
            while(j>=0 && k<s.length && s[j]==s[k])
            {
                even += 2;
                --j;
                ++k;
            }

            Integer odd = 1;
            j=i-1; k=i+1;
            while(j>=0 && k<s.length && s[j]==s[k])
            {
                odd += 2;
                --j;
                ++k;
            }

            max = Math.max(max, Math.max(even, odd));
        }

        return max;
    }
}