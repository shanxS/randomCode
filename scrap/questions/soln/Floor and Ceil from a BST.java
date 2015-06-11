// Floor and Ceil from a BST
// code question: 75

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {30, 20, 40, 25, 35, 21, 38};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        System.out.println("----------------------");

        LimitGetter lg = new LimitGetter();
//        lg.compute(tree.getHead(), 22);
//        lg.compute(tree.getHead(), 39);
//        lg.compute(tree.getHead(), 36);
//        lg.compute(tree.getHead(), 31);
        lg.compute(tree.getHead(), 29);

        System.out.print(lg.ceil + " " + lg.floor);
    }
}

class LimitGetter
{
    public Integer ceil = null;
    public Integer floor = null;

    public void compute(Node node, Integer value)
    {
        findLimit (node, value);
    }

    private Boolean findLimit(Node node, Integer value)
    {
        if (node == null)
        {
            return false;
        }

        if (findLimit(node.getLeft(), value))
        {
            return true;
        }
        else
        {
            if (node.getValue() > value)
            {
                ceil = node.getValue();
                return true;
            }
            else
            {
                floor = node.getValue();
                return findLimit(node.getRight(), value);
            }
        }
    }
}

class BST
{
    private Node head;

    public BST()
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

    public Node getHead()
    {
        return head;
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