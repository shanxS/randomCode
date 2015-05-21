// kmp practice

public class Main
{
    public static void main(String[] er)
    {
        KMP kmp = new KMP();
        kmp.find("ABC ABCDAB ABCDABCDABDE", "ABCDABD");
    }
}

class KMP
{
    private String pattern;
    private Integer[] patternMatch;

    public KMP()
    {
        this.pattern = null;
        this.patternMatch = null;
    }

    public Integer find(String text, String pattern)
    {
        computePatternMatch(pattern);

        Integer textCursor = 0;
        Integer patternCursor = 0;
        while(textCursor-patternCursor <= text.length()-pattern.length())
        {
            while(patternCursor < pattern.length()
                    && pattern.charAt(patternCursor) == text.charAt(textCursor))
            {
                ++patternCursor;
                ++textCursor;
            }

            if (patternCursor == pattern.length())
            {
                return textCursor-patternCursor;
            }
            if (patternCursor == 0)
            {
                ++textCursor;
                patternCursor = 0;
            }
            else
            {
                if (patternMatch[patternCursor - 1] != 0)
                {
                    patternCursor = patternMatch[patternCursor-1];
                }
                else
                {
                    patternCursor = 0;
                }
            }

        }

        return -1;
    }

    private void computePatternMatch(String pattern)
    {
        patternMatch = new Integer[pattern.length()];

        patternMatch[0] = 0;
        for (Integer endIndex=1; endIndex<pattern.length(); ++endIndex)
        {
            patternMatch[endIndex] = 0;
            Integer cursor = 0;

            while(cursor < endIndex)
            {
                Integer startCursor = 0;
                Integer endCursor = endIndex - cursor;
                Integer length = 0;

                while(startCursor <= cursor
                        && pattern.charAt(startCursor) == pattern.charAt(endCursor))
                {
                    ++startCursor;
                    ++endCursor;
                    ++length;
                }

                if (length > patternMatch[endIndex] && startCursor == cursor+1)
                {
                    patternMatch[endIndex] = length;
                }

                ++cursor;
            }
        }
    }
}