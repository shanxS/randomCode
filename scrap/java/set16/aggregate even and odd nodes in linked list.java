// aggregate even and odd nodes in linked list
// r1, q1, aet 16

public class Main
{
    public static void main(String[] args)
    {
        //Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        //Integer[] array = new Integer[]{1,2,3,4,5,6};
        Integer[] array = new Integer[]{2,3,4,5,6};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LL.print(ll.getHead());
        Aggregator aggregator = new Aggregator(ll);
        LL.print(aggregator.aggregate());
    }
}

class Aggregator
{
    private Node head;

    public Aggregator(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node aggregate()
    {
        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.setNext(head);
        Node cursor = dummy;
        Integer move = getLength();
        Node floatingEnd = getEnd();

        while(move > 0)
        {
            Node next = cursor.getNext();
            if (next.getValue() % 2 == 0)
            {
                cursor.setNext(next.getNext());
                next.setNext(null);
                floatingEnd.setNext(next);
                floatingEnd = next;

                --move;
            }

            cursor = cursor.getNext();
            --move;
        }

        return dummy.getNext();
    }

    private Integer getLength()
    {
        Integer size = 0;
        Node cursor = head;
        while (cursor != null)
        {
            cursor = cursor.getNext();
            ++size;
        }

        return size;
    }

    private Node getEnd()
    {
        Node cursor = head;

        while (cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }

        return cursor;
    }
}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public static void print(Node node)
    {
        Node cursor = node;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
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
}