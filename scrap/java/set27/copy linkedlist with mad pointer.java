// copy linkedlist with mad pointer
// r4, q2, set27

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.connectMad(4,3);
        ll.connectMad(3,6);
        ll.connectMad(7,1);

        Copier copier = new Copier();
        LL.print(ll.getHead());
        System.out.println("----------------");
        LL.print(copier.copy(ll));

    }
}

class Copier
{
    public Node copy(LL ll)
    {
        final Node head = ll.getHead();

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
            if (cursor.getMad() != null)
            {
                Node copyNode = cursor.getNext();
                copyNode.setMad(cursor.getMad().getNext());
            }

            cursor = cursor.getNext().getNext();
        }

        final Node copyHead = head.getNext();
        cursor = head;
        while(cursor != null)
        {
            Node copyCursor = cursor.getNext();

            cursor.setNext(copyCursor.getNext());
            if (copyCursor.getNext() != null)
            {
                copyCursor.setNext(copyCursor.getNext().getNext());
            }

            cursor = copyCursor.getNext();
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

    public void connectMad(Integer value1, Integer value2)
    {
        Node node1 = findNode(value1);
        Node node2 = findNode(value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant find nodes");
        }

        node1.setMad(node2);
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
    private Node next, mad;

    public Node(Integer value)
    {
        this.value = value;
        this.mad = null;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }
}