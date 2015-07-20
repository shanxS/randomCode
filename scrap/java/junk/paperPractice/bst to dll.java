// bst to dll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100,50,40,70,45,65,80};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }

        BSTToDLL b2d = new BSTToDLL();
        Node n = b2d.covert(tree.getHead());
        while (n.getRight() != null)
        {
            System.out.print(n.getValue() + " ");
            n = n.getRight();
        }
        System.out.println(n.getValue() + " ");

        while (n != null)
        {
            System.out.print(n.getValue() + " ");
            n = n.getLeft();
        }

    }
}

class BSTToDLL
{
    public Node covert(Node head)
    {
        Node node = convert(head, null);

        while (node.getLeft() != null)
        {
            node = node.getLeft();
        }

        return node;
    }

    private Node convert(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftReturn = convert(node.getLeft(), parent);
        if (leftReturn != null)
        {
            leftReturn.setRight(node);
            node.setLeft(leftReturn);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightReturn = convert(node.getRight(), node);
        return (rightReturn == null) ? node : rightReturn;
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

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
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
    private Integer value;

    public Node(Integer v)
    {
        this.value = v;
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