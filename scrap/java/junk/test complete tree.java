// test complete tree

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {1,2,3,4};
        Integer[] ar = {1,2,3,4,5};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);

        DepthTester dt = new DepthTester();
        System.out.print(dt.test(tree));
    }
}

class DepthTester
{
    public boolean test(Node node)
    {
        if (node == null)
        {
            return true;
        }

        if ((node.getLeft() != null && node.getRight() == null)
                || (node.getLeft() == null && node.getRight() != null))
        {
            return false;
        }

        return test(node.getLeft()) && test(node.getRight());
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
    public Node left, right;
    final public Integer value;

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