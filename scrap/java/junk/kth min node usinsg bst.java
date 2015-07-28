// kth min node

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
            if (tree.getMaxNode() != null)
            {
                System.out.print(" min: " + tree.getMaxNode().getValue() + " ");
            }
            System.out.println();
        }
    }
}

class ModifiedBST
{
    private Node head, maxParent;
    private Integer nodeCount;
    private final Integer K;

    public ModifiedBST(Integer k)
    {
        head = null;
        maxParent = null;
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
                setMaxParent();
                ++nodeCount;
            }
            else if (getMaxNode().getValue() > n)
            {
                removeMaxNode();
                insert(head, n);
                setMaxParent();
            }
        }
    }

    private void removeMaxNode()
    {
        if (maxParent == null)
        {
            head = head.getLeft();
        }
        else
        {
            maxParent.setRight(maxParent.getRight().getLeft());
        }
    }

    private void setMaxParent()
    {
        if (head == null)
        {
            return;
        }
        
        Node cursor = head;
        while (cursor.getRight() != null && cursor.getRight().getRight() != null)
        {
            cursor = cursor.getRight();
        }
        
        if (cursor == head && head.getRight() == null)
        {
            maxParent = null;
        }
        else 
        {
            maxParent = cursor;
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

    public Node getMaxNode()
    {
        if (nodeCount < K)
        {
            return null;
        }
        else if (maxParent == null)
        {
            return head;
        }
        else
        {
            return maxParent.getRight();
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