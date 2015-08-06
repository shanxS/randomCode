// vertex cover

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,20,30,40,50,null,60, null, null, 70, 80};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.makeFor(ar);
        btUtil.print(tree);
        System.out.println("-------------");
        System.out.print((new VertexCover()).findFor(tree));
    }
}

class VertexCover
{
    private final boolean parentTaken = true;
    public Integer findFor(Node head)
    {
        Integer thisTakenCost = 1 + evaluate(head.getLeft(), parentTaken) + evaluate(head.getRight(), parentTaken);
        Integer thisNotTakenCost = evaluate(head.getLeft(), !parentTaken) + evaluate(head.getRight(), !parentTaken);

        return Math.min(thisNotTakenCost,
                thisTakenCost);
    }

    private Integer evaluate(Node node, boolean parentStatus)
    {
        if (node == null)
        {
            return 0;
        }

        Integer thisTakenCost = 1 + evaluate(node.getLeft(), parentTaken) + evaluate(node.getRight(), parentTaken);
        Integer thisNotTakenCost = evaluate(node.getLeft(), !parentTaken) + evaluate(node.getRight(), !parentTaken);

        if (parentStatus != parentTaken)
        {
            return thisTakenCost;
        }
        else
        {
            return Math.min(thisNotTakenCost, thisTakenCost);
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