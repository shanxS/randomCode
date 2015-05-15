// BT into SUM BT(each node values = sum of left and right node)
// f2f 2, q1

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30, 20, 40, 35, 50};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        Summer summer = new Summer(tree);
        System.out.println("----------------");
        BST.print(summer.sun());

    }
}

class Summer
{
    private Node head;

    public Summer(BST tree)
    {
        this.head = tree.getHead();
    }

    public Node sun()
    {
        sum(head);

        return head;
    }

    private void sum(Node node)
    {
        if (node == null)
        {
            return;
        }

        sum(node.getLeft());
        sum(node.getRight());

        Integer thisValue = 0;
        if (node.getRight() != null)
        {
            thisValue += node.getRight().getValue();
        }

        if (node.getLeft() != null)
        {
            thisValue += node.getLeft().getValue();
        }

        if (thisValue != 0)
        {
            node.setValue(thisValue);
        }
    }
}

class BST
{
    private Node head;
    private Boolean rultLeftGreaterThan;

    public BST()
    {
        this.head = null;
        this.rultLeftGreaterThan = true;
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

        print(node.getRight());
        print(node.getLeft());
    }

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == rultLeftGreaterThan)
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
        else if ((node.getValue() > value) != rultLeftGreaterThan)
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