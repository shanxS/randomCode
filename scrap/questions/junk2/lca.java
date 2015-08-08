lca

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

        (new LCA()).find(bst.getHead(),8,10);
    }
}

class LCA
{
    public void find(Node head, int v1, int v2)
    {
        LCAFinder(head, v1, v2, null);
    }

    private void LCAFinder(Node node, int v1, int v2, Node parent)
    {
        if (node == null)
        {
            return;
        }

        if (node.getValue() == v1 || node.getValue() == v2)
        {
            System.out.print(node.getValue());
            return;
        }
        if (node.getValue() > v1 && node.getValue() < v2)
        {
            System.out.print(node.getValue());
            return;
        }
        else if (node.getValue() < v1)
        {
            LCAFinder(node.getRight(), v1, v2, node);
            return;
        }
        else
        {
            LCAFinder(node.getLeft(), v1, v2, node);
            return;
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