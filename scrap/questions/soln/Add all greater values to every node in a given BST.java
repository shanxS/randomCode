// Add all greater values to every node in a given BST

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {50, 30, 70, 20, 40, 60, 80};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        System.out.println("---------------------");

        MaxAdder ms = new MaxAdder();
        ms.add(tree.getHead());

        BST.print(tree.getHead());

    }
}

class MaxAdder
{
    public void add(Node head)
    {
        reverseInorderTraversal(head);
    }

    private Integer reverseInorderTraversal(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer rightValue = reverseInorderTraversal(node.getRight());
        node.setValue(rightValue + node.getValue());
        Integer leftValue = reverseInorderTraversal(node.getLeft());

        return leftValue + node.getValue();
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
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
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}