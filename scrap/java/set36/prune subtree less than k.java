// prune subtree less than k
// r1, q1, set36

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 29, 35, 50, 5, 11};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        Pruner p = new Pruner();
        p.prune(tree.getHead(), 47);
        System.out.println("-------------------------------");
        BST.print(tree.getHead());

    }
}

class Pruner
{
    public void prune (Node head, Integer k)
    {
        test(head, k);
    }

    private Integer test(Node node, Integer k)
    {
        if (node == null)
        {
            return 0;
        }

        Integer leftSum = test(node.getLeft(), k) + node.getValue();
        Integer rightSum = test(node.getRight(), k) + node.getValue();

        if (leftSum > k && rightSum < k)
        {
            node.setRight(null);
            return leftSum;
        }
        else if (leftSum < k && rightSum > k)
        {
            node.setLeft(null);
            return rightSum;
        }

        return leftSum + rightSum - node.getValue();
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