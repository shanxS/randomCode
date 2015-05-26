// permutations of string
// r2, q4, set29

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String str = "sum";
        Permuter p = new Permuter();
        p.permute(str, 3);
    }
}

class Permuter
{
    public void permute(String s, Integer size)
    {
        List<Character> bag = new ArrayList<>();
        for(Character c : s.toCharArray())
        {
            bag.add(c);
        }

        List<String> list = permute(bag, size);
        return;
    }

    private List<String> permute(List<Character> bag, Integer size)
    {
        if (size == 1)
        {
            List<String> l = new ArrayList<String>();
            for (Character c : bag)
            {
                l.add("" + c);
            }

            return l;
        }

        List<String> postStrings = permute(bag, size-1);

        List<String> strings = new ArrayList<>();
        for (Character c : bag)
        {
            for (String s : postStrings)
            {
                strings.add(c + s);
            }
        }

        return strings;
    }
}