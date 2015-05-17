// BST to hold greatest k elements
// r3, q3, set21, question was not clear - this is the nearest solution

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{70,50,90,100,110,60,80,120};
        ModifiedBST tree = new ModifiedBST(3);
        for (Integer value : array)
        {
            tree.insert(value);
            System.out.print(tree.getMinNode().getValue() + " ");
        }
    }
}

class ModifiedBST
{
    private Node head;
    final private Integer size;
    private Integer nodeCount;
    private Node minNodeParent;
    private Boolean ruleLeftLess;

    public ModifiedBST(Integer size)
    {
        this.head = null;
        this.size = size;
        this.nodeCount = 0;
        this.minNodeParent = null;
        this.ruleLeftLess = true;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            removeMin();
        }
        else
        {
            if (nodeCount == size)
            {
                if (getMinNode().getValue() > value)
                {
                    return;
                }
                else
                {
                    insert(head, value);
                    removeMin();
                }
            }
            else
            {
                insert(head, value);
                removeMin();
            }
        }
    }

    private void removeMin()
    {
        if (nodeCount < size)
        {
            ++nodeCount;
        }
        else
        {
            if (minNodeParent != null)
            {
                Node minNode = minNodeParent.getLeft();
                if (minNode.getRight() != null)
                {
                    minNodeParent.setLeft(minNode.getRight());
                }
                else
                {
                    minNodeParent.setLeft(null);
                }
            }
            else
            {
                head = head.getRight();
            }
        }

        setMinNodeParent();
    }

    private void setMinNodeParent()
    {
        if (head == null || head.getLeft() == null)
        {
            minNodeParent = null;
            return;
        }

        Node cursor = head;
        while (cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        minNodeParent = cursor;

    }

    public Node getMinNode()
    {
        if (head == null)
        {
            return null;
        }

        if (minNodeParent == null)
        {
            return head;
        }

        return minNodeParent.getLeft();
    }

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == ruleLeftLess)
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
        else if ((node.getValue() > value) != ruleLeftLess)
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
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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