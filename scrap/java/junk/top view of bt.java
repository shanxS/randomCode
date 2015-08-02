// top view of bt

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,
        null,
        4,      null,null,
                null,null,null,
        5,      null,null,null,null,
                null,null,null,null,null,null,null,
        6,      null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
        7};

        BTUtil btutil = new BTUtil();
        Node tree = btutil.make(ar);
        btutil.print(tree);
        System.out.println("====================");

        TopPrinter tp = new TopPrinter();
        tp.print(tree);
    }
}

class TopPrinter
{
    private Map<Integer, Node> horizNodeMap;
    private Map<Integer, Integer> horizDepthMap;

    public void print(Node head)
    {
        horizNodeMap = new HashMap<>();
        horizDepthMap = new HashMap<>();

        compute(head,0, 0);

        for (Map.Entry<Integer, Node> entry : horizNodeMap.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue().getValue());
        }
    }

    private void compute(Node node, Integer depth, Integer horiz)
    {
        if (node == null)
        {
            return;
        }

        if (horizDepthMap.get(horiz) == null
                || horizDepthMap.get(horiz) > depth)
        {
            horizDepthMap.put(horiz, depth);
            horizNodeMap.put(horiz, node);
        }

        compute(node.getLeft(), depth+1, horiz-1);
        compute(node.getRight(), depth+1, horiz+1);
    }
}

class BTUtil
{
    public void print(Node node)
    {
        if (node == null)
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
            if (i % 2 == 0)
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