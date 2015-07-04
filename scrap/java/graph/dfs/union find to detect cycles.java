// union find with Union By Rank and Path Compression

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,1,0},
                {0,0,1},
                {0,0,0},
        };

        UnionFind uf = new UnionFind();
        System.out.print(uf.hasCycle(adjMatrix));
    }
}

class UnionFind
{
    private Map<Integer, Node> valueNode;

    public Boolean hasCycle(Integer[][] adjMatrix)
    {
        createNodes(adjMatrix.length);

        for (Integer parentValue=0; parentValue<adjMatrix.length; ++parentValue)
        {
            Integer[] children = adjMatrix[parentValue];
            Integer parentGroup = findGroup(parentValue).getValue();
            for (Integer childValue = 0; childValue < children.length; ++childValue)
            {
                if (children[childValue] == 1)
                {
                    Integer childGroup = findGroup(childValue).getValue();
                    if (childGroup == parentGroup)
                    {
                        return true;
                    }

                    union(childGroup, parentGroup);
                }
            }
        }

        return false;
    }

    private void union(Integer nodeValue1, Integer nodeValue2)
    {
        Node head1 = findGroup(nodeValue1);
        Node head2 = findGroup(nodeValue2);

        if (head1.getSubNodeCount() > head2.getSubNodeCount())
        {
            head2.setParent(head1);
        }
        else
        {
            head1.setParent(head2);
        }
    }

    private Node findGroup(Integer nodeValue)
    {
        Node startNode = valueNode.get(nodeValue);
        Node node =  startNode;

        while (!node.isHead())
        {
            node = node.getParent();
        }

        if (startNode.getParent() != null && (startNode.getParent().getValue() != node.getValue()))
        {
            startNode.setParent(node);
        }

        return node;
    }

    private void createNodes(Integer nodeCount)
    {
        valueNode = new HashMap<>();
        for (Integer value = 0; value<nodeCount; ++value)
        {
            Node node = new Node(value);
            valueNode.put(value, node);
        }
    }
}

class Node
{
    private Node parent;
    private Integer value;
    private Integer subNodeCount;

    public Node(Integer v)
    {
        value = v;
        parent = null;
        subNodeCount = 0;
    }

    public Integer getSubNodeCount()
    {
        return subNodeCount;
    }

    public void setSubNodeCount(Integer subNodeCount)
    {
        this.subNodeCount = subNodeCount;
    }

    public Boolean isHead()
    {
        return parent == null;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
}