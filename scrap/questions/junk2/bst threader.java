// bst threader

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {6,3,8,1,5,7,11,50,60,9,13};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }

        BSTThreader threader = new BSTThreader();
        threader.thread(bst.getHead());
        threader.print(bst.getHead());

    }
}

class BSTThreader
{
    public Node thread(Node node)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = thread(node.getLeft());
        if (leftNode != null)
        {
            leftNode.setRight(node);
        }

        Node rightNode = thread(node.getRight());
        return (rightNode == null) ? node : rightNode;
    }

    public void print(Node head)
    {
        Node cursor = head;

        while (cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
            if (cursor != null)
            {
                System.out.print(cursor.getValue() + " ");
                cursor = cursor.getRight();
            }

            while (cursor != null && cursor.getLeft() != null)
            {
                cursor = cursor.getLeft();
            }
        }
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