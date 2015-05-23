// Given input string and list of words - test if input string can be segmented into space-separated sequence of lsit words
// related to r1, q1,2 set27

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String[] array = new String[] {"i", "like", "sam", "sung", "samsung", "mobile", "ice",
                "cream", "icecream", "man", "go", "mango"};
        List<String> dictionary = new ArrayList<>();
        for (String str : array)
        {
            dictionary.add(str);
        }

        BrokenWordFinder bwf = new BrokenWordFinder(dictionary, "ilikemango");
        bwf.findWord();
    }
}

class BrokenWordFinder
{
    private List<String> dictionary;
    private char[] input;
    private List<String> list;

    public BrokenWordFinder(List<String> dictionary, String input)
    {
        this.dictionary = dictionary;
        this.input = input.toCharArray();
        this.list = new ArrayList<>();
    }

    public boolean findWord()
    {
        Boolean result =  findWord(0);
        return result;
    }

    private boolean findWord(final Integer thisIndex)
    {
        if (thisIndex == input.length)
        {
            return true;
        }

        Integer cursor = thisIndex;

        String thisString = makeString(thisIndex, cursor);
        while(cursor < input.length && !dictionary.contains(thisString))
        {
            ++cursor;
            thisString = makeString(thisIndex, cursor);
        }

        list.add(thisString);
        while(cursor < input.length && !findWord(cursor+1))
        {
            list.remove(thisString);
            ++cursor;
            thisString = makeString(thisIndex, cursor);
            while(cursor < input.length && !dictionary.contains(thisString))
            {
                ++cursor;
                thisString = makeString(thisIndex, cursor);
            }
        }

        if (!dictionary.contains(thisString))
        {
            list.remove(thisString);
            return false;
        }
        else
        {
            return true;
        }
    }

    private String makeString(Integer startIndex, Integer endIndex)
    {
        if (endIndex == input.length)
        {
            return null;
        }

        String str = "";
        for (Integer i=startIndex; i<=endIndex; ++i)
        {
            str += input[i];
        }

        return str;
    }

}
