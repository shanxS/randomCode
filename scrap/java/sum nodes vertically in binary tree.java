// sum nodes vertically in binary tree
// this is classic bull shit - couldnt find proper difinition of "vertical"
// the geek for geeks and SO solution seems to voilate "vertical" as we know in real world
// ttry to go beyond 3rd level and this shit hits the fan - good luck

// http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
// https://stackoverflow.com/questions/14485255/vertical-sum-in-a-given-binary-tree

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{10,5,15,1,7,12,20,8,11};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());
        System.out.println("--------------------");

        VerticalSummer verticalSummer = new VerticalSummer(tree);
        for (Map.Entry<Integer, Integer> entry : verticalSummer.getVerticalSum().entrySet())
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}

class VerticalSummer
{
    private Node head;
    private Map<Integer, Integer> map;

    public VerticalSummer(BST tree)
    {
        this.head = tree.getHead();
        this.map = new HashMap<>();
    }

    public Map<Integer, Integer> getVerticalSum()
    {
        sum(head, 0);
        return map;
    }

    private void sum(Node node, int distance)
    {
        if (node == null)
        {
            return;
        }

        Integer previousSum = map.get(distance);
        if (previousSum == null)
        {
            previousSum = 0;
            map.put(distance, previousSum);
        }
        map.put(distance, previousSum + node.getValue());

        sum(node.getLeft(), distance - 1);
        sum(node.getRight(), distance+1);
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