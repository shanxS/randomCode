// all possible strings that can be made by placing spaces
// code question: 54
// http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s = "ABC";
        StringMaker sm = new StringMaker();

        sm.compute(s).stream().forEach(x -> System.out.println(x));
    }
}

class StringMaker
{
    public List<String> compute(String s)
    {
        char[] chars = s.toCharArray();

        return permuteSpace(chars);
    }

    private List<String> permuteSpace(char[] chars)
    {
        if (chars.length == 1)
        {
            List<String> theseStrings = new ArrayList<>();
            theseStrings.add("" + chars[0]);
            return theseStrings;
        }

        List<String> postFix = permuteSpace(Arrays.copyOfRange(chars, 1, chars.length));

        List<String> theseStrings = new ArrayList<>();
        String prefix1 = "" + chars[0];
        String prefix2 = prefix1 + " ";

        for (String s : postFix)
        {
            theseStrings.add(prefix1 + s);
            theseStrings.add(prefix2 + s);
        }

        return theseStrings;
    }

}