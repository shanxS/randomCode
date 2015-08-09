bt to dll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,6,7,
                null,null,null,null,null, 8};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
        util.print(head);

        Node node = (new BT2DLL()).convert(head);
        while (node.getRight() != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getRight();
        }
        System.out.println(node.getValue());

        while (node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getLeft();
        }
    }
}

class BT2DLL
{
    public Node convert(Node head)
    {
        Node cursor = convertFor(head, null);
        while (cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        return cursor;
    }

    private Node convertFor(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = convertFor(node.getLeft(), parent);
        if (leftNode != null)
        {
            leftNode.setRight(node);
            node.setLeft(leftNode);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightNode = convertFor(node.getRight(), node);

        return (rightNode == null) ? node : rightNode;
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