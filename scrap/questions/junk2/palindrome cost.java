// palindrome cost

public class Main
{
    public static void main(String[] er)
    {
        String s = "abcde";
        System.out.print((new PalindromeCost()).find(s));
    }
}

class PalindromeCost
{
    private char[] chars;
    public Integer find(String s)
    {
        chars = s.toCharArray();
        Integer L, R;
        if (chars.length%2 == 0)
        {
            R = chars.length/2;
            L = R-1;
        }
        else
        {
            R = (chars.length/2) + 1;
            L = R-2;
        }

        return contemplate(0, chars.length-1);
    }

    private Integer contemplate(Integer L, Integer R)
    {
        if (L == R)
        {
            return 0;
        }
        if (L > R)
        {
            return Integer.MAX_VALUE;
        }
        if (L == R-1)
        {
            return chars[L] == chars[R] ? 0 : 1;
        }

        if (chars[L] == chars[R])
        {
            return contemplate(L+1, R-1);
        }
        else
        {
            return 1 + Math.min(contemplate(L+1, R), contemplate(L, R-1));
        }
    }
}