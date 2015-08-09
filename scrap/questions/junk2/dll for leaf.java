// dll for leaf

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,null,6,7,8,
        null,null,null,null,9,10};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
        util.print(head);

        Node dll = (new LeafDLL()).makeDLL(head);
        while (dll.getRight() != null)
        {
            System.out.print(dll.getValue() + " ");
            dll = dll.getRight();
        }
        System.out.println(dll.getValue());

        while(dll != null)
        {
            System.out.print(dll.getValue() + " ");
            dll = dll.getLeft();
        }
    }
}

class LeafDLL
{
    private Node dllCursor;
    public Node makeDLL(Node head)
    {
        dllCursor = new Node(-1);
        makeFor(head);
        while (dllCursor.getLeft() != null)
        {
            dllCursor = dllCursor.getLeft();
        }

        return dllCursor;
    }

    private void makeFor(Node node)
    {
        if (node == null)
        {
            return;
        }
        if (isLeaf(node))
        {
            dllCursor.setRight(node);
            node.setLeft(dllCursor);
            dllCursor = node;

            return;
        }

        makeFor(node.getLeft());
        makeFor(node.getRight());
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