// kth smallest node in BST

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10,5,20,3,6};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        System.out.println("-----------------------");

        KthSmallGetter getter = new KthSmallGetter();
        System.out.print(getter.getKthSmall(tree.getHead(), 4));
    }
}

class KthSmallGetter
{
    private Integer kthSmall;
    private Integer globalCount;

    public Integer getKthSmall(Node head, Integer k)
    {
        this.k = k;
        find(head);

        return kthSmall;
    }
    Integer k;

    private void find(Node node)
    {
        if (node == null)
        {
            return;
        }

        find (node.getLeft());

        if (k == 0)
        {
            kthSmall = node.getValue();
        }
        --k;

        find(node.getRight());
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