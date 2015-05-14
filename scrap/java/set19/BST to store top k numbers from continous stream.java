// BST to store top k numbers from continous stream
// r4, q1, set19

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{7,5,9,8,10,6,11,12};
        ModifiedBST mBST = new ModifiedBST(5);
        for (Integer value : array)
        {
            mBST.insert(value);
            System.out.println(mBST.getMinNode().getValue());
        }
    }
}


class ModifiedBST
{
    private Node head;
    final private Integer size;
    private Integer nodeCount;
    private Node minParent;
    private Boolean ruleLeftLessThan;

    public ModifiedBST(Integer size)
    {
        this.head = null;
        this.size = size;
        this.nodeCount = 0;
        this.minParent = null;
        this.ruleLeftLessThan = true;
    }

    public void  insert(Integer value)
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
                    removeMin();
                }
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
            Node previousMinNode = getMinNode();

            if (minParent != null)
            {
                if (previousMinNode.getRight() != null)
                {
                    minParent.setLeft(previousMinNode.getRight());
                } else
                {
                    minParent.setLeft(null);
                }
            }
            else
            {
                head = head.getRight();
            }
        }

        setMinParent();
    }

    private void setMinParent()
    {
        if (nodeCount <= 1)
        {
            minParent = null;
            return;
        }

        if (head.getLeft() == null)
        {
            minParent = null;
            return;
        }

        Node cursor = head;
        while(cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }
        minParent = cursor;
    }

    public Node getMinNode()
    {
        if (head == null)
        {
            return null;
        }

        if (minParent == null)
        {
            return head;
        }

        return minParent.getLeft();
    }

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == ruleLeftLessThan)
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
        else if ((node.getValue() > value) != ruleLeftLessThan)
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