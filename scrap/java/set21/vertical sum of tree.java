// vertical sum of tree
// r3, q2, set21

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20,10,30,15,25};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());

        Summer s = new Summer(tree);
        s.printSum();
    }
}

class Summer
{
    private Node head;
    private Map<Integer, Integer> keySum;

    public Summer(BST tree)
    {
        this.head = tree.getHead();
        this.keySum = new HashMap<>();
    }

    public void printSum()
    {
        calculateSum (0, head);
        for (Map.Entry<Integer, Integer> entry : keySum.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private void calculateSum(Integer key, Node node)
    {
        if (node == null)
        {
            return;
        }

        Integer value = keySum.get(key);
        if (value == null)
        {
            value = 0;
        }
        value += node.getValue();
        keySum.put(key, value);

        calculateSum(key + 1, node.getRight());
        calculateSum(key-1, node.getLeft());
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLess;

    public BST()
    {
        this.head = null;
        this.ruleLeftLess = true;
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
        if((node.getValue() > value) == ruleLeftLess)
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
        else if ((node.getValue() > value) != ruleLeftLess)
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
    private Node left, right;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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