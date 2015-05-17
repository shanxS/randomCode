// print tree in zigzag manner
// r2, q2, set21

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{50,30,60,20,40,70,35,45,65};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        ZigZagPrinter zzp = new ZigZagPrinter(tree);
        zzp.print();
    }
}

class ZigZagPrinter
{
    private Node head;
    private List<Node> list;

    public ZigZagPrinter(BST tree)
    {
        this.head = tree.getHead();
        this.list = new ArrayList<>();
    }

    public void print()
    {
        list.add(head);
        Integer cursor = 0;
        Integer size = list.size();
        Boolean direction = true;

        while(cursor < size)
        {
            Integer printCursor = (direction) ? cursor : size-1;

            for (Integer addCursor=cursor; addCursor<size; ++addCursor)
            {
                Node printNode = list.get(printCursor);
                System.out.print(printNode.getValue() + " ");

                Node addNode = list.get(addCursor);
                if (addNode.getLeft() != null)
                {
                    list.add(addNode.getLeft());
                }
                if (addNode.getRight() != null)
                {
                    list.add(addNode.getRight());
                }

                if (direction)
                {
                    ++printCursor;
                }
                else
                {
                    --printCursor;
                }
            }

            System.out.println();
            direction = !direction;
            cursor = size;
            size = list.size();
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
        else
        {
            if ((node.getValue() > value) != ruleLeftLessThan)
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
