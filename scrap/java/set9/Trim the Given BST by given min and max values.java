// Trim the Given BST by given min and max values
// r3, q1, set 9, amazon

public class Main
{
    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(17);
        bst.insert(11);
        bst.insert(13);
        bst.insert(16);
        bst.insert(20);

        bst.print();

        bst.removeGreaterThan(3);
        bst.printMin();
        bst.printMax();
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void printMax()
    {
        printConditional(head, true);
    }

    public void printMin()
    {
        printConditional(head, false);
    }

    private void printConditional(Node node, boolean max)
    {
        if (max)
        {
            if (node.getRight() != null)
            {
                printConditional(node.getRight(), max);
            }
            else
            {
                System.out.println("Max in BST " + node.getValue());
            }
        }
        else
        {
            if (node.getLeft() != null)
            {
                printConditional(node.getLeft(), max);
            }
            else
            {
                System.out.println("Min in BST " + node.getValue());
            }
        }
    }

    public void removeLessThan(Integer value)
    {
        Node node = searchNode(head, value);

        if (node == null)
        {
            System.out.print("node not found " + value);
            return;
        }

        node.setLeft(null);
    }

    public void removeGreaterThan(Integer value)
    {
        Node node = searchNode(head, value);

        if (node == null)
        {
            System.out.print("node not found " + value);
            return;
        }

        node.setRight(null);
    }

    private Node searchNode(Node node, Integer value)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }
        else if (node.getValue() > value)
        {
            return searchNode(node.getLeft(), value);
        }
        else if (node.getValue() < value)
        {
            return searchNode(node.getRight(), value);
        }

        return null;
    }

    public void insert(Integer value)
    {
        if (head ==  null)
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

    public void print()
    {
        print(head);
    }

    private void print(Node node)
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