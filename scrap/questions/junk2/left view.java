left view

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {12,10,30,null,null,25,40};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
        (new LeftView()).print(head);
    }
}

class LeftView
{
    private int maxDepth;
    public void print(Node head)
    {
        maxDepth = -1;
        printFor(head, 0);
    }

    private void printFor(Node node, int thisDepth)
    {
        if (node == null)
        {
            return;
        }

        if (thisDepth > maxDepth)
        {
            System.out.print(node.getValue() + " ");
            maxDepth = thisDepth;
        }

        printFor(node.getLeft(), thisDepth+1);
        printFor(node.getRight(), thisDepth+1);
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