test if leaves are on same level

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {12,5,7,3};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
        (new LeafLevelTeater()).test(head);
    }
}

class LeafLevelTeater
{
    private Integer depth;
    public void test(Node head)
    {
        depth = null;

        System.out.print(findFor(head, 0));
    }

    private boolean findFor(Node node, int thisDepth)
    {
        if (node == null)
        {
            return true;
        }
        if (isLeaf(node))
        {
            if (depth != null && thisDepth != depth)
            {
                return false;
            }
            else
            {
                depth = thisDepth;
            }
        }

        return findFor(node.getLeft(), thisDepth+1) && findFor(node.getRight(), thisDepth+1);
    }

    private boolean isLeaf(Node node)
    {
        return node.getLeft() == null && node.getRight() == null;
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