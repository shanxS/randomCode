// merge sorted linked list in place
// r4, q1, set16

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array1 = new Integer[]{1,3,5,7};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }

        Integer[] array2 = new Integer[]{2,4,6,8,10,12,14};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }

        LL.print(merge(ll1, ll2));
    }

    private static Node merge(LL ll1, LL ll2)
    {
        Node cursor1 = ll1.getHead();
        Node cursor2 = ll2.getHead();
        Node head = (cursor1.getValue() > cursor2.getValue()) ? cursor2 : cursor1;
        Node cursor = head;

        while(cursor1 != null && cursor2 != null)
        {
            if (cursor1.getValue() > cursor2.getValue())
            {
                Node node = cursor2;
                cursor2 = cursor2.getNext();

                node.setNext(cursor1);
            }
            else
            {
                Node node = cursor1;
                cursor1 = cursor1.getNext();

                node.setNext(cursor2);
            }
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

    public static void print(Node node)
    {
        Node cursor = node;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
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