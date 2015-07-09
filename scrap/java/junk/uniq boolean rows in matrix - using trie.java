// uniq boolean rows in matrix - using trie

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        String[] s = {
                "01001",
                "10110",
                "01001",
                "11100"
        };

        Trie trie = new Trie(s[0]);
        System.out.println(0);

        for (Integer i=1; i<s.length; ++i)
        {
            if (!trie.find(s[i]))
            {
                System.out.println(i);
                trie.add(s[i]);
            }
        }
    }
}

class Trie
{
    private Character c;
    private Map<Character, Trie> map;

    public Trie(String s)
    {
        this.c = s.charAt(0);
        map = new HashMap<>();
        add(s);
    }

    public void add(String s)
    {
        if (s.length() > 1)
        {
            Trie nextNode = map.get(s.charAt(1));
            if (nextNode == null)
            {
                nextNode = new Trie(s.substring(1, s.length()));
                map.put(s.charAt(1), nextNode);
            }
            else
            {
                nextNode.add(s.substring(1, s.length()));
            }
        }
    }

    public boolean find(String s)
    {
        if (c != s.charAt(0))
        {
            return false;
        }

        if (s.length() > 1)
        {
            Trie nextNode = map.get(s.charAt(1));
            if (nextNode == null)
            {
                return false;
            }
            else
            {
                return nextNode.find(s.substring(1, s.length()));
            }
        }

        return true;
    }
}