// Construct a Maximum Sum Linked List out of two Sorted Linked Lists having some Common nodes

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,3,30,90,120,240,511};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }

        Integer[] array2 = {0,3,12,32,90,125,240,249};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }

        MaxSumFinder mf = new MaxSumFinder();
        LL.print(mf.mergeMaxSum(ll1.getHead(), ll2.getHead()));
    }
}


class MaxSumFinder
{
    public Node mergeMaxSum(Node node1, Node node2)
    {
        Node cursor1 = node1;
        Node cursor2 = node2;

        Integer thisSum1 = 0;
        Integer thisSum2 = 0;

        while (cursor1 != null && cursor2 != null)
        {
            if (cursor1.getValue() < cursor2.getValue())
            {
                thisSum1 += cursor1.getValue();
                cursor1 = cursor1.getNext();
            }
            else if (cursor1.getValue() > cursor2.getValue())
            {
                thisSum2 += cursor2.getValue();
                cursor2 = cursor2.getNext();
            }
            else
            {
                break;
            }
        }

        merge(cursor1, cursor2);

        return (thisSum1+sum1 > thisSum2+sum2) ? node1 : node2;
    }

    private Integer sum1, sum2;
    private void merge(Node node1, Node node2)
    {
        if (node1 == null || node2 == null)
        {
            sum1 = 0;
            while(node1 != null)
            {
                sum1 += node1.getValue();
                node1 = node1.getNext();
            }

            sum2 = 0;
            while(node2 != null)
            {
                sum2 += node2.getValue();
                node2 = node2.getNext();
            }

            return;
        }

        Node cursor1 = node1.getNext();
        Node cursor2 = node2.getNext();
        Integer thisSum1 = node1.getValue();
        Integer thisSum2 = node2.getValue();
        while(cursor1 != null && cursor2 != null)
        {
            if (cursor1.getValue() < cursor2.getValue())
            {
                thisSum1 += cursor1.getValue();
                cursor1 = cursor1.getNext();
            }
            else if (cursor1.getValue() > cursor2.getValue())
            {
                thisSum2 += cursor2.getValue();
                cursor2 = cursor2.getNext();
            }
            else
            {
                break;
            }
        }

        merge(cursor1, cursor2);
        if (sum1+thisSum1 > sum2+thisSum2)
        {
            node2.setNext(node1.getNext());
            sum1 = sum1+thisSum1;
            sum2 = sum1;
        }
        else
        {
            node1.setNext(node2.getNext());
            sum2 = sum2+thisSum2;
            sum1 = sum2;
        }
    }
}

class LL
{
    private Node head;

    public LL()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            Node cursor = head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }
    }

    public void print()
    {
        LL.print(head);
    }

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        Node cursor = node;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node (Integer value)
    {
        this.value = value;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node next)
    {
        this.next = next;
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