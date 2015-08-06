// removing half nodes

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,null,4,null,null,6,7};//{2,7,5,null,6,null,9,
        //null,null,1,11,null,null,4};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.makeFor(ar);
        btUtil.print(tree);
        System.out.println("-------------");
        btUtil.print((new HalfNodeRemover()).process(tree));
    }
}

class HalfNodeRemover
{
    public Node process(Node node)
    {
        if (node == null || isLeafNode(node))
        {
            return node;
        }

        Node leftNode = process(node.getLeft());
        Node rightNode = process(node.getRight());

        if (leftNode != null && rightNode != null)
        {
            node.setLeft(leftNode);
            node.setRight(rightNode);

            return node;
        }
        else
        {
            return (leftNode == null) ? rightNode : leftNode;
        }
    }

    private boolean isLeafNode(Node node)
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