// lca in  bst
// r5, q1, set33

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 15, 25, 35, 50, 5, 45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        LCAFinder finder = new LCAFinder();
        //System.out.print(finder.find(tree.getHead(), 15, 25).getValue());
        //System.out.print(finder.find(tree.getHead(), 15, 45).getValue());
        System.out.print(finder.find(tree.getHead(), 35, 45).getValue());


    }
}

class LCAFinder
{
    private Node LCA;
    private Queue<Node> queue;

    public Node find(Node head, Integer value1, Integer value2)
    {
        LCA = null;
        queue = new ArrayDeque<>();
        findAndPopulateQueue(head, value1);
        findLCA(head, value2);

        return LCA;
    }

    private void findLCA(Node node, Integer value)
    {
        if (node == null)
        {
            return;
        }

        if (queue.size() == 0 || node.getValue() != queue.peek().getValue())
        {
            return;
        }
        else
        {
            LCA = queue.remove();
        }


        if (node.getValue() > value)
        {
            findLCA(node.getLeft(), value);
        }
        else
        {
            findLCA(node.getRight(), value);
        }
    }

    private void findAndPopulateQueue(Node node, Integer value)
    {
        if (node == null)
        {
            return;
        }

        if (node.getValue() == value)
        {
            return;
        }

        queue.add(node);

        if (node.getValue() > value)
        {
            findAndPopulateQueue(node.getLeft(), value);
        }
        else
        {
            findAndPopulateQueue(node.getRight(), value);
        }
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