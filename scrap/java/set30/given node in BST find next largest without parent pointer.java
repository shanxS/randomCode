// given node in BST find next largest without parent pointer
// r1, q2, set30

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20, 10, 40, 5, 30, 50, 35, 45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        NextLargestFinder nlf = new NextLargestFinder();
        //System.out.print(nlf.find(tree, 35).getValue());
        //System.out.print(nlf.find(tree, 45).getValue());
        //System.out.print(nlf.find(tree, 20).getValue());
        //System.out.print(nlf.find(tree, 50).getValue());
        System.out.print(nlf.find(tree, 60).getValue());

    }
}

class NextLargestFinder
{
    Integer target;
    Node nextGreatest;

    public Node find(BST tree, Integer target)
    {
        Node head = tree.getHead();
        this.target = target;
        nextGreatest = null;
        find(head);

        return nextGreatest;
    }

    private Boolean find(Node node)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getValue() == target)
        {
            if (node.getRight() != null)
            {
                Node cursor = node.getRight();
                while (cursor.getLeft() != null)
                {
                    cursor = cursor.getLeft();
                }
                nextGreatest = cursor;
            }
            return true;
        }
        else if (node.getValue() > target)
        {
            Boolean leftStatus = find(node.getLeft());
            if (leftStatus)
            {
                if (nextGreatest == null)
                {
                    nextGreatest = node;
                }
                return leftStatus;
            }
        }
        else
        {
            Boolean rigghtStatus = find(node.getRight());
            if (rigghtStatus)
            {
                return rigghtStatus;
            }
        }

        return null;
    }
}

class BST
{
    private Node head;

    public static void print(Node node)
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

        print(node.getLeft());
        print(node.getRight());
    }

    public BST()
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
            insert(head, value);
        }
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
                Node leftNode = new Node(value);
                node.setLeft(leftNode);
                leftNode.setParent(node);
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getValue() < value)
            {
                if (node.getRight() != null)
                {
                    insert(node.getRight(), value);
                }
                else
                {
                    Node rightNode = new Node(value);
                    node.setRight(rightNode);
                    rightNode.setParent(node);
                }
            }
        }
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right, parent;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
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

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
}