// print n=ary tree in zig zag manner
// r2, q2, set22

import java.util.ArrayList;
import java.util.List;

public class  Main
{
    public static void main(String[] er)
    {
        Integer[] arry = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BT bt = new BT();
        for (Integer value : arry)
        {
            bt.insert(value);
        }

        BT.print(bt.getHead());
        bt.printZigZag();
    }
}

class BT
{
    private Node head;
    private List<Node> inserter;

    public BT()
    {
        this.head = null;
        this.inserter = new ArrayList<>();
    }

    public void printZigZag()
    {
        List<Node> list = new ArrayList<>();
        list.add(head);
        Boolean direction = true;

        while(list.size() > 0)
        {
            Integer staleSize = list.size();
            Integer reader  = (direction) ? 0 : staleSize-1;
            for (Integer writer=0; writer<staleSize; ++writer)
            {
                System.out.print(list.get(reader).getValue() + " ");

                Node node = list.get(writer);
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

                if (direction)
                {
                    ++reader;
                }
                else
                {
                    --reader;
                }
            }

            System.out.println();

            direction = !direction;
            list = list.subList(staleSize, list.size());
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

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            inserter.add(head);
        }
        else
        {
            Integer counter=0;
            Integer staleCount = inserter.size();
            while(counter<staleCount)
            {
                Node node = inserter.get(counter);
                if (node.getLeft() == null)
                {
                    node.setLeft(new Node(value));
                    inserter.add(node.getLeft());
                    break;
                }
                else if (node.getMid() == null)
                {
                    node.setMid(new Node(value));
                    inserter.add(node.getMid());
                    break;
                }
                else if (node.getRight() == null)
                {
                    node.setRight(new Node(value));
                    inserter.add(node.getRight());
                    break;
                }

                ++counter;
            }

            inserter  = inserter.subList(counter, inserter.size());
        }
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
        this.value = value;
        this.left = null;
        this.right = null;
        this.mid = null;
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