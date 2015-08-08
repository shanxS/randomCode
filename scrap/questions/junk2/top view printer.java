// top view printer

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100,20,300,40,50,60};
        BST bst = new BST();
        for (Integer  i : ar)
        {
            bst.insert(i);
        }
        bst.print(bst.getHead());
        (new TopPrinter()).print(bst.getHead());
    }
}

class TopPrinter
{
    private Map<Integer, Integer> horizValue, horizDepth;
    public void print(Node head)
    {
        horizValue = new HashMap<>();
        horizDepth = new HashMap<>();
        process(head, 0, 0);

        for (Map.Entry<Integer, Integer> entry : horizValue.entrySet())
        {
            System.out.print(entry.getValue() + " ");
        }
    }

    private void process(Node node, int horiz, int depth)
    {
        if (node == null)
        {
            return;
        }

        if (horizDepth.get(horiz) == null || horizDepth.get(horiz) > depth)
        {
            horizValue.put(horiz, node.getValue());
            horizDepth.put(horiz, depth);
        }

        process(node.getLeft(), horiz-1, depth+1);
        process(node.getRight(), horiz+1, depth+1);
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
        if (head == null)
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
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    public void print(Node node)
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

    public Node(Integer v)
    {
        value = v;
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