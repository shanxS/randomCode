// AVL

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
        this.head = null;
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

    private Integer insert(Node node, Integer value, Node parent)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                Integer newLeftLevel = insert(node.getLeft(), value, node);
                node.setLeftLevel(newLeftLevel);

                if (unbalanced(node))
                {
                    balance(node, parent);
                }

                return node.getLevel();
            }
            else
            {
                node.setLeft(new Node(value));
                node.setLeftLevel(1);
                return node.getLevel();
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                Integer newRightLevel = insert(node.getRight(), value, node);
                node.setRightLevel(newRightLevel);

                if (unbalanced(node))
                {
                    balance(node, parent);
                }

                return node.getRightLevel();
            }
            else
            {
                node.setRight(new Node(value));
                node.setRightLevel(1);
                return node.getLevel();
            }
        }
        else
        {
            return node.getLevel();
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
                Node newRightNode = leftRotate(y);
                z.setRight(newRightNode);

                newZ = rightRotate(z);
            }
            else
            {
                newZ = rightRotate(z);
            }
        }

        if (parent == null)
        {
            head = newZ;
        }
        else if (parent.getValue() > newZ.getValue())
        {
            parent.setLeft(newZ);
        }
        else
        {
            parent.setRight(newZ);
        }
    }

    private Node rightRotate(Node parent)
    {
        Node child = parent.getLeft();
        Node childRightChild = child.getRight();

        parent.setLeft(childRightChild);
        child.setRight(parent);

        return child;
    }

    private Node leftRotate(Node parent)
    {
        Node child = parent.getRight();
        Node childLeftChild = child.getLeft();

        parent.setRight(childLeftChild);
        child.setLeft(parent);

        return child;
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
    }

    public void printPreorder()
    {
        print(head);
    }

    private void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
        print(node.getLeft());
        print(node.getRight());
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

    public void setLeftLevel(Integer i)
    {
        leftLevel = i;
    }

    public void setRightLevel(Integer i)
    {
        rightLevel = i;
    }

    public Integer getRightLevel()
    {
        return rightLevel;
    }

    public Integer getLevel()
    {
        return 1 + Math.max(rightLevel, leftLevel);
    }

    public Integer getLeftLevel()
    {
        return leftLevel;
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