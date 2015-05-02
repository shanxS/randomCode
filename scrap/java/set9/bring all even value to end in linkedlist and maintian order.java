// bring all even value to end in linkedlist and maintian order
// r1, q1, set9

public class Main
{
    public static void main(String[] args)
    {
        LL ll = new LL();
        ll.insert(12);
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);

        Sorter sorter = new Sorter(ll);
        sorter.sort();

    }
}

class Sorter
{
    private Node head;

    public Sorter(LL ll)
    {
        this.head = ll.getHead();

        LL.print(ll.getHead());
    }

    public void sort()
    {
        Node cursor = head;
        while(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }
        final Node end = cursor;
        Node floatingEnd = cursor;

        cursor = head;
        final Node dummyNode = new Node(-1);
        dummyNode.setNext(head);
        Node previous = dummyNode;

        while(cursor.getValue() != end.getValue())
        {
            if (cursor.getValue() % 2 == 0)
            {
                previous.setNext(cursor.getNext());
                cursor.setNext(null);
                floatingEnd.setNext(cursor);

                floatingEnd = cursor;
                cursor = previous.getNext();
            }
            else
            {
                previous = cursor;
                cursor = cursor.getNext();
            }
        }
        if (cursor.getValue() % 2 == 0)
        {
            previous.setNext(cursor.getNext());
            cursor.setNext(null);
            floatingEnd.setNext(cursor);
        }


        LL.print(dummyNode.getNext());
    }
}

class LL
{
    private Node head;

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