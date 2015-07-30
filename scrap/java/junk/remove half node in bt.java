// remove half node in bt

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {2,7,5,
//        null,
//        6,
//        null,
//        9,
//        null, null,
//        1, 11,
//        null, null,
//        4};

        Integer[] ar = {2,null,6,null,null,7,8};

        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);

        System.out.println();

        HalfRemover hr = new HalfRemover();
        btUtil.print(hr.removeHalf(tree));
    }
}

class HalfRemover
{
    public Node removeHalf(Node node)
    {
        if (node == null || isLeaf(node))
        {
            return node;
        }

        Node leftNode = removeHalf(node.getLeft());
        Node rightNode = removeHalf(node.getRight());

        if (leftNode == null || rightNode == null)
        {
            if (leftNode != null)
            {
                return leftNode;
            }
            else if (rightNode != null)
            {
                return rightNode;
            }
        }

        node.setLeft(leftNode);
        node.setRight(rightNode);

        return node;
    }

    private boolean isLeaf(Node node)
    {
        return node.getLeft() == null && node.getRight() == null;
    }
}

class BTUtil
{
    public void print(Node node)
    {
        if (node == null)
        {return;}

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

    private Integer getParentIndex(Integer index)
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