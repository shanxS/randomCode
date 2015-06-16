// reverse alternate k nodes in ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,2,3,4,5,6};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        GroupReverser gr = new GroupReverser();
        LL.print(gr.alternaeReverse(ll1.getHead(), 2));

    }

}

class GroupReverser
{
    public Node alternaeReverse(Node head, final Integer k)
    {
        if (head == null || k == 0)
        {
            return head;
        }
        Node current = head;

        if (current.getNext() == null)
        {
            return head;
        }
        Node next = current.getNext();
        Node previous = null;

        Integer count = k;
        while(count > 0 && current != null)
        {
            current.setNext(previous);

            previous = current;
            current = next;

            if (next != null)
            {
                next = next.getNext();
            }
            --count;
        }

        head.setNext(current);

        count = k;
        while(count > 1 && current != null)
        {
            current = current.getNext();
            --count;
        }

        if (current != null)
        {
            current.setNext(alternaeReverse(current.getNext(), k));
        }
        return previous;
    }
}

class LL
{
    private Node head;

    public void makeLoop(Integer value)
    {
        Node endLoopNode = head;
        while (endLoopNode != null)
        {
            if (endLoopNode.getValue() == value)
            {
                break;
            }

            endLoopNode  = endLoopNode.getNext();
        }

        Node lastNode = head;
        while(lastNode.getNext() != null)
        {
            lastNode = lastNode.getNext();
        }

        lastNode.setNext(endLoopNode);
    }

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

    public void print()
    {
        LL.print(head);
    }

    public static void print(Node head)
    {
        Node cursor = head;

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
    private Node next;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}