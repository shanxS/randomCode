// bst to dll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 50, 200, 25, 90, 150, 75, 95, 175};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }
        tree.print(tree.getHead());

        BSTToDLL b2d = new BSTToDLL();
        Node head = b2d.convert(tree.getHead());

        Node cursor = head;
        while (cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println(cursor.getValue());

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }

    }
}

class BSTToDLL
{
    public Node convert(Node head)
    {
        Node cursor = converFor(head, null);
        while (cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        return cursor;
    }

    private Node converFor(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = converFor(node.getLeft(), parent);
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

        Node rightParent = converFor(node.getRight(), node);

        return (rightParent == null) ? node : rightParent;
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
        value = v;
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