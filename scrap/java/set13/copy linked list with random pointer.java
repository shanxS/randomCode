// copy linked list with random pointer
// r1, q3, set13

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        ll.connectMad(5,4);
        ll.connectMad(4,2);
        ll.connectMad(2,3);

        LL.print(ll.getHead());
        System.out.println("---------------------------");

        Copier copier = new Copier(ll);
        copier.copy();
        System.out.println("---------------------------");
        LL.print(ll.getHead());
    }
}
class Copier
{
    private Node head;

    public Copier(LL ll)
    {
        head = ll.getHead();
    }

    public void copy()
    {
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
            Node node = cursor.getNext();
            if (cursor.getMad() != null)
            {
                node.setMad(cursor.getMad().getNext());
            }

            cursor = node.getNext();
        }

        cursor = head;
        Node copyHead = cursor.getNext();
        Node copyCursor = copyHead;
        while(cursor != null)
        {
            cursor.setNext(copyCursor.getNext());

            if (cursor.getNext() != null)
            {
                copyCursor.setNext(cursor.getNext().getNext());
            }

            cursor = cursor.getNext();
            copyCursor = copyCursor.getNext();
        }

        LL.print(copyHead);
    }
}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public void connectMad(Integer value1, Integer value2)
    {
        Node node1 = getNode(value1);
        Node node2 = getNode(value2);

        if (node1 == null || node2 == null)
        {
            System.out.println("cant find node");
            return;
        }

        node1.setMad(node2);
    }

    private Node getNode(Integer value)
    {
        Node cursor = head;

        while(cursor != null)
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

    public static void print(Node head)
    {
        Node cursor = head;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " - ");
            if (cursor.getMad() != null)
            {
                System.out.print(cursor.getMad().getValue());
            }
            System.out.println();

            cursor = cursor.getNext();
        }
    }
}

class Node
{
    private Node next;
    private Node mad;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.mad = null;
        this.next = null;
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