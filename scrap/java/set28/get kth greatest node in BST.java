// get kth greatest node in BST
// r1, q4, set28

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{50, 40, 70, 30, 45, 55, 80, 75};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        GetKthLargest kth = new GetKthLargest();
        //System.out.print(kth.get(tree.getHead(), 1).getValue());
        System.out.print("-------- " + kth.get(tree.getHead(), 7).getValue());
    }
}

class GetKthLargest
{
    private Integer k;
    public Node get(Node head, Integer k)
    {
       this.k = k;
        return get(head);
    }

    public Node get(Node node)
    {
        if (node == null)
        {
            return null;
        }

        Node rightNode = get(node.getRight(), k);
        if (rightNode != null)
        {
            return rightNode;
        }
        else if (k == 0)
        {
            return node;
        }

        return get(node.getLeft(), k-1);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
