// test to check if character is repeated in string
// r1, q1, set28

import java.util.TreeSet;

public class Main
{
    public static void main(String[] er)
    {
        String str = "shashank nanu";
        TreeSet<Character> set = new TreeSet<>();
        for (Character c : str.toCharArray())
        {
            if (!set.add(c))
            {
                System.out.println(c);
            }
        }
    }
}