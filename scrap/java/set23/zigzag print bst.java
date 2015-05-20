// zigzag print bst
// r3, q2, set23

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{20,10,30,5,15,40,1,35};
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

    public ZigZagPrinter(BST tree)
    {
        this.head = tree.getHead();
    }

    public void print()
    {
        List<Node> list = new ArrayList<>();
        list.add(head);
        Boolean direction = true;

        while(list.size() > 0)
        {
            Integer printCursor = (direction) ? 0 : list.size()-1;
            Integer staleSize = list.size();
            for (Integer writeCursor=0; writeCursor < staleSize; ++writeCursor)
            {
                Node node = list.get(writeCursor);
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }

                System.out.print(list.get(printCursor).getValue() + " ");
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
            list = list.subList(staleSize, list.size());
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

    private void insert(Node node, Integer value)
    {
        if ((node.getValue()>value) == ruleLeftLessThan)
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