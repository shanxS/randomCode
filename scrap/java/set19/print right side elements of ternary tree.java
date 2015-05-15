// print right side elements of ternary tree
// f2f 4, q1


import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        TernaryTree tt = new TernaryTree();
        for (Integer value : array)
        {
            tt.insert(value);
        }

        TernaryTree.print(tt.getHead());
        RightPrinter rp = new RightPrinter(tt);
        rp.print();
    }
}

class RightPrinter
{
    private Node head;

    public RightPrinter(TernaryTree tt)
    {
        this.head = tt.getHead();
    }

    public void  print()
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();
        while(cursor < size)
        {
            System.out.println(list.get(size - 1).getValue());

            for (Integer i=cursor; i<size; ++i)
            {
                Node node = list.get(i);
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getMid() != null)
                {
                    list.add(node.getMid());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }
            }

            list = list.subList(size, list.size());
            cursor = size;
            size = list.size();
        }

        System.out.println(list.get(size-1).getValue());
    }
}

class TernaryTree
{
    private Node head;
    private List<Node> list;

    public TernaryTree()
    {
        this.head = null;
        this.list = new ArrayList<>();
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            list.add(head);
        }
        else
        {
            Node newNode = new Node(value);

            Node topNode = list.get(0);
            if (topNode.getLeft() == null)
            {
                topNode.setLeft(newNode);
            }
            else if (topNode.getMid() == null)
            {
                topNode.setMid(newNode);
            }
            else if (topNode.getRight() == null)
            {
                topNode.setRight(newNode);
                list.remove(topNode);
            }

            list.add(newNode);
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
        if (node.getMid() != null)
        {
            System.out.print(node.getMid().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getMid());
        print(node.getRight());

    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, mid, right;
    private Integer value;

    public Node(Integer value)
    {
        this.left = null;
        this.mid = null;
        this.right = null;
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

    public Node getMid()
    {
        return mid;
    }

    public void setMid(Node mid)
    {
        this.mid = mid;
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