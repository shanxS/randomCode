// find first duplicate character
// r2, q1, set16

import java.util.Set;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args)
    {
        //String str = "shashank";
        String str = "sumedha";
        Set<Character> set = new TreeSet<>();

        for (Character c : str.toCharArray())
        {
            if (set.contains(c))
            {
                System.out.print("repeated " + c);
                break;
            }
            set.add(c);
        }

        if (set.size() == str.length())
        {
            System.out.print("no duplicates");
        }
    }
}