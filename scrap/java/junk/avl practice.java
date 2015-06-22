// avl practice

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10,20,30,40,50,25};
        AVL avl = new AVL();
        for (Integer value : array)
        {
            avl.insert(value);
        }

        avl.printPreorder();
    }
}

class AVL
{
    private Node head;

    public AVL()
    {
        head = null;
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
            if (head.getValue() > newZ.getValue())
            {
                head.setLeft(newZ);
            }
            else
            {
                head.setRight(newZ);
            }
        }
    }

    private Node rotateLeft(Node parent)
    {
        Node child = parent.getRight();
        Node childLeftChild = child.getLeft();

        child.setLeft(parent);
        parent.setRight(childLeftChild);

        return child;
    }

    private Node rotateRight(Node parent)
    {
        Node child = parent.getLeft();
        Node childRightChild = child.getRight();

        child.setRight(parent);
        parent.setLeft(childRightChild);

        return child;
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
    }

    public void printPreorder()
    {
        printPreorder(head);
    }

    private void printPreorder(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
        printPreorder(node.getLeft());
        printPreorder(node.getRight());
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
        updateLevel();
        return leftLevel;
    }

    public Integer getRightLevel()
    {
        updateLevel();
        return rightLevel;
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

    public Integer getLevel()
    {
        updateLevel();
        return 1 + Math.max(leftLevel, rightLevel);
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