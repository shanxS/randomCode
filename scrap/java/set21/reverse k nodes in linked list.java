// reverse k nodes in linked list
// phone r3, q2, set21

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        Reverser r = new Reverser();
        LL.print(r.revrse(ll.getHead(), 5));
    }
}

class Reverser
{
    public Node revrse(Node node, final Integer count)
    {
        if (node == null)
        {
            return  null;
        }
        Node cursor = node;

        if (cursor.getNext() == null)
        {
            return cursor;
        }
        Node next = cursor.getNext();

        if (next.getNext() == null)
        {
            if (count == 2)
            {
                next.setNext(cursor);
                cursor.setNext(null);
                return next;
            }
            else
            {
                return cursor;
            }
        }
        Node nextNext = next.getNext();

        Integer counter = count;
        while(counter > 0 && cursor != null)
        {
            cursor = cursor.getNext();
            --counter;
        }
        if (counter != 0)
        {
            return node;
        }
        cursor = node;

        counter = count;
        while(counter > 1 && next != null)
        {
            next.setNext(cursor);
            cursor = next;
            next = nextNext;

            if (nextNext != null)
            {
                nextNext = nextNext.getNext();
            }
            --counter;
        }

        if (next == null)
        {
            node.setNext(null);
        }
        else
        {
            node.setNext(revrse(next, count));
        }

        return cursor;
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
            Node cursor  = head;
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

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node(Integer value)
    {
        this.next = null;
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}