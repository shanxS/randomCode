// print BST level order upside down
// r1, q2, set19

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20,10,30,15,25,35};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());
        ReverseLevelPrinter rlp = new ReverseLevelPrinter(tree);
        rlp.print();
    }
}

class ReverseLevelPrinter
{
    private Node head;

    public ReverseLevelPrinter(BST tree)
    {
        this.head = tree.getHead();
    }

    public void print()
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();
        while(cursor < size)
        {
            for (Integer i=cursor; i<size; ++i)
            {
                Node node = list.get(i);
                if (node.getRight()!= null)
                {
                    list.add(node.getRight());
                }
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
            }

            cursor = size;
            size = list.size();
        }

        cursor = list.size()-1;
        while (cursor>= 0)
        {
            System.out.print(list.get(cursor).getValue() + " ");
            --cursor;
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