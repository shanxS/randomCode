// practice - store top k elements form continous stream

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{7,5,10,9,11,6,12};
        ModifiedBST tree = new ModifiedBST(5);
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
    final private Boolean ruleLeftLessThan;
    private Integer nodeCount;
    private Node minParentNode;


    public ModifiedBST(Integer size)
    {
        this.head = null;
        this.size = size;
        this.ruleLeftLessThan = true;
        this.nodeCount = 0;
        this.minParentNode = null;
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
            if(minParentNode == null)
            {
                head = minParentNode.getRight();
            }
            else if (minParentNode != null)
            {
                if (getMinNode().getRight() != null)
                {
                    minParentNode.setLeft(getMinNode().getRight());
                }
                else
                {
                    minParentNode.setLeft(null);
                }
            }
        }

        setMinParentNode();
    }

    private void setMinParentNode()
    {
        if (head.getLeft() == null)
        {
            minParentNode = null;
            return;
        }

        Node cursor = head;
        while (cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        minParentNode = cursor;
    }

    public Node getMinNode()
    {
        if (head == null)
        {
            return null;
        }

        if (minParentNode == null)
        {
            return head;
        }

        return minParentNode.getLeft();
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
        else if((node.getValue() > value) != ruleLeftLessThan)
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