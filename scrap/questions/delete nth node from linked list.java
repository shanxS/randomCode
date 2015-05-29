// delete nth node from linked list
// written, r1, q5, set32

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }
        LL.print(ll.getHead());
        NthDeleter nd = new NthDeleter();
        //LL.print(nd.delete(ll.getHead(), 3));
        //LL.print(nd.delete(ll.getHead(), 0));
        LL.print(nd.delete(ll.getHead(), 2));

    }
}

class NthDeleter
{
    public Node delete (Node head, Integer n)
    {
        Node parent = null;
        Node cursor = head;

        while (n>0 && cursor != null)
        {
            parent = cursor;
            cursor = cursor.getNext();
            --n;
        }

        if (cursor == null)
        {
            return head;
        }

        if (parent == null)
        {
            head = head.getNext();
        }
        else
        {
            Node child = cursor.getNext();
            parent.setNext(child);
        }

        return head;
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
            Node cusor = head;
            while(cusor.getNext() != null)
            {
                cusor = cusor.getNext();
            }

            cusor.setNext(new Node(value));
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