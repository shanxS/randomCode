// sandwhich printer

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {20,8,22,4,12,null,null,null,null,10,14};
        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
//        btUtil.print(head);
        LevelSandwhich ls = new LevelSandwhich();
        ls.print(head, 0,2);
    }
}

class LevelSandwhich
{
    public void print(Node head, int s, int e)
    {
        List<Node> l1 = new ArrayList<>();
        List<Node> l2 = new ArrayList<>();
        int depth = 0;

        l1.add(head);
        while (l1.size()>0 || l2.size()>0)
        {
            if (l1.size() > 0)
            {
                if (depth >= s && depth <= e)
                {
                    l1.stream().forEach(x -> System.out.print(x.getValue() + " "));
                }

                pushChildren(l1, l2);
                l1.clear();
            }
            else
            {
                if (depth >= s && depth <= e)
                {
                    l2.stream().forEach(x -> System.out.print(x.getValue() + " "));
                }

                pushChildren(l2, l1);
                l2.clear();
            }

            ++depth;
        }
    }

    private void pushChildren(List<Node> from, List<Node> to)
    {
        for (Node node : from)
        {
            if (node.getLeft() != null)
            {
                to.add(node.getLeft());
            }

            if (node.getRight() != null)
            {
                to.add(node.getRight());
            }
        }
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