// kth largets in bst
// r1, q3,  set29

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20, 10, 40, 5, 25, 50, 7, 45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        LargestFetcher lf = new LargestFetcher();
        System.out.println(lf.getKthLargest(tree.getHead(), 0).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 1).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 2).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 3).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 4).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 5).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 6).getValue());
        System.out.println(lf.getKthLargest(tree.getHead(), 7).getValue());
//        System.out.println(lf.getKthLargest(tree.getHead(), 8).getValue());
//        System.out.println(lf.getKthLargest(tree.getHead(), 9).getValue());

    }
}

class LargestFetcher
{
    private Node targetNode;
    private Integer k;
    private Integer count;

    public Node getKthLargest(Node head, Integer k)
    {
        this.k = k;
        this.count = 0;
        this.targetNode = null;
        getKthLargest(head);
        return targetNode;
    }

    private void getKthLargest(Node node)
    {
        if (node == null)
        {
            return;
        }


        getKthLargest(node.getRight());
        if (k == 0)
        {
            targetNode = node;
        }
        --k;
        if (targetNode != null)
        {
            return;
        }
        getKthLargest(node.getLeft());

    }
}

class BST
{
    private Node head;

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