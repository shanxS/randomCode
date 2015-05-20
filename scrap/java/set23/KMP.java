// KMP
// r5, q5, set 23

public class Main
{
    public static void main(String[] er)
    {
        String text = "ABC ABCDAB ABCDABCDABD";//"bacbababaabcbab";
        String pattern = "ABCDABD";//"abababca";

        KMP kmp = new KMP(text, pattern);
        kmp.find();


    }
}

class KMP
{
    private String text;
    private String pattern;
    private Integer[] partialMatchTable;

    public KMP(String text, String pattern)
    {
        this.text = text;
        this.pattern = pattern;
        this.partialMatchTable = new Integer[pattern.length()];

        computePartialMatchTable();
    }

    private void computePartialMatchTable()
    {
        partialMatchTable[0] = 0;

        for (Integer endIndex = 1; endIndex<pattern.length(); ++endIndex)
        {
            partialMatchTable[endIndex] = 0;
            Integer cursor = 0;
            while(cursor < endIndex)
            {
                Integer revCursor = endIndex - cursor;
                Integer fwdCursor = 0;
                Integer length = 0;
                while (fwdCursor <= cursor
                        && pattern.charAt(fwdCursor) == pattern.charAt(revCursor))
                {
                    ++length;
                    ++fwdCursor;
                    ++revCursor;
                }

                if (length > partialMatchTable[endIndex] && fwdCursor == cursor+1)
                {
                    partialMatchTable[endIndex] = length;
                }

                ++cursor;
            }

        }
    }

    public Integer find()
    {
        Integer textCursor=0, patternCursor = 0;
        while(textCursor-patternCursor<=text.length()-pattern.length())
        {
            while(patternCursor < pattern.length()
                && text.charAt(textCursor) == pattern.charAt(patternCursor))
            {
                ++textCursor;
                ++patternCursor;
            }

            if (patternCursor == pattern.length())
            {
                return textCursor;
            }
            else if (patternCursor >= 1)
            {
                if (partialMatchTable[patternCursor -1] == 0)
                {
                    patternCursor = 0;
                }
                else
                {
                    patternCursor = partialMatchTable[patternCursor - 1];
                }
            }
            else if (patternCursor == 0)
            {
                patternCursor = 0;
                ++textCursor;
            }
            else
            {
                patternCursor = 0;
            }
        }

        return -1;
    }
}