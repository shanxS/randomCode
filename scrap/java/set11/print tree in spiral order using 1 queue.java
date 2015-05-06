// print tree in spiral order using 1 queue
// tech1, q1, set11


import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 15, 30, 10, 18, 25, 40, 2, 19};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());
        SpiralPrinter sp = new SpiralPrinter(tree);
        sp.print();

    }
}

class SpiralPrinter
{
    private final Node head;
    private List<Node> list;
    private Integer depth, cursor;

    public SpiralPrinter(BST tree)
    {
        this.head = tree.getHead();
        this.list = new ArrayList<>();
    }

    public void print()
    {
        Boolean flag = false;
        list.add(head);
        cursor = 0;
        depth = list.size();

        while(cursor != depth)
        {
            for (Integer  i=depth-1; i>=cursor; --i)
            {
                Node node = list.get(i);

                if (flag)
                {
                    if(node.getLeft() != null)
                    {
                        list.add(node.getLeft());
                    }
                    if (node.getRight() != null)
                    {
                        list.add(node.getRight());
                    }
                }
                else
                {
                    if (node.getRight() != null)
                    {
                        list.add(node.getRight());
                    }
                    if(node.getLeft() != null)
                    {
                        list.add(node.getLeft());
                    }
                }
            }

            flag = !flag;
            cursor = depth;
            depth = list.size();
        }

        list.stream().forEach(x -> System.out.print(x.getValue() + " "));
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