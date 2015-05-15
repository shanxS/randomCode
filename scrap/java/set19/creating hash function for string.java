// creating hash function for string
// f2f 5, q2

public class Main
{
    public static void main(String[] wer)
    {
        String[] array = new String[]{
                "aa",
                "shashank",
                "sumedha",
                "amazon",
                "black",
                "swan"
        };

        BST tree = new BST();
        for (String value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());

        for (String value : array)
        {
            System.out.println(value + " " +  tree.getHash(value));
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
    }

    public void insert(String value)
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

    private void insert(Node node, String value)
    {
        if (node.getValue().compareTo(value) < 0)
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
        else if (node.getValue().compareTo(value) > 0)
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

    public String getHash(String value)
    {
        return findHash(head, value, "");
    }

    private String findHash(Node node, String value, String hash)
    {
        if (node == null)
        {
            return hash;
        }

        if (node.getValue().equals(value))
        {
            return hash;
        }

        if (node.getValue().compareTo(value) < 0)
        {
            return findHash(node.getLeft(), value, hash + "0");
        }
        else if (node.getValue().compareTo(value) > 0)
        {
            return findHash(node.getRight(), value, hash + "1");
        }

        return "";
    }
}

class Node
{
    private Node left, right;
    private String value;

    public Node(String value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}