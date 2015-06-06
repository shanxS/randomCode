// KMP

public class Main
{
    public static void main(String[] er)
    {
        KMP kmp = new KMP();

        //System.out.print(kmp.printOccurances("ABCDABD", "ABC ABCDAB ABCDABCDABDE"));
        System.out.print(kmp.printOccurances("shasha", "sumedhashashank"));
    }
}

class KMP
{
    private Integer[] partialMatch;
    private String pattern, text;

    public Integer printOccurances(String pattern, String text)
    {
        this.partialMatch = new Integer[pattern.length()];
        this.pattern = pattern;
        this.text = text;

        setupPartialMatch();

        return findOccurance();
    }

    private Integer findOccurance()
    {
        Integer textCursor = 0;
        Integer patternCursor = 0;

        while (textCursor < text.length()
                && patternCursor < pattern.length())
        {
            if (text.charAt(textCursor) == pattern.charAt(patternCursor))
            {
                ++textCursor;
                ++patternCursor;
            }
            else
            {
                if (patternCursor == 0)
                {
                    ++textCursor;
                }
                else if (partialMatch[patternCursor-1] == 0)
                {
                    patternCursor = 0;
                }
                else
                {
                    patternCursor = partialMatch[patternCursor-1];
                }
            }
        }

        if (patternCursor == pattern.length())
        {
            return textCursor-patternCursor;
        }
        else
        {
            return null;
        }
    }

    private void setupPartialMatch()
    {
        for (Integer i = 0; i < partialMatch.length; ++i)
        {
            partialMatch[i] = 0;
        }

        for (Integer end = 1; end < partialMatch.length; ++end)
        {
            Integer startOfEnd = end;
            while(startOfEnd > 0)
            {
                Integer forwardCursor = 0;
                Integer endCursor = startOfEnd;
                Integer thisLength = 0;

                while(endCursor <= end
                        && pattern.charAt(endCursor) == pattern.charAt(forwardCursor))
                {
                    ++endCursor;
                    ++forwardCursor;
                    ++thisLength;
                }

                if (endCursor > end && partialMatch[end] < thisLength)
                {
                    partialMatch[end] = thisLength;
                }

                --startOfEnd;
            }
        }
    }
}
