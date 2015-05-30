// reversal of N nodes in linked list

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

        LL.print(ll.getHead());
        PartialReverser pr = new PartialReverser();
        //LL.print(pr.reverseN(ll.getHead(), 4));
        //LL.print(pr.reverseN(ll.getHead(), 3));
        //LL.print(pr.reverseN(ll.getHead(), 2));
        //LL.print(pr.reverseN(ll.getHead(), 7));/
        //LL.print(pr.reverseN(ll.getHead(), 8));
        LL.print(pr.reverseN(ll.getHead(), 9));

    }
}

class PartialReverser
{
    public Node reverseN(Node node, final Integer N)
    {
        if (N < 2 || node == null)
        {
            return node;
        }
        Node current = node;

        if (current.getNext() == null)
        {
            return current;
        }
        Node next = current.getNext();

        Node previous = null;
        Integer count = N;

        while(next != null && count > 1)
        {
            current.setNext(previous);
            previous = current;
            current = next;

            next = next.getNext();
            --count;
        }
        current.setNext(previous);

        node.setNext(reverseN(next, N));
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

    public static void print(Node head)
    {
        Node cursor = head;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
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