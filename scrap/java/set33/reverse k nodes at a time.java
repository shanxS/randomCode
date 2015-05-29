// reverse k nodes at a time
// code quetion 19
// r1, q1, set 33

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        NthReverser nr = new NthReverser();
        //LL.print(nr.reverse(ll.getHead(), 2));
        //LL.print(nr.reverse(ll.getHead(), 3));
        LL.print(nr.reverse(ll.getHead(), 4));
    }
}

class NthReverser
{
    public Node reverse(Node node, final Integer N)
    {
        if (node == null)
        {
            return null;
        }
        Node current = node;

        if (current.getNext() == null)
        {
            return current;
        }
        Node next = current.getNext();

        if (next.getNext() == null)
        {
            if (N == 2)
            {
                next.setNext(current);
                current.setNext(null);

                return next;
            }
            else
            {
                return current;
            }
        }

        Node previous = null;
        Integer count = N;

        while (count > 1 && next != null)
        {
            current.setNext(previous);
            previous = current;
            current = next;

            next = next.getNext();
            --count;
        }
        current.setNext(previous);

        node.setNext(reverse(next, N));
        return current;
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