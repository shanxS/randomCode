// in and pre

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {20,8,22,4,12,10,14};
        BST bst = new BST();
        for (int i : ar)
        {
            bst.insert(i);
        }

        (new InPreSuc()).find(bst.getHead(), 20);
    }
}

class InPreSuc
{
    public void find(Node head, int v)
    {
        findFor(head, v, null);
    }

    private Node findFor(Node node, int v, Node parent)
    {
        if (node == null)
        {return null;}

        if (parent != null && parent.getValue() == v)
        {
            System.out.println("succ " + node.getValue());
            return node;
        }

        Node leftNode = findFor(node.getLeft(), v, parent);
        if (node.getValue() == v)
        {
            if (leftNode != null)
            {
                System.out.println("pre " + leftNode.getValue());
            }
            else if (parent != null)
            {
                System.out.println("pre " + parent.getValue());
            }

        }
        else if (leftNode != null && leftNode.getValue() == v)
        {
            System.out.println("succ " + node.getValue());
        }


        Node rightNode = findFor(node.getRight(), v, node);
        return (rightNode == null) ? node : rightNode;
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