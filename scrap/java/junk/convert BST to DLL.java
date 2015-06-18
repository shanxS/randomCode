// convert BST to DLL

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10, 5, 20, 9, 15, 29, 8};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        tree.print();
        BSTToDLL b2d = new BSTToDLL();
        b2d.convert(tree.getHead());
    }
}

class BSTToDLL
{
    public void convert(Node head)
    {
        Node cursor  = convert(head, null);

        while (cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        while (cursor.getRight() !=  null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.print(cursor.getValue() + " ");
        System.out.println();
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
    }

    private Node convert(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = convert(node.getLeft(), parent);
        if (leftNode != null)
        {
            leftNode.setRight(node);
            node.setLeft(leftNode);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightNode = convert(node.getRight(), node);
        return (rightNode == null) ? node : rightNode;
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
        BST.print(head);
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

    public Node (Integer value)
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