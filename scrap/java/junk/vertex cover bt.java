// vertex cover bt

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] re)
    {
        Integer[] ar = {10,20,30,40,50,
        null,
        60,
        null, null, 70, 80
        };

        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);

        System.out.println();

        VertexCover vc = new VertexCover(tree);
        System.out.println(vc.findCover());
        vc.printVertices();
    }
}

class VertexCover
{
    private Map<Node, Integer> includeCost, excludeCost;
    private Node head;

    public VertexCover(Node node)
    {
        head = node;
    }

    public Integer findCover()
    {
        includeCost = new HashMap<>();
        excludeCost = new HashMap<>();

        return Math.min(findFor(head, true), findFor(head, false));
    }

    private int findFor(Node node, boolean parentIncluded)
    {
        if (node == null)
        {
            return 0;
        }

        if (parentIncluded)
        {
            Integer thisIncludeCost = includeCost.get(node);
            if (thisIncludeCost == null)
            {
                thisIncludeCost = 1 + findFor(node.getLeft(), true) + findFor(node.getRight(), true);
                includeCost.put(node, thisIncludeCost);
            }

            Integer thisExcludeCost = excludeCost.get(node);
            if (thisExcludeCost == null)
            {
                thisExcludeCost = 0 + findFor(node.getLeft(), false) + findFor(node.getRight(), false);
                excludeCost.put(node, thisExcludeCost);
            }

            return Math.min(thisExcludeCost, thisIncludeCost);
        }
        else
        {
            Integer thisIncludeCost = includeCost.get(node);
            if (thisIncludeCost == null)
            {
                thisIncludeCost = 1 + findFor(node.getLeft(), true) + findFor(node.getRight(), true);
                includeCost.put(node, thisIncludeCost);
            }

            return thisIncludeCost;
        }
    }

    public void printVertices()
    {
        if (includeCost.get(head) < excludeCost.get(head))
        {
            System.out.print(head.getValue() + " ");
            printFor(head.getLeft(), true);
            printFor(head.getRight(), true);
        }
        else
        {
            printFor(head.getLeft(), false);
            printFor(head.getRight(), false);
        }
    }

    private void printFor(Node node, boolean parentIncluded)
    {
        if (node == null)
        {
            return;
        }

        if (!parentIncluded)
        {
            System.out.print(node.getValue() + " ");
            printFor(node.getLeft(), true);
            printFor(node.getRight(), true);
        }
        else
        {
            if (includeCost.get(node) < excludeCost.get(node))
            {
                System.out.print(node.getValue() + " ");
                printFor(node.getLeft(), true);
                printFor(node.getRight(), true);
            }
            else
            {
                printFor(node.getLeft(), false);
                printFor(node.getRight(), false);
            }
        }
    }
}

class BTUtil
{
    public void print(Node node)
    {
        if(node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public Node make(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parentNode = nodes[getParentIndex(i)];
            if (i%2 == 0)
            {
                parentNode.setRight(new Node(ar[i]));
                nodes[i] = parentNode.getRight();
            }
            else
            {
                parentNode.setLeft(new Node(ar[i]));
                nodes[i] = parentNode.getLeft();
            }
        }

        return nodes[0];
    }

    private int getParentIndex(Integer index)
    {
        return (index-1)/2;
    }
}

class Node
{
    private Node left, right;
    final private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}