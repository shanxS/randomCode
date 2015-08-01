// Convert left-right representation of a binary tree to down-right

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
        6,
        null,
        7,8};

        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);

        System.out.print("================");

        Converter c = new Converter();
        c.convert(tree);
        btUtil.print(tree);
    }
}

class Converter
{
    public void convert(Node head)
    {
        List<Node> A1 = new ArrayList<>();
        List<Node> A2 = new ArrayList<>();
        boolean readA1 = true;

        A1.add(head);
        while (A1.size() > 0 || A2.size() > 0)
        {
            if (readA1)
            {
                populateChilderen(A1, A2);
                Node bottom = null;
                if (A2.size() > 0)
                {
                    bottom = A2.get(0);
                }

                A1.get(0).setLeft(bottom);
                A1.get(0).setRight(null);
                horizontalConnect(A1);

                readA1 = !readA1;
                A1.clear();
            }
            else
            {
                populateChilderen(A2, A1);
                Node bottom = null;
                if (A1.size() > 0)
                {
                    bottom = A1.get(0);
                }

                A2.get(0).setLeft(bottom);
                A2.get(0).setRight(null);
                horizontalConnect(A2);

                readA1 = !readA1;
                A2.clear();
            }
        }
    }

    private void populateChilderen(List<Node> A1, List<Node> A2)
    {
        for (Node node : A1)
        {
            if (node.getLeft() != null)
            {
                A2.add(node.getLeft());
            }
            if (node.getRight() != null)
            {
                A2.add(node.getRight());
            }
        }
    }

    private void horizontalConnect(List<Node> A1)
    {
        for (Integer i=0; i<A1.size()-1; ++i)
        {
            A1.get(i).setRight(A1.get(i + 1));
            A1.get(i+ 1).setLeft(null);
            A1.get(i+1).setRight(null);
        }
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