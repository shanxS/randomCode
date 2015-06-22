// Count smaller elements on right side practice

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {12, 1, 2, 3, 0, 11, 4};
        ModifiedAVL modifiedAvl = new ModifiedAVL(array.length);
        for (Integer i=array.length-1; i>=0; --i)
        {
            modifiedAvl.insert(array[i]);
        }

        ModifiedAVL.printPreorde(modifiedAvl.getHead());

        for (Integer value : modifiedAvl.getResults())
        {
            System.out.print(value + " ");
        }
    }

}

class ModifiedAVL
{
    private Node head;
    private Integer[] results;
    private Integer resultsIndex;

    public ModifiedAVL(Integer n)
    {
        head = null;
        results = new Integer[n];
        for (Integer i=0; i<n; ++i)
        {
            results[i] = 0;
        }

        resultsIndex = n-1;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value, null);
        }

        --resultsIndex;
    }

    private void insert(Node node, Integer value, Node parent)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value, node);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if (node.getValue() < value)
        {
            updateResults(node, resultsIndex);
            if (node.getRight() != null)
            {
                insert(node.getRight(), value, node);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }

        if (unbalanced(node))
        {
            balance(node, parent);
        }
    }

    private void updateResults(Node node, Integer resultsIndex)
    {
        results[resultsIndex] += node.getLeftChildren();
    }

    private void balance(Node z, Node parent)
    {
        Node newZ;

        if (z.getRightLevel() > z.getLeftLevel())
        {
            Node y = z.getRight();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                newZ = rotateLeft(z);
            }
            else
            {
                z.setRight(rotateRight(y));
                newZ = rotateLeft(z);
            }
        }
        else
        {
            Node y = z.getLeft();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                z.setLeft(rotateLeft(y));
                newZ = rotateRight(z);
            }
            else
            {
                newZ = rotateRight(z);
            }
        }

        if (parent == null)
        {
            head = newZ;
        }
        else
        {
            if (parent.getValue() > newZ.getValue())
            {
                parent.setLeft(newZ);
            }
            else
            {
                parent.setRight(newZ);
            }
        }
    }

    private Node rotateRight(Node parent)
    {
        Node child = parent.getLeft();
        Node childRightChild = child.getRight();

        child.setRight(parent);
        parent.setLeft(childRightChild);

        return child;
    }

    private Node rotateLeft(Node parent)
    {
        Node child = parent.getRight();
        Node childLeftChild = child.getLeft();

        child.setLeft(parent);
        parent.setRight(childLeftChild);

        return child;
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
    }

    public static void printPreorde(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        printPreorde(node.getLeft());
        printPreorde(node.getRight());
    }

    public Node getHead()
    {
        return head;
    }

    public Integer[] getResults()
    {
        return results;
    }
}

class Node
{
    private Node left, right;
    private Integer value, leftLevel, rightLevel;

    public Node(Integer value)
    {
        this.value = value;
        this.leftLevel = 0;
        this.rightLevel = 0;
    }

    public Integer getValue()
    {
        return value;
    }

    public Integer getLeftLevel()
    {
        updateLevels();
        return leftLevel;
    }

    public Integer getRightLevel()
    {
        updateLevels();
        return rightLevel;
    }

    public Integer getLevel()
    {
        updateLevels();
        return 1 + Math.max(leftLevel, rightLevel);
    }

    private void updateLevels()
    {
        if (left == null)
        {
            leftLevel = 0;
        }
        else
        {
            leftLevel = left.getLevel();
        }

        if (right == null)
        {
            rightLevel = 0;
        }
        else
        {
            rightLevel = right.getLevel();
        }
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

    public Integer getLeftChildren()
    {
        updateLevels();
        return 1 + leftLevel;
    }
}