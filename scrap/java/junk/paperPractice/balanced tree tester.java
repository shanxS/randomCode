// balanced tree tester

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {32,24,41,21,28,36,14,25,31,39};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }

        BalanceTester bt = new BalanceTester();
        System.out.print(bt.isBalanced(bst.getHead()));
    }
}

class BalanceTester
{
    private boolean status;
    public boolean isBalanced(Node head)
    {
        status = true;

        testFor(head);

        return status;
    }

    private Integer testFor(Node node)
    {
        if (node == null || !status)
        {
            return 0;
        }

        Integer leftDepth = testFor(node.getLeft());
        Integer rightDepth = testFor(node.getRight());

        if (Math.abs(leftDepth-rightDepth) > 1)
        {
            status = false;
        }

        return Math.max(leftDepth, rightDepth) + 1;
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
    final private Integer value;

    public Node (Integer v)
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