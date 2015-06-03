// longest palindrome sub string using dp
// set 187, r2, q2

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s = "forgeeksskeegfor";
        PalindromFinder pf = new PalindromFinder();
        System.out.print(pf.findMax(s));
    }
}

class PalindromFinder
{
    List<Character> chars;
    public Integer findMax(String s)
    {
        chars = new ArrayList<>();
        for (Character c : s.toCharArray())
        {
            chars.add(c);
        }
        return find(0, chars.size()-1);
    }

    private Integer find(Integer start, Integer end)
    {
        if (end < start)
        {
            return 0;
        }

        if (end == start)
        {
            return 1;
        }

        Integer lenght = 0;
        while(end > start
                && chars.get(end) == chars.get(start))
        {
            --end;
            ++start;
            ++lenght;
        }

        if (end - start == 0 )
        {
            return lenght*2 - 1;
        }
        else if (end - start == -1)
        {
            return lenght*2;
        }
        else
        {
            return Math.max(find(start+1, end), find(start, end-1));
        }
    }
}