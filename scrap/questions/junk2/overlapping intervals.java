// overlapping intervals

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        List<Node> nodes = makeNodes();
        Collections.sort(nodes);

        Integer overlap = 0;
        for (Integer i=1; i<nodes.size(); ++i)
        {
            if (nodes.get(i-1).end > nodes.get(i).start)
            {
                ++overlap;
            }
        }

        System.out.print(overlap);
    }

    private static List<Node> makeNodes()
    {
        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node(1,3));
        nodes.add(new Node(7,9));
        nodes.add(new Node(4,6));
        nodes.add(new Node(10,13));

        return nodes;
    }
}

class Node implements Comparable
{
    final public Integer start, end;

    public Node(Integer start, Integer end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Node))
        {
            return false;
        }

        Node otherNode = (Node) o;
        return start==otherNode.start && end==otherNode.end;
    }

    @Override
    public int hashCode()
    {
        Integer prime = 17;
        Integer result = 13;
        result = prime*result + start;
        result = prime*result + end;

        return result;
    }

    @Override
    public int compareTo(Object o)
    {
        Node otherNode = (Node)o;

        if (otherNode.start > start)
        {
            return -1;
        }
        else if (otherNode.start < start)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}