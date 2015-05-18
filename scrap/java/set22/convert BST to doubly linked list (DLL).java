// convert BST to doubly linked list (DLL)
// r1, q1, partial set22

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20,10,30,5,15,25};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        Node dll = tree.converToDLL();
        Node cursor = dll;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
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

    public Node converToDLL()
    {
        inorder(head, null);

        Node cursor = head;
        while(cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        return cursor;
    }

    private Node inorder(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node previousNode = inorder(node.getLeft(), parent);

        if (previousNode != null)
        {
            previousNode.setRight(node);
            node.setLeft(previousNode);
        }
        else if (parent != null)
        {
            node.setLeft(parent);
            parent.setRight(node);
        }

        inorder(node.getRight(), node);

        return (node.getRight() == null) ? node : node.getRight();
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
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}