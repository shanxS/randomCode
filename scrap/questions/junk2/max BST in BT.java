// max BST in BT

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {5,2,4,1,3};
        Integer[] ar = {50,30,60,5,20,45,70,
        null,null,
                null,null,
                null,null,
        65,80};

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
        btUtil.print(head);
        (new Tester()).test(head);
    }
}

class Tester
{
    private Integer globalMax;
    public void test(Node head)
    {
        globalMax = Integer.MIN_VALUE;

        analyseFor(head, null, null);

        System.out.print(globalMax);
    }

    private Integer analyseFor(Node node, Node max, Node min)
    {
        if (node == null)
        {
            return 0;
        }

        if (isLeaf(node))
        {
            return isPart(node, max, min) ? 1 : 0;
        }

        Integer leftCount = analyseFor(node.getLeft(), node, min);
        Integer rightCount = analyseFor(node.getRight(), max, node);

        Integer thisDepth = 0;
        if (isPart(node, max, min))
        {
            thisDepth = leftCount + rightCount + 1;
            globalMax = Math.max(globalMax, thisDepth);

            return thisDepth;
        }
        else
        {
            return 0;
        }
    }

    private boolean isPart(Node node, Node max, Node min)
    {
        boolean status = true;

        if ((max != null && max.getValue() < node.getValue())
                || (min != null && min.getValue() > node.getValue()))
        {
            status = false;
        }

        return status;
    }

    private boolean isLeaf(Node node)
    {
        return node.getRight() == null && node.getLeft() == null;
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