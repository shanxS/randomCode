// axis summer of BST

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,35,50};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        AxisSummer as = new AxisSummer(tree);
        as.printSum();
    }
}

class AxisSummer
{
    private Node head;
    private Map<Integer, Integer> valueSum;
    private Integer left, right;

    public AxisSummer(BST tree)
    {
        this.head = tree.getHead();
        this.valueSum = new HashMap<>();
        this.left = -1;
        this.right = 1;
    }

    public void printSum()
    {
        valueSum.put(0, head.getValue());
        sum(head.getLeft(), left);
        sum(head.getRight(), right);

        for (Map.Entry<Integer, Integer> entry : valueSum.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    private void sum(Node node, Integer direction)
    {
        if (node == null)
        {
            return;
        }

        Integer directionValue = valueSum.get(direction);
        if (directionValue == null)
        {
            directionValue = 0;
        }
        directionValue += node.getValue();
        valueSum.put(direction, directionValue);

        sum(node.getLeft(), direction + left);
        sum(node.getRight(), direction + right);
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