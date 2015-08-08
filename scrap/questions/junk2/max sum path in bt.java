// max sum path in bt

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {15,5,6,-8,1,3,9,2,6,
        null,null, null,null, null, 0,
                null,null,null,null,null,null,null,null,null,null,null,null, null, null,
        4,-1,
                null,null,null,null,null,null,null,null,null,null,null,null, null, null,
                null,null,null,null,null,null,null,null,null,null,null,null, null, null,
        null,null,10};

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
//        btUtil.print(head);
        (new MaxPathFinder()).find(head);
    }
}

class MaxPathFinder
{
    private Integer globalMax;
    public void find(Node head)
    {
        globalMax = Integer.MIN_VALUE;

        processFor(head);

        System.out.print(globalMax);
    }

    private int processFor(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        int leftSum = processFor(node.getLeft());
        int rightSum = processFor(node.getRight());

        int thisSum = leftSum + rightSum + node.getValue();
        globalMax = Math.max(globalMax, thisSum);

        return node.getValue() + Math.max(leftSum, rightSum);
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