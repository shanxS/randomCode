// edit distance

public class Main
{
    public static void main(String[] er)
    {
        String pat = "sunday";
        String text = "saturday";

        EditDistance ed = new EditDistance();
        System.out.print(ed.find(pat.toCharArray(), text.toCharArray()));
    }
}

class EditDistance
{
    private char[] pattern, text;
    public Integer find (char[] pattern, char[] text)
    {
        this.pattern = pattern;
        this.text = text;

        return contemplate(0,0);
    }

    private Integer contemplate(int pattenCursor, int textCursor)
    {
        if (pattenCursor < pattern.length && textCursor == text.length)
        {
            return pattern.length - pattenCursor;
        }
        else if (pattenCursor == pattern.length && textCursor < text.length)
        {
            return text.length - textCursor;
        }
        else if (pattenCursor == pattern.length && textCursor == text.length)
        {
            return 0;
        }

        if (pattern[pattenCursor] == text[textCursor])
        {
            return contemplate(pattenCursor+1, textCursor+1);
        }
        else
        {
            Integer subCost = 1 + contemplate(pattenCursor+1, textCursor+1);
            Integer delCost = 1 + contemplate(pattenCursor, textCursor+1);
            Integer insCost = 1 + contemplate(pattenCursor+1, textCursor);
            return Math.min(subCost
                    ,Math.min(delCost, insCost));
        }
    }
}