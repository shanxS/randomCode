// suffix tree

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        String s = "banana";
        Node node = new Node(s);
        for (Integer i=1; i<s.length(); ++i)
        {
            node.add(s.substring(i, s.length()));
        }

        node.print();
    }
}

class Node
{
    private Map<Character, Node> charNodeMap;
    private Boolean isEnding;

    public Node (String data)
    {
        charNodeMap = new HashMap<>();

        isEnding = data.isEmpty();

        if (!data.isEmpty())
        {
            Character c = data.charAt(0);

            Node childNode = charNodeMap.get(c);
            if (childNode == null)
            {
                childNode = new Node(data.substring(1, data.length()));
                charNodeMap.put(c, childNode);
            }
            else
            {
                childNode.add(data.substring(1, data.length()));
            }
        }
    }

    public void add(String substring)
    {
        isEnding = substring.isEmpty();

        if (!substring.isEmpty())
        {
            Character c = substring.charAt(0);

            Node childNode = charNodeMap.get(c);
            if (childNode == null)
            {
                childNode = new Node(substring.substring(1, substring.length()));
                charNodeMap.put(c, childNode);
            }
            else
            {
                childNode.add(substring.substring(1, substring.length()));
            }
        }
    }

    public void print()
    {


        for (Character key: charNodeMap.keySet())
        {
            System.out.print(key + " ");
        }

        System.out.println("Ending " + isEnding + " ");

        for (Map.Entry<Character, Node> entry : charNodeMap.entrySet())
        {
            System.out.println();
            System.out.println("for parent: " + entry.getKey());
            entry.getValue().print();
        }
    }

}