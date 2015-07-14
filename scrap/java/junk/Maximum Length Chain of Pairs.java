// Maximum Length Chain of Pairs

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Node[] nodes = {
                new Node(5, 24),
                new Node(39, 60),
                new Node(15, 28),
                new Node(27, 40),
                new Node(50, 90)
        };

        PairFinder pf = new PairFinder();
        System.out.print(pf.findLen(nodes));
    }
}

class PairFinder
{
    private Node[] nodes;
    public Integer findLen (Node[] nodes)
    {
        Arrays.sort(nodes);
        this.nodes = nodes;

        return findMax(0);
    }

    private Integer findMax(Integer thisIndex)
    {
        if (thisIndex == nodes.length-1)
        {
            return 1;
        }

        Integer maxLen = Integer.MIN_VALUE;
        for (Integer cursor = thisIndex+1; cursor<nodes.length; ++cursor)
        {
            if (nodes[thisIndex].s < nodes[cursor].f)
            {
                maxLen = Math.max(maxLen, findMax(cursor));
            }
        }

        return maxLen+1;
    }
}

class Node implements Comparable
{
    final Integer f, s;

    public Node(Integer f, Integer s)
    {
        this.f = f;
        this.s = s;
    }

    @Override
    public int compareTo(Object o)
    {
        Node otherNode = (Node) o;

        if (otherNode.f > f)
        {
            return -1;
        }
        else if (otherNode.f < f)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}