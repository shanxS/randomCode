// permute all string fomed from given string
// code question : 55

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s = "ABC";
        StringPermuter sp = new StringPermuter();
        sp.computeStrings(s).stream().forEach(x -> System.out.println(x));
    }
}

class StringPermuter
{
    public List<String> computeStrings(String s)
    {
        List<Character> bag = new ArrayList<>();
        for(Character c : s.toCharArray())
        {
            bag.add(c);
        }

        return permute(bag);
    }

    private List<String> permute(List<Character> bag)
    {
        if (bag.size() == 1)
        {
            List<String> theseStrings = new ArrayList<>();
            theseStrings.add("" + bag.get(0));
            return theseStrings;
        }

        List<String> theseStrings = new ArrayList<>();

        for (Integer cursor = 0; cursor<bag.size(); ++ cursor)
        {
            Character c = bag.get(cursor);

            List<Character> newBag = new ArrayList<>(bag);
            newBag.remove(c);

            for (String s : permute(newBag))
            {
                theseStrings.add(c + s);
            }
        }

        return theseStrings;
    }
}