// find kth greatest number
// written q2, set17

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 10, 30, 25, 40, 21, 50, 45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        GreatestFinder gf = new GreatestFinder(tree);
        //System.out.print(gf.findKthGreatest(3));
        //System.out.print(gf.findKthGreatest(1));
        System.out.print(gf.findKthGreatest(6));
    }
}

class GreatestFinder
{
    private Node head;
    private Integer kth;
    private Integer globalValue;
    private Boolean start;

    public GreatestFinder(BST tree)
    {
        this.head = tree.getHead();
        this.kth = null;
        this.globalValue = null;
        this.start = false;
    }

    public Integer findKthGreatest(Integer k)
    {
        kth = k;
        find(head);
        return globalValue;
    }

    private void find(Node node)
    {
        if (node == null)
        {
            start = true;
            return;
        }

        find(node.getRight());
        if (start)
        {
            --kth;
        }
        if (start && kth == 0)
        {
            globalValue = node.getValue();
            return;
        }
        find(node.getLeft());
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
        if (head == null)
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

    public Boolean getRule()
    {
        return ruleLeftLessThan;
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
