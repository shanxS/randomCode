// merge printing bst

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar1 = {8,2,10,1};
        BST bst1 = new BST();
        for (Integer i : ar1)
        {
            bst1.insert(i);
        }

        Integer[] ar2 = {5,3,0};
        BST bst2 = new BST();
        for (Integer i : ar2)
        {
            bst2.insert(i);
        }

        (new BSTMergePrinter()).print(bst1.getHead(), bst2.getHead());
    }
}

class BSTMergePrinter
{
    public void print(Node node1, Node node2)
    {
        if (node1 == null && node2 == null)
        {
            return;
        }

        Node node1Left = node1 == null ? null : node1.getLeft();
        Node node2Left = node2 == null ? null : node2.getLeft();
        print(node1Left, node2Left);

        if (node1 != null && node2 != null)
        {
            System.out.print(Math.min(node1.getValue(), node2.getValue()) + " " + Math.max(node1.getValue(), node2.getValue()) + " ");
        }
        else if (node1 != null)
        {
            System.out.print(node1.getValue() + " ");
        }
        else if (node2 != null)
        {
            System.out.print(node2.getValue() + " ");
        }

        Node node1Right = node1 == null ? null : node1.getRight();
        Node node2Right = node2 == null ? null : node2.getRight();
        print(node1Right, node2Right);
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
        } else
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