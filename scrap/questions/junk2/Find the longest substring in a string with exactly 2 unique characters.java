// Find the longest substring in a string with exactly 2 unique characters

import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] er)
    {
//        String s = "aaababaddd";
        String s = "ababadddaa";

        (new LongestUniqueSub()).find(s);
    }
}

class LongestUniqueSub
{
    private char[] chars;
    private Integer maxLen;
    public void find(String s)
    {
        chars = s.toCharArray();
        maxLen = Integer.MIN_VALUE;

        Set<Character> uniqueChars = new HashSet<>();
        Integer len = 0;
        for (Integer i=0; i<chars.length; ++i)
        {
            if (uniqueChars.size() < 2)
            {
                uniqueChars.add(chars[i]);
                ++len;
            }
            else if (uniqueChars.contains(chars[i]))
            {
                ++len;
                maxLen = Math.max(maxLen, len);
            }
            else
            {
                maxLen = Math.max(maxLen, len);

                len = 2;
                Integer prevStartIndex = i-1;
                while (prevStartIndex > 0 && chars[prevStartIndex] == chars[prevStartIndex-1])
                {
                    --prevStartIndex;
                    ++len;
                }

                uniqueChars.clear();
                uniqueChars.add(chars[i]);
                uniqueChars.add(chars[i-1]);
            }
        }

        System.out.print(maxLen);
    }
}