// Count smaller elements on right side
// code question: 112

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5};//{5, 4, 3, 2, 1};//{12, 1, 2, 3, 0, 11, 4};
        ModifiedAVL modifiedAvl = new ModifiedAVL(array.length);
        for (Integer i = array.length - 1; i >= 0; --i)
        {
            modifiedAvl.insert(array[i]);
        }

        ModifiedAVL.printPreorder(modifiedAvl.getHead());

        for (Integer v : modifiedAvl.getResults())
        {
            System.out.print(v + " ");
        }
    }
}

class ModifiedAVL
{
    private Node head;
    private Integer[] results;
    private Integer resultIndex;

    public ModifiedAVL(Integer n)
    {
        head = null;
        results = new Integer[n];
        for (Integer i=0; i<n; ++i)
        {
            results[i] = 0;
        }

        resultIndex = n-1;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            updateResult(null, resultIndex);
        }
        else
        {
            insert(head, value, null);
        }

        --resultIndex;
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
                updateResult(null, resultIndex);
            }
        }
        else if (node.getValue() < value)
        {
            updateResult(node, resultIndex);

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

    private void updateResult(Node node, Integer resultIndex)
    {
        if (node == null)
        {
            return;
        }

        results[resultIndex] += node.getChildrenCount();
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
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

    private Node rotateLeft(Node parent)
    {
        Node child =  parent.getRight();
        Node childLeftChlid = child.getLeft();

        parent.setRight(childLeftChlid);
        child.setLeft(parent);

        return child;
    }

    private Node rotateRight(Node parent)
    {
        Node child = parent.getLeft();
        Node childRighChild = child.getRight();

        parent.setLeft(childRighChild);
        child.setRight(parent);

        return child;
    }

    public Node getHead()
    {
        return head;
    }

    public static void printPreorder(Node node)
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

        printPreorder(node.getLeft());
        printPreorder(node.getRight());
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

    public Node (Integer value)
    {
        this.value = value;
        this.leftLevel = 0;
        this.rightLevel = 0;
    }

    public Integer getValue()
    {
        return value;
    }

    public Integer getRightLevel()
    {
        updateLevel();
        return rightLevel;
    }

    public Integer getLeftLevel()
    {
        updateLevel();
        return leftLevel;
    }

    public Integer getLevel()
    {
        updateLevel();
        return 1 + Math.max(leftLevel, rightLevel);
    }

    public Integer getChildrenCount()
    {
        updateLevel();
        return 1 + leftLevel;
    }

    private void updateLevel()
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
}