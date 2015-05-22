// given stream of integers find top k
// r2, q4, set26

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,50,60,70,80};
        FixedBST tree = new FixedBST(5);
        for (Integer value : array)
        {
            tree.insert(value);
            System.out.println("after inserting " + value + " " + tree.getMinNode().getValue());
        }
    }
}

class FixedBST
{
    private Node head;
    private Node minNodeParent;
    final private Integer size;
    private Integer nodeCount;

    public FixedBST(Integer size)
    {
        this.head = null;
        this.minNodeParent = null;
        this.size = size;
        this.nodeCount = 0;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            updateMin();
            ++nodeCount;
        }
        else
        {
            if (nodeCount == size
                    && getMinNode().getValue() < value)
            {
                insert(head, value);
                updateMin();
            }
            else
            {
                insert(head, value);
                updateMin();
                ++nodeCount;
            }
        }
    }

    private void updateMin()
    {
        if (nodeCount == size)
        {
            Node minNode = getMinNode();
            if (minNode.getValue() == head.getValue())
            {
                head = head.getRight();
            }
            else if (minNode.getRight() != null)
            {
                minNodeParent.setLeft(minNode.getRight());
            }
            else
            {
                minNodeParent.setLeft(null);
            }
        }
        setMinNodeParent();
    }

    private void setMinNodeParent()
    {
        if(head == null)
        {
            return;
        }

        if (head.getLeft() == null)
        {
            minNodeParent = null;
            return;
        }
        else if (head.getLeft() != null && head.getLeft().getLeft() == null)
        {
            minNodeParent = head;
            return;
        }
        else
        {

            Node cursor = head;
            while (cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
            {
                cursor = cursor.getLeft();
            }
            minNodeParent = cursor;
        }
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
            if(node.getLeft() != null)
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