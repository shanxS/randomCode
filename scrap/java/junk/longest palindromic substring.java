// longest palindromic substring

public class Main
{
    public static void main(String[] er)
    {
        String s = "GEEKSFORGEEKS";
        FindPalindrome fp = new FindPalindrome();
        System.out.print(fp.find(s));
    }
}


class FindPalindrome
{
    private Character[] str;

    public Integer find(String s)
    {
        str = new Character[s.length()];
        for (Integer i=0; i<s.length(); ++i)
        {
            str[i] = s.charAt(i);
        }

        return findRecursivly(0, s.length()-1);
    }

    private Integer findRecursivly(Integer start, Integer end)
    {
        if (start == end)
        {
            return 1;
        }

        Integer subStrignSize = end - start + 1;
        while(start <= end && str[start] == str[end])
        {
            --end;
            ++start;
        }

        if (start > end)
        {
            return subStrignSize;
        }
        else
        {
            return Math.max(findRecursivly(start + 1, end), findRecursivly(start, end - 1));
        }
    }
}