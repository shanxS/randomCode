// uniq boolean rows in matrix

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] array = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 1, 1, 0, 0}
        };

        int[] dump = new int[array.length];
        for (Integer c=0; c<array[0].length; ++c)
        {
            for (Integer r = 0; r < array.length; ++r)
            {
                dump[r] += Math.pow(2, c) * array[r][c];
            }
        }

        BST tree = new BST();
        for (Integer r=0; r<dump.length; ++r)
        {
            if (tree.insert(dump[r]))
            {
                System.out.println("üniq " + r);
            }
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public boolean insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            return true;
        }
        else
        {
            return insert(head, value);
        }
    }

    private boolean insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
        else
        {
            return false;
        }

        return true;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

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