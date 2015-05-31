// code question 14

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String str = "aaaaabbaaaccccccccccbcccccccccccccc";
        LongestFinder lf = new LongestFinder();
        lf.printLongest(str);
    }
}

class LongestFinder
{
    public void printLongest(String str)
    {
        Integer start = 0;
        Integer end = 0;
        while(str.charAt(end) == str.charAt(start))
        {
            ++end;
        }

        List<Character> chars = new ArrayList<>();
        chars.add(str.charAt(start));
        chars.add(str.charAt(end));

        Integer maxStart = start;
        Integer maxEnd = end;

        for (Integer cursor = end+1; cursor<str.length(); ++cursor)
        {
            Character c = str.charAt(cursor);
            if (!chars.contains(c))
            {
                if (end - start > maxEnd - maxStart)
                {
                    maxEnd = end;
                    maxStart = start;
                }

                Character deletedChar = null;
                if (chars.get(0) == str.charAt(end))
                {
                    deletedChar = chars.remove(1);
                }
                else
                {
                    deletedChar = chars.remove(0);
                }
                chars.add(c);

                start = end;
                while (str.charAt(start-1) != deletedChar)
                {
                    --start;
                }
            }
            ++end;
        }
        if (end - start > maxEnd - maxStart)
        {
            maxEnd = end;
            maxStart = start;
        }

        for (Integer cursor=maxStart; cursor<=maxEnd; ++cursor)
        {
            System.out.print(str.charAt(cursor));
        }

    }
}