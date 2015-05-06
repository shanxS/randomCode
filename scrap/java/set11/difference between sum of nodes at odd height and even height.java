// difference between sum of nodes at odd height and even height

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 15, 30, 10, 18, 25, 40, 2, 19};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());


        Summer summer = new Summer(tree);
        System.out.print(summer.sum());

    }
}

class Summer
{
    private final Node head;
    private Integer evenSum, oddSum;

    public Summer(BST tree)
    {
        this.head = tree.getHead();
        this.evenSum = 0;
        this.oddSum = 0;
    }

    public Integer sum()
    {

        sum(head, 0);

        return oddSum - evenSum;
    }

    private void sum(Node node, int level)
    {
        if (node == null)
        {
            return;
        }

        if (level % 2 == 0)
        {
            evenSum += node.getValue();
        }
        else
        {
            oddSum += node.getValue();
        }

        sum(node.getRight(), level + 1);
        sum(node.getLeft(), level + 1);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    public static void print(Node node)
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

    private void insert(Node node, Integer value)
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
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
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