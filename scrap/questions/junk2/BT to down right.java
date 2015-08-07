// BT to down right

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,
                null,null,
        4,5,
                null,null,null,null,
        6,null,
        8,9};

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
        btUtil.print(head);

        System.out.println("-------------");

        (new DownRight()).convert(head);
        btUtil.print(head);
    }
}

class DownRight
{
    public void convert(Node head)
    {
        List<Node> l1 = new ArrayList<>();
        List<Node> l2 = new ArrayList<>();

        l1.add(head);

        while (l1.size()>0 || l2.size()>0)
        {
            if (l1.size() > 0)
            {
                pushChildren(l1, l2);
                if (l2.size() > 0)
                {
                    l1.get(0).setLeft(l2.get(0));
                }
                rewire(l1);
                l1.clear();
            }
            else
            {
                pushChildren(l2, l1);
                if (l1.size() > 0)
                {
                    l2.get(0).setLeft(l1.get(0));
                }
                rewire(l2);
                l2.clear();
            }
        }
    }

    private void rewire(List<Node> l)
    {
        if (l.size() > 1)
        {
            l.get(0).setRight(l.get(1));
            for (Integer i=1; i<l.size()-1; ++i)
            {
                l.get(i).setLeft(null);
                l.get(i).setRight(l.get(i+1));
            }
            l.get(l.size()-1).setRight(null);
            l.get(l.size()-1).setLeft(null);
        }
        else
        {
            l.get(0).setRight(null);
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