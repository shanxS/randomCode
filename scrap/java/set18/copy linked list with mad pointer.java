// copy linked list with mad pointer
// r4, q2, set18

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.connectMad(2, 5);
        ll.connectMad(5, 4);
        ll.connectMad(6, 1);
        LL.print(ll.getHead());

        System.out.println("--------------------");
        Copier copier = new Copier(ll);
        LL.print(copier.copy());
    }
}

class Copier
{
    private Node head;

    public Copier(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node copy()
    {
        Node cursor = head;
        while (cursor != null)
        {
            Node node = new Node(cursor.getValue());
            node.setNext(cursor.getNext());

            cursor.setNext(node);
            cursor = node.getNext();
        }

        cursor = head;
        while(cursor != null)
        {
            Node copyCursor = cursor.getNext();
            if (cursor.getMad() != null)
            {
                copyCursor.setMad(cursor.getMad().getNext());
            }

            cursor = copyCursor.getNext();
        }

        cursor = head;
        Node copyHead = cursor.getNext();
        while(cursor != null)
        {
            Node copyCursor = cursor.getNext();
            cursor.setNext(copyCursor.getNext());

            if (copyCursor.getNext() != null)
            {
                copyCursor.setNext(copyCursor.getNext().getNext());
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

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
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

    public void connectMad(Integer value1, Integer value2)
    {
        Node node1 = find(value1);
        Node node2 = find(value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant fing ndoe");
            return;
        }

        node1.setMad(node2);
    }

    private Node find(Integer target)
    {
        Node cursor = head;

        while(cursor != null)
        {
            if (cursor.getValue() == target)
            {
                return cursor;
            }

            cursor = cursor.getNext();
        }

        return cursor;
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
    private Node mad;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
        this.mad = null;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }
}