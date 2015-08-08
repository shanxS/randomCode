// alternate level order reverser

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
        btUtil.print(head);
        System.out.print("---------------");
        (new Reverser()).reverse(head);
        btUtil.print(head);
    }
}

class Reverser
{
    public void reverse(Node head)
    {
        List<Node> l1 = new ArrayList<>();
        List<Node> l2 = new ArrayList<>();

        l1.add(head);

        while (l1.size() > 0 || l2.size() > 0)
        {
            if (l1.size() > 0)
            {
                pushChildren(l1, l2);
                reverse(l2);
                reconnect(l1, l2);
                l1.clear();
            }
            else
            {
                pushChildren(l2, l1);
                l2.clear();
            }
        }
    }

    private void reconnect(List<Node> from, List<Node> to)
    {
        for (Integer f=0, t=0; f<from.size(); ++f)
        {
            from.get(f).setLeft(to.get(t));
            from.get(f).setRight(to.get(t + 1));

            t += 2;
        }
    }

    private void reverse(List<Node> l2)
    {
        for (int i=0, j=l2.size()-1; i<j; ++i, --j)
        {
            Node last = l2.get(j);
            Node first = l2.get(i);

            swapChildren(last, first);

            l2.set(j, first);
            l2.set(i, last);
        }
    }

    private void swapChildren(Node last, Node first)
    {
        Node tmp = last.getLeft();
        last.setLeft(first.getLeft());
        first.setLeft(tmp);

        tmp = last.getRight();
        last.setRight(first.getRight());
        first.setRight(tmp);
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