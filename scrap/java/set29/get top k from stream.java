// get top k from stream
// r4, q2, set29

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{
                6,5,7,1,0,3,8,9,10,11,12,13,14
        };
        ModifiedBST tree = new ModifiedBST(5);
        for(Integer value : array)
        {
            tree.insert(value);
            System.out.println("value " + value + " min " + tree.getMinNode().getValue());
        }

    }
}

class ModifiedBST
{
    final private Integer size;
    private Node head;
    private Integer nodeCount;
    private Node minNodeParent;

    public ModifiedBST(Integer size)
    {
        this.size = size;
        this.head = null;
        this.nodeCount = 0;
        this.minNodeParent = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            removeMin();
            ++nodeCount;
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
                }
                removeMin();
            }
            else
            {
                insert(head, value);
                removeMin();
                ++nodeCount;
            }
        }
    }

    private void removeMin()
    {
        if (nodeCount == size)
        {
            if (minNodeParent == null)
            {
                head = head.getRight();
            }
            else
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
        }

        setMinParentNode();
    }

    private void setMinParentNode()
    {
        if (head == null)
        {
            minNodeParent = null;
            return;
        }

        if (head.getLeft() == null)
        {
            minNodeParent = null;
            return;
        }

        Node cursor = head;
        while(cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        minNodeParent = cursor;
        return;
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


}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
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
}