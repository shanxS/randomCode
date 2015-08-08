// cousin tester

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {6,3,5,7,8,1,3};
        BTUtil btUtil = new BTUtil();
        Node head = btUtil.makeFor(ar);
        (new CousinTester()).test(head, 7, 3);
    }
}

class CousinTester
{
    private int v1Level, v1Parent, v2Level, v2Parent, v1, v2;
    private boolean v1Found, v2Found;

    public void test(Node head, int v1, int v2)
    {
        v1Found = false;
        v2Found = false;
        this.v1 = v1;
        this.v2 = v2;

        find(head, 0, null);

        if (v1Level == v2Level
                && v1Parent != v2Parent)
        {
            System.out.print("yes");
        }
        else
        {
            System.out.print("no");
        }

    }

    private void find(Node node, int horiz, Node parent)
    {
        if (node == null || (v1Found && v2Found))
        {
            return;
        }

        if (node.getValue() == v1)
        {
            v1Found = true;
            v1Level = horiz;
            v1Parent = (parent == null) ? -1 : parent.getValue();
        }
        else if (node.getValue() == v2)
        {
            v2Found = true;
            v2Level = horiz;
            v2Parent = (parent == null) ? -1 : parent.getValue();
        }

        find(node.getRight(), horiz+1, node);
        find(node.getLeft(), horiz+1, node);
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