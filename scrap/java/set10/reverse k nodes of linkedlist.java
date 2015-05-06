// reverse k nodes of linkedlist
// r4, q4, set10, amazon

public class Main
{
    public static void main(String[] args)
    {
        LL ll = new LL();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);
        ll.insert(7);
        ll.insert(8);

        LL.print(ll.getHead());
        Reverser reverser = new Reverser(ll);
        reverser.doReverse(3);

    }
}

class Reverser
{
    private Node head;

    public Reverser(LL ll)
    {
        this.head = ll.getHead();
    }

    public void doReverse(Integer limit)
    {
        head = reverse(head, limit);
        LL.print(head);
    }

    private Node reverse(Node node, Integer limit)
    {
        if (node == null || node.getNext() == null)
        {
            return node;
        }

        Node current = node;
        Node next = current.getNext();
        if (next.getNext() == null)
        {
            next.setNext(current);
            current.setNext(null);
            return next;
        }

        Node nextNext = next.getNext();

        Integer numNode = limit;
        while (numNode > 1 && next != null)
        {
            next.setNext(current);
            current = next;
            next = nextNext;

            nextNext = nextNext.getNext();
            --numNode;
        }

        if (next != null)
        {
            node.setNext(reverse(next, limit));
        }
        else
        {
            node.setNext(null);
        }

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