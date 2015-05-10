// getting depth of binary tree
// r3, q1, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30, 20, 40, 25, 35, 50, 45, 60, 46, 70, 47, 48};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        DiameterCalculator diameterCalculator = new DiameterCalculator(tree);
        System.out.print(diameterCalculator.getDiameter());
    }
}

class DiameterCalculator
{
    private Node head;

    public DiameterCalculator(BST tree)
    {
        this.head = tree.getHead();
    }

    public Integer getDiameter()
    {
        Integer rightDepth = getDepth(head.getRight(), 1);
        Integer leftDepth = getDepth(head.getLeft(), 1);

        return rightDepth + leftDepth;
    }

    private Integer getDepth(Node node, Integer lastDepth)
    {
        if (node == null)
        {
            return lastDepth;
        }

        Integer rightDepth = getDepth(node.getRight(), lastDepth + 1);
        Integer leftDepth = getDepth(node.getLeft(), lastDepth + 1);

        return (rightDepth > leftDepth) ? rightDepth : leftDepth;
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.ruleLeftLessThan = true;
    }

    public void insert(Integer value)
    {
        if(head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

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

    public Node getHead()
    {
        return head;
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