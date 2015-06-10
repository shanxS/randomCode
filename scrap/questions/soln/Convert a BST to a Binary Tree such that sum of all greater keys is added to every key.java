// Convert a BST to a Binary Tree such that sum of all greater keys is added to every key
// code question: 74

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {4, 2, 6, 1, 3, 5, 7};
        BST tree = new BST();
        for (Integer v : array)
        {
            tree.insert(v);
        }

        BST.print(tree.getHead());
        System.out.println("-------------------");
        ValueAdder va = new ValueAdder();
        va.add(tree.getHead());
        BST.print(tree.getHead());
    }
}


class ValueAdder
{
    public void add(Node head)
    {
        act(head);
    }

    private Integer act(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer rightRes =  act(node.getRight());
        node.setValue(node.getValue() + rightRes);

        return node.getValue() + act(node.getLeft());
    }
}

class BST
{
    private Node head;
    public BST()
    {
        head = null;
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

