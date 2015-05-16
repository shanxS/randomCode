// copy linked list with mad pointer
// r1, q2, set21

public class Main
{
    public static void main(String[] ert)
    {
        Integer[] array = new Integer[]{1,2,3,4,5};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }
        ll.connectMad(3,2);
        ll.connectMad(2,5);
        ll.connectMad(5, 1);
        LL.print(ll.getHead());

        System.out.println("-----------------");
        Copier copier = new Copier();
        LL.print(copier.copy(ll));
    }
}

class Copier
{
    public Node copy(LL ll)
    {
        Node head = ll.getHead();

        Node cursor = head;
        while(cursor != null)
        {
            Node node = new Node(cursor.getValue());
            node.setNext(cursor.getNext());
            cursor.setNext(node);

            cursor = node.getNext();
        }

        cursor = head;
        while(cursor != null)
        {
            Node cursor2 = cursor.getNext();
            if (cursor.getMad() != null)
            {
                cursor2.setMad(cursor.getMad().getNext());
            }

            cursor = cursor2.getNext();
        }

        Node copyHead = head.getNext();
        cursor = head;
        while(cursor != null)
        {
            Node cursor2 = cursor.getNext();

            cursor.setNext(cursor2.getNext());
            if (cursor2.getNext() != null)
            {
                cursor2.setNext(cursor2.getNext().getNext());
            }
            else
            {
                cursor2.setNext(null);
            }

            cursor = cursor.getNext();
        }

        return copyHead;
    }
}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public static void print(Node head)
    {
        Node cursor = head;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            if (cursor.getMad() != null)
            {
                System.out.print(cursor.getMad().getValue());
            }
            System.out.println();

            cursor = cursor.getNext();
        }
    }

    public void connectMad (Integer value1, Integer value2)
    {
        Node node1 = findNode(value1);
        Node node2 = findNode(value2);

        node1.setMad(node2);
    }

    private Node findNode(Integer value)
    {
        Node cursor = head;
        while (cursor != null)
        {
            if (cursor.getValue() == value)
            {
                return cursor;
            }

            cursor = cursor.getNext();
        }

        return null;
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
            while (cursor.getNext() != null)
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
    private Node next, mad;
    private Integer value;

    public Node(Integer value)
    {
        this.next = null;
        this.mad = null;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
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