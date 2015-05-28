// print binary tree in zig zag
// r5, q9, set30 

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20,10,30,8,25,40,5,9};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        ZigZagPrinter zp = new ZigZagPrinter();
        zp.print(tree);
    }
}

class ZigZagPrinter
{
    public void print(BST tree)
    {
        Node head = tree.getHead();
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();

        Boolean printDirection = false;
        while(cursor < size)
        {
            Integer printCursor = (printDirection) ? cursor : size-1;

            for (Integer addCursor = cursor; addCursor < size; ++addCursor)
            {
                Node node = list.get(addCursor);
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }

                System.out.print(list.get(printCursor).getValue() + " ");

                if (printDirection)
                {
                    ++printCursor;
                }
                else
                {
                    --printCursor;
                }
            }
            System.out.println();
            printDirection = !printDirection;

            cursor = size;
            size = list.size();
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