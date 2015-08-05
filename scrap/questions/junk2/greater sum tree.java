// greater sum tree

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {11,2,29,1,7,15,40,35};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }

        bst.print(bst.getHead());
        System.out.println("---------------");
        (new Converter()).conver(bst.getHead());
        bst.print(bst.getHead());
    }
}

class Converter
{
    public void conver(Node head)
    {
        covertFor(head, 0);
    }

    private int covertFor(Node node, int parent)
    {
        if (node == null)
        {
            return 0;
        }

        int rightValue = covertFor(node.getRight(), parent);
        int cache = node.getValue();
        if (rightValue == 0)
        {
            node.setValue(parent);
            rightValue = parent + cache;
        }
        else
        {
            node.setValue(rightValue);
            rightValue += cache;
        }
        int leftValue = covertFor(node.getLeft(), rightValue);

        return (leftValue == 0) ? rightValue : leftValue;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
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

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if(node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
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
    private Node left, right;
    private int value;

    public Node(Integer i)
    {
        value = i;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
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