// list all the possible words from the given set of data in the same order
// r4, q1, set17

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        String string = "sume";
        Shuffler shuffler = new Shuffler(string);
        for (String str : shuffler.getStrings())
        {
            System.out.println(str);
        }
    }
}

class Shuffler
{
    private char[] chars;
    private List<String> strings;

    public Shuffler(String string)
    {
        this.chars = string.toCharArray();
        this.strings = new ArrayList<>();
    }

    public List<String> getStrings()
    {
        for (Integer i=1; i<chars.length; ++i)
        {
            String stringStart = "";
            for (Integer j=0; j<i; ++j)
            {
                stringStart += chars[j];
            }

            String stringEnd = "";
            for (Integer j=i; j<chars.length; ++j)
            {
                stringEnd += chars[j];
            }

            strings.add(stringStart);
            strings.add(stringEnd);
        }

        return strings;
    }

}