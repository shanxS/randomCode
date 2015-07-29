// Find sum of all left leaves in a given Binary Tree

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {20,9,49,5,12,23,52,
        null, null, null, 15, null, null, 50};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        System.out.print((new LeftLeafSummer()).sum(tree));
    }
}

class LeftLeafSummer
{
    public Integer sum(Node node)
    {
        if(node == null)
        {
            return 0;
        }

        Integer thisSum = 0;
        if (node.getLeft() != null && isLeaf(node.getLeft()))
        {
            thisSum += node.getLeft().getValue();
        }
        else
        {
            thisSum += sum(node.getLeft());
        }

        thisSum += sum(node.getRight());

        return thisSum;
    }

    private boolean isLeaf(Node node)
    {
        return (node.getLeft() == null && node.getRight() == null);
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
            if(i%2 == 0)
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

    public Node (Integer v)
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