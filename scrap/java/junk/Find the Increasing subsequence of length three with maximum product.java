// Find the Increasing subsequence of length three with maximum product

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 5, 10, 8, 9};//{6, 7, 8, 1, 2, 3, 9, 10};
        AVL avl = new AVL(array.length);
        for (Integer v : array)
        {
            avl.insert(v);
        }

        Integer[] LSL = avl.getLSL();
        Integer[] LGR = new Integer[array.length];
        Integer maxSoFar = array[array.length-1];
        for (Integer i=array.length-2; i>=0; --i)
        {
            if (array[i] < maxSoFar)
            {
                LGR[i] = maxSoFar;
            }
            else
            {
                maxSoFar = array[i];
            }
        }

        Integer maxProductIndex = 1;
        for (Integer i=1; i<array.length-1; ++i)
        {
            if (LSL[i] != null && LGR[i] != null)
            {
                Integer thisProd = LGR[i] * LSL[i] * array[i];
                if (thisProd > (LGR[maxProductIndex] * array[maxProductIndex] * LSL[maxProductIndex]))
                {
                    maxProductIndex = i;
                }
            }
        }

        System.out.print((LGR[maxProductIndex] + " " + array[maxProductIndex] + " " + LSL[maxProductIndex]));
    }
}

class AVL
{
    private Node head;
    private Integer[] LSL;
    private Integer currentIndex;

    public AVL(Integer n)
    {
        head = null;
        LSL = new Integer[n];
        currentIndex = 0;
    }

    public Integer[] getLSL()
    {
        return LSL;
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

        ++currentIndex;
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
            if (node.getRight() != null)
            {
                LSL[currentIndex] = node.getValue();
                insert(node.getRight(), value, node);
            }
            else
            {
                LSL[currentIndex] = node.getValue();
                node.setRight(new Node(value));
            }
        }

        if (unbalanced(node))
        {
            balance(node, parent);
        }
    }

    private void balance(Node z, Node parent)
    {
        Node newZ = null;
        if (z.getRightLevel() > z.getLeftLevel())
        {
            Node y = z.getRight();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                newZ = leftRotate(z);
            }
            else
            {
                z.setRight(rightRotate(y));
                newZ = leftRotate(z);
            }
        }
        else
        {
            Node y = z.getLeft();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                z.setLeft(leftRotate(y));
                newZ = rightRotate(z);
            }
            else
            {
                newZ = rightRotate(y);
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

    private Node leftRotate(Node parent)
    {
        Node child = parent.getRight();
        Node childLeftChild = child.getLeft();

        parent.setRight(childLeftChild);
        child.setLeft(parent);

        return child;
    }

    private Node rightRotate(Node parent)
    {
        Node child = parent.getLeft();
        Node childRightChild = child.getRight();

        parent.setLeft(childRightChild);
        child.setRight(parent);

        return child;
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
    }

    public Node getHead()
    {
        return head;
    }

    public static void printPre(Node node)
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
        System.out.print(",");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        printPre(node.getLeft());
        printPre(node.getRight());
    }
}

class Node
{
    private Node left, right;
    private Integer value, leftLevel, rightLevel;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Integer getLevel()
    {
        updateLevel();
        return 1 + Math.max(leftLevel, rightLevel);
    }

    public Integer getRightLevel()
    {
        updateLevel();
        return 1 + rightLevel;
    }

    public Integer getLeftLevel()
    {
        updateLevel();
        return 1 + leftLevel;
    }

    private void updateLevel()
    {
        leftLevel = 0;
        if (left != null)
        {
            leftLevel = left.getLevel();
        }

        rightLevel = 0;
        if (right != null)
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

    public Integer getValue()
    {
        return value;
    }
}