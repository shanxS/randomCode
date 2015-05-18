// copy linked list with mad pointer
// r1, q2, set22

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[] {1,2,3,4,5,6,7,8};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.connectMad(5,2);
        ll.connectMad(2,8);
        ll.connectMad(8,1);

        Copier copier = new Copier(ll);
        LL.print(ll.getHead());
        System.out.print("---------------------");
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
            Node node = cursor.getNext();
            if(cursor.getMad() != null)
            {
                node.setMad(cursor.getMad().getNext());
            }

            cursor = node.getNext();
        }

        cursor = head;
        Node copyHead = cursor.getNext();
        while(cursor != null)
        {
            Node node = cursor.getNext();

            cursor.setNext(node.getNext());
            if (node.getNext() != null)
            {
                node.setNext(node.getNext().getNext());
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
                System.out.print(cursor.getMad().getValue() + " ");
            }

            System.out.println();
            cursor = cursor.getNext();
        }
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

    public void connectMad(Integer value1, Integer value2)
    {
        Node node1 = findNode(value1);
        Node node2 = findNode(value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant find ndoe");
        }

        node2.setMad(node1);
    }

    private Node findNode(Integer value)
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
        this.value = value;
        this.next = null;
        this.mad = null;
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