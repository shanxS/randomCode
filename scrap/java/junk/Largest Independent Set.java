// Largest Independent Set

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 50, 110, 40, 75, 120, 60, 85};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }

        LargestIndependentSetFinder lisf = new LargestIndependentSetFinder();
        System.out.print(lisf.find(tree.getHead()));

    }
}

class LargestIndependentSetFinder
{
    Map<Integer, Integer> includeMap, excludeMap;
    public Integer find(Node head)
    {
        includeMap = new HashMap<>();
        excludeMap = new HashMap<>();

        contemplate(head, true);
        contemplate(head, false);

        return Math.max(includeMap.get(head.getValue()), excludeMap.get(head.getValue()));
    }

    private Integer contemplate(Node node, boolean includeMe)
    {
        if (node == null)
        {
            return 0;
        }


        Integer excludeMeCost = excludeMap.get(node.getValue());
        if (excludeMeCost == null)
        {
            excludeMeCost = contemplate(node.getLeft(), true) + contemplate(node.getRight(), true);
            excludeMap.put(node.getValue(), excludeMeCost);
        }

        Integer thisCost = excludeMeCost;

        if (includeMe)
        {
            Integer includeMeCost = includeMap.get(node.getValue());
            if (includeMeCost == null)
            {
                includeMeCost = 1 + contemplate(node.getLeft(), false) + contemplate(node.getRight(), false);
            }

            thisCost = Math.max(excludeMeCost, includeMeCost);
            includeMap.put(node.getValue(), thisCost);
        }

        return thisCost;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer v)
    {
        if(head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
    }

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() !=  null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    public void print()
    {
        BST.print(head);
    }

    private static void print(Node node)
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
    private Integer value;
    private Node left, right;

    public Node(Integer v)
    {
        this.value = v;
    }

    public Integer getValue()
    {
        return value;
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