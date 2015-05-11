// given BST of memory blocks get which block should be used for given size
// r1, q2, set16

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 10, 30, 5, 15, 25, 40};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        Finder finder = new Finder(tree);
        //System.out.print("value: " + finder.find(19));
        //System.out.print("value: " + finder.find(1));
        System.out.print("value: " + finder.find(59));
    }
}

class Finder
{

    private Node head;
    private Boolean ruleLeftGreaterThan;

    public Finder(BST tree)
    {
        this.head = tree.getHead();
        this.ruleLeftGreaterThan = tree.getRule();
    }

    public Integer find(Integer target)
    {
        return find(target, head);
    }

    private Integer find(Integer target, Node node)
    {
        if (node == null)
        {
            return null;
        }

        if((node.getValue() > target) == ruleLeftGreaterThan)
        {
            Integer childValue = find(target, node.getLeft());
            if (childValue == null)
            {
                return node.getValue();
            } else
            {
                return childValue;
            }
        }
        else if ((node.getValue() > target) != ruleLeftGreaterThan)
        {
            return find(target, node.getRight());
        }
        else
        {
            return node.getValue();
        }

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