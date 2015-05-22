// character which occurs maximum number of times with best space and time complexity
// online q1, set26

public class Main
{
    public static void main(String[] er)
    {
        String str = "xxaaakllmmnnzzzxxx";
        ModifiedBST mBST = new ModifiedBST();
        for (Character c : str.toCharArray())
        {
            mBST.insert(c);
        }
        System.out.print(mBST.getMaxFreq());
    }
}


class ModifiedBST
{
    private Node head, maxNode;

    public ModifiedBST()
    {
        this.head = null;
        this.maxNode = null;
    }

    public Character getMaxFreq()
    {
        if (maxNode == null)
        {
            return null;
        }

        return maxNode.getValue();
    }

    public void insert(Character value)
    {
        if (head == null)
        {
            head = new Node(value);
            updateMax(head);
        }
        else
        {
            insert(head, value);
        }
    }

    private void updateMax(Node node)
    {
        if (maxNode == null)
        {
            maxNode = node;
        }
        else if (maxNode.getCount() < node.getCount())
        {
            maxNode = node;
        }
    }

    private void insert(Node node, Character value)
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
                updateMax(node.getLeft());
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
                updateMax(node.getRight());
            }
        }
        else if (node.getValue() == value)
        {
            node.incrementCount();
            updateMax(node);
        }
    }
}

class Node
{
    final private Character value;
    private Integer count;
    private Node left, right;

    public Node(Character value)
    {
        this.value = value;
        this.count = 1;
        this.left = null;
        this.right = null;
    }

    public Integer getCount()
    {
        return count;
    }

    public void incrementCount()
    {
        ++count;
    }

    public Character getValue()
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