// selective level order traversal

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);

        Printer printer = new Printer();
        printer.print(tree);
    }
}

class Printer
{
    public void print(Node head)
    {
        List<Node> A1 = new ArrayList<>();
        List<Node> A2 = new ArrayList<>();

        A1.add(head);

        while (A1.size() > 0 || A2.size() > 0)
        {
            if (A1.size() > 0)
            {
                populateChildren(A1, A2);
                print(A1);
                A1.clear();
            }
            else
            {
                populateChildren(A2, A1);
                print(A2);
                A2.clear();
            }
        }
    }

    private void populateChildren(List<Node> from, List<Node> to)
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

    private void print(List<Node> A)
    {
        if (A.size() == 1)
        {
            System.out.println(A.get(0).getValue());
        }

        Integer fwd = 0, bkwd = A.size()-1;

        while (fwd < bkwd)
        {
            System.out.print(A.get(fwd).getValue() + " ");
            ++fwd;

            System.out.print(A.get(bkwd).getValue() + " ");
            --bkwd;
        }

        System.out.println();
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
            System.out.print(node.getLeft());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight());
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
}
