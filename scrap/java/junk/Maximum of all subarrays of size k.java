// Maximum of all subarrays of size k

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array =  {1, 2, 3, 1, 4, 5, 2, 3, 6};
        final Integer k = 3;
        LimitedTree lt = new LimitedTree(k);
        Integer cursor = 0;
        while (cursor < k)
        {
            lt.insert(array[cursor]);
            ++cursor;
        }
        System.out.println(lt.getMax());

        while (cursor < array.length)
        {
            lt.insert(array[cursor]);
            System.out.println(lt.getMax());
            ++cursor;
        }
    }
}

class LimitedTree
{
    private Node head, minNodeParent;
    final private Integer size;
    private Integer nodeCount;

    public LimitedTree(Integer size)
    {
        this.head = null;
        this.size = size;
        this.nodeCount = 0;
        this.minNodeParent = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            if (size == nodeCount && (getMinNode().getValue() > value))
            {
                return;
            }

            insert(head, value);
        }

        setMinNodeParent();
    }

    private void setMinNodeParent()
    {
        if (nodeCount == size)
        {
            if (minNodeParent == null)
            {
                head = head.getRight();

                minNodeParent = searchForMinNodeParent();
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
                    minNodeParent = searchForMinNodeParent();
                }
            }
        }
        else
        {
            minNodeParent = searchForMinNodeParent();
            ++nodeCount;
        }
    }

    private Node searchForMinNodeParent()
    {
        Node cursor = head;
        Node newMinNodeParent = null;
        while (cursor.getLeft() != null && cursor.getLeft().getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        if (cursor==head && head.getLeft() != null)
        {
            newMinNodeParent = head;
        }
        else
        {
            newMinNodeParent = null;
        }

        return newMinNodeParent;
    }

    private Node getMinNode()
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


    public Integer getMax()
    {
        Node cursor = head;
        while (cursor.getRight() != null)
        {
            cursor = cursor.getRight();
        }

        return cursor.getValue();
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}