// bottom printer

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {20,8,22,5,3,
//        null,
//        25,
//                null,null,
//                10,14};
        Integer[] ar = {20,8,22,5,3,4,25,
        null,null,10};

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
        (new BottomPrinter()).print(head);
    }
}

class BottomPrinter
{
    private Map<Integer, Integer> horizDepth, horizValue;
    public void print(Node head)
    {
        horizDepth = new HashMap<>();
        horizValue = new HashMap<>();

        evaluate(head, 0, 0);

        for (Map.Entry<Integer, Integer> entry : horizValue.entrySet())
        {
            System.out.println(entry.getValue() + " ");
        }
    }

    private void evaluate(Node node, int horiz, int depth)
    {
        if (node == null)
        {return;}

        if (horizDepth.get(horiz) == null || horizDepth.get(horiz) <= depth)
        {
            horizDepth.put(horiz, depth);
            horizValue.put(horiz, node.getValue());
        }

        evaluate(node.getLeft(), horiz-1, depth+1);
        evaluate(node.getRight(), horiz+1, depth+1);
    }
}

class BTUtil
{

    public Node makeFor(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {continue;}

            Node parent = nodes[getParentIndex(i)];
            if (i%2 == 0)
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
            else
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
        }

        return nodes[0];
    }

    private int getParentIndex(Integer i)
    {
        return (i-1)/2;
    }

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
}


class Node
{
    private Node left, right;
    private Integer value;

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

    public void setValue(Integer value)
    {
        this.value = value;
    }
}