// print node at kth distance from root
// useing recursion and BFS
// written, q2, set15

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{50,40,60,20,45,70,15,30,65};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        DistancePrinter dp = new DistancePrinter(tree);
        dp.printUsingRecursion(3);
        System.out.println();
        dp.printUsingBFS(3);

    }
}

class DistancePrinter
{
    private Node head;

    public DistancePrinter(BST tree)
    {
        this.head = tree.getHead();
    }

    public void printUsingRecursion(Integer depth)
    {
        printNodeAtDepth(head, depth);
    }

    private void printNodeAtDepth(Node node, Integer depth)
    {
        if (node == null)
        {
            return;
        }

        if (depth == 0)
        {
            System.out.print(node.getValue() + " ");
            return;
        }

        printNodeAtDepth(node.getLeft(), depth-1);
        printNodeAtDepth(node.getRight(), depth-1);
    }

    public void printUsingBFS(Integer depth)
    {
        List<Node> list = new ArrayList<>();
        list.add(head);
        Integer cursor = 0;
        Integer size = list.size();

        while(depth >= 0 && cursor < size)
        {
            if (depth == 0)
            {
                for (Integer i=cursor; i<size; ++i)
                {
                    System.out.print(list.get(i).getValue() + " ");
                }
            }
            else
            {
                for (Integer i=cursor; i<size; ++i)
                {
                    Node node = list.get(i);
                    if (node.getRight() != null)
                    {
                        list.add(node.getRight());
                    }

                    if (node.getLeft() != null)
                    {
                        list.add(node.getLeft());
                    }
                }
            }

            cursor = size;
            size = list.size();
            --depth;
        }
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
        if (node.getValue() > value == ruleLeftLessThan)
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
        else if (node.getValue() > value != ruleLeftLessThan)
        {
            if(node.getRight() != null)
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