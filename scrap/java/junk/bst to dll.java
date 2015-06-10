// bst to dll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {20, 10, 30, 5, 15, 25, 27};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());
        System.out.print("---------------");

        BSTToDLL b2d = new BSTToDLL();
        Node cursor = b2d.convert(bst.getHead());
        while(cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println(cursor.getValue() + " ");

        while(cursor != null)
        {
            System.out.print(cursor.getValue() +  " ");
            cursor = cursor.getLeft();
        }
    }
}

class BSTToDLL
{
    public Node convert(Node head)
    {
        convert(head, null);
        Node cursor = head;
        while(cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }
        return cursor;
    }

    private Node convert(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftChlid = convert(node.getLeft(), parent);
        if (leftChlid != null)
        {
            node.setLeft(leftChlid);
            leftChlid.setRight(node);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightChild = convert(node.getRight(), node);
        return (rightChild == null) ? node : rightChild;
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