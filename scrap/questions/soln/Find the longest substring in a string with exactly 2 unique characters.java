// Find the longest substring in a string with exactly 2 unique characters.
// code question 14
// r2, q3, set32

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s = "aaaaabbaaaccccccbcccc";
        StringFinder sf = new StringFinder();
        System.out.print(sf.find(s));

    }
}

class StringFinder
{
    public String find(String str)
    {
        if (str.length() < 2)
        {
            return "";
        }

        Integer start = 0;
        Integer end = 1;
        List<Character> chars = new ArrayList<>();

        chars.add(str.charAt(start));
        while(str.charAt(start) == str.charAt(end))
        {
            ++end;
        }
        chars.add(str.charAt(end));


        Integer maxStart = start;
        Integer maxEnd = end;
        for (Integer cursor = end+1; cursor<str.length(); ++cursor)
        {
            Character c = str.charAt(cursor);

            if (chars.contains(c))
            {
                ++end;
            }
            else
            {
                if (end-start > maxEnd-maxStart)
                {
                    maxEnd = end;
                    maxStart = start;
                }

                if (chars.get(0) == str.charAt(end))
                {
                    chars.remove(1);
                }
                else
                {
                    chars.remove(0);
                }
                Character startChar = chars.get(0);
                start = end;
                while(startChar == str.charAt(start-1))
                {
                    --start;
                }

                ++end;
                chars.add(c);
            }
        }
        if (end-start > maxEnd-maxStart)
        {
            maxEnd = end;
            maxStart = start;
        }

        String result = "";
        for (Integer cursor = maxStart; cursor<=maxEnd; ++cursor)
        {
            result += str.charAt(cursor);
        }

        return result;
    }
}