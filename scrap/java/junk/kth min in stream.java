// kth min in stream

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100,50,150,40,70,125,200};
        ModifiedBST tree = new ModifiedBST(3);
        for (Integer i : ar)
        {
            tree.insert(i);
            System.out.print("inserted " + i);
            if (tree.getMinNode() != null)
            {
                System.out.print(" min: " + tree.getMinNode().getValue() + " ");
            }
            System.out.println();
        }
    }
}

class ModifiedBST
{
    private Node head, minParent;
    private Integer nodeCount;
    final private Integer K;

    public ModifiedBST(Integer k)
    {
        head = null;
        minParent = null;
        nodeCount = 0;
        K = k;
    }

    public void insert(Integer n)
    {
        if (head == null)
        {
            head = new Node(n);
            ++nodeCount;
        }
        else
        {
            if (nodeCount < K)
            {
                insert(head, n);
                setMinParent();
                ++nodeCount;
            }
            else if (getMinNode().getValue() < n)
            {
                removeMin();
                insert(head, n);
                setMinParent();
            }
        }
    }

    private void removeMin()
    {
        if (minParent == null)
        {
            head = head.getRight();
        }
        else
        {
            minParent.setLeft(minParent.getLeft().getRight());
        }
    }

    private void setMinParent()
    {
        if (head == null)
        {
            return;
        }

        Node cursor = head;
        while (cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        if (cursor == head && head.getLeft() == null)
        {
            minParent = null;
        }
        else
        {
            minParent = cursor;
        }
    }

    private void insert(Node node, Integer n)
    {
        if (node.getValue() > n)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), n);
            }
            else
            {
                node.setLeft(new Node(n));
            }
        }
        else if (node.getValue() < n)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), n);
            }
            else
            {
                node.setRight(new Node(n));
            }
        }
    }

    public Node getMinNode()
    {
        if (nodeCount < K)
        {
            return null;
        }
        else if (minParent == null)
        {
            return head;
        }
        else
        {
            return minParent.getLeft();
        }
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