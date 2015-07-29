// Remove nodes on root to leaf paths of length < K

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,null,6,
            7,null,null,null,null,null,8};

        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);

        System.out.print("====================");

        Trimmer trimmer = new Trimmer();
        trimmer.trim(tree, 4);
        btUtil.print(tree);
    }
}

class Trimmer
{
    private Integer minDepth;
    public void trim(Node head, Integer minDepth)
    {
        this.minDepth = minDepth;
        processFor(head, 1);
    }

    private Integer processFor(Node node, Integer thisDepth)
    {
        if (node == null)
        {
            return thisDepth-1;
        }

        Integer leftDepth = processFor(node.getLeft(), thisDepth+1);
        if (leftDepth < minDepth)
        {
            node.setLeft(null);
        }

        Integer rightDepth = processFor(node.getRight(), thisDepth+1);
        if (rightDepth < minDepth)
        {
            node.setRight(null);
        }

        return Math.max(leftDepth, rightDepth);
    }
}

class BTUtil
{
    public Node make (Integer[] ar)
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
}