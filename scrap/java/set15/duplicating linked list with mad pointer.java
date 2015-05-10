// duplicating linked list with mad pointer
// written, q3, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.connectMad(5, 3);
        ll.connectMad(4, 5);
        ll.connectMad(6, 2);

        System.out.println("orignal");
        LL.print(ll.getHead());

        DuplicateLL  dll = new DuplicateLL(ll);
        System.out.println("duplicate");
        LL.print(dll.duplicate());

        System.out.println("orgnal");
        LL.print(ll.getHead());


    }
}

class DuplicateLL
{
    private Node head;

    public DuplicateLL(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node duplicate()
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
            if(copyCursor.getNext() != null)
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

    public void connectMad(Integer parentValue, Integer childValue)
    {
        Node parent = getNode(parentValue);
        Node child = getNode(childValue);

        if (parent == null || child == null)
        {
            System.out.println("cant find nodes to connect");
        }

        parent.setMad(child);
    }

    private Node getNode(Integer value)
    {
        Node cursor = head;
        while(cursor != null)
        {
            if (cursor.getValue() == value)
            {
                return  cursor;
            }

            cursor = cursor.getNext();
        }

        return null;
    }

    public static void print(Node node)
    {
        Node cursor = node;
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