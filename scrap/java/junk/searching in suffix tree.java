// searching in suffix tree

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        //node.print();

        SuffixSearcher ss = new SuffixSearcher();
        System.out.print(ss.find(node, "ana"));

    }

}

class SuffixSearcher
{
    public Boolean find(Node suffixHead, String s)
    {
        if (s.length() == 0)
        {
            return null; // invalid case
        }

        Character thisChar = s.charAt(0);
        if (suffixHead.getCharNodesMap().keySet().contains(thisChar))
        {
            List<Node> theseNodes = suffixHead.getCharNodesMap().get(thisChar);
            if (s.length() == 1)
            {
                return theseNodes.contains(null);
            }
            else
            {
                Character targetChar = s.charAt(1);
                for (Node node : theseNodes)
                {
                    if (node != null)
                    {
                        if (node.getCharNodesMap().keySet().contains(targetChar))
                        {
                            return find(node, s.substring(1, s.length()));
                        }
                    }
                }
            }
        }

        return false;
    }
}

class Node
{
    private Map<Character, List<Node>> charNodesMap;

    public Map<Character, List<Node>> getCharNodesMap()
    {
        return charNodesMap;
    }

    public Node(String data)
    {
        charNodesMap = new HashMap<>();

        Character thisChar = data.charAt(0);
        List<Node> theseNodes = new ArrayList<>();
        if (data.length() == 1)
        {
            theseNodes.add(null);
        }
        else
        {
            theseNodes.add(new Node(data.substring(1, data.length())));
        }
        charNodesMap.put(thisChar, theseNodes);
    }

    public void add(String substring)
    {
        Character thisChar = substring.charAt(0);
        List<Node> theseNode = charNodesMap.get(thisChar);
        if (theseNode == null)
        {
            theseNode = new ArrayList<>();
            if (substring.length() == 1)
            {
                theseNode.add(null);
            }
            else
            {
                theseNode.add(new Node(substring.substring(1, substring.length())));
            }

            charNodesMap.put(thisChar, theseNode);
        }
        else
        {
            if (substring.length() == 1)
            {
                if (!theseNode.contains(null))
                {
                    theseNode.add(null);
                }
            }
            else
            {
                Character targetChar = substring.charAt(1);
                Boolean found = false;

                for (Node node : theseNode)
                {
                    if (node.charNodesMap.keySet().contains(targetChar))
                    {
                        node.add(substring.substring(1, substring.length()));
                        found = true;
                        break;
                    }
                }

                if (!found)
                {
                    theseNode.add(new Node(substring.substring(1, substring.length())));
                }
            }
        }
    }

    public void print()
    {
        print(charNodesMap, "");
    }

    private void print(Map<Character, List<Node>> charNodeMap, String space)
    {
        for (Map.Entry<Character, List<Node>> entry : charNodeMap.entrySet())
        {
            System.out.print(space + entry.getKey() + " ");
            if (entry.getValue().contains(null))
            {
                System.out.print("' ");
            }

            for (Node node : entry.getValue())
            {
                if (node != null)
                {
                    node.print(node.charNodesMap, " "  + space);
                    System.out.println();
                }
            }

            System.out.println();
        }
    }
}