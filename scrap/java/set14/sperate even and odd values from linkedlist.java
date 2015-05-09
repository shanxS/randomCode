// sperate even and odd values from linkedlist
// r4, q2, set14

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        Separator separator = new Separator(ll);
        separator.separate();
        LL.print(separator.getEvenHead());
        LL.print(separator.getOddHead());

    }
}

class Separator
{
    private Node evenHead, oddHead, head;

    public Separator(LL ll)
    {
        this.head = ll.getHead();
        this.evenHead = null;
        this.oddHead = null;
    }

    public void separate()
    {
        Node lagger = null;
        Node cursor = head;
        Node evenCursor = null;
        Node oddCursor = null;

        while(cursor != null)
        {
            if (lagger != null)
            {
                lagger.setNext(null);
            }

            if (cursor.getValue() % 2 == 0)
            {
                if (evenHead == null)
                {
                    evenHead = cursor;
                    evenCursor = evenHead;
                }
                else
                {
                    evenCursor.setNext(cursor);
                    evenCursor = evenCursor.getNext();
                }
            }
            else
            {
                if (oddHead == null)
                {
                    oddHead = cursor;
                    oddCursor = oddHead;
                }
                else
                {
                    oddCursor.setNext(cursor);
                    oddCursor = oddCursor.getNext();
                }
            }

            lagger = cursor;
            cursor = cursor.getNext();
        }
    }

    public Node getEvenHead()
    {
        return evenHead;
    }

    public Node getOddHead()
    {
        return oddHead;
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