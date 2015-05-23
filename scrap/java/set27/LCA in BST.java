// LCA in BST
// r3, q4, set27

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30,20,50,10,40,55,5,35,60};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        LCAFinder finder = new LCAFinder();
        System.out.print(finder.find(tree.getHead(), 55, 35).getValue());
    }
}

class LCAFinder
{
    public Node find(Node node, Integer value1, Integer value2)
    {
        if (node == null)
        {
            return  null;
        }

        if (node.getValue() > value1 && node.getValue() > value2)
        {
            return find(node.getLeft(), value1, value2);
        }
        else if (node.getValue() < value1 && node.getValue() < value2)
        {
            return find(node.getRight(), value1, value2);
        }

        return node;
    }
}


class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
        else if (node.getValue() < value )
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

    public Node (Integer value)
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