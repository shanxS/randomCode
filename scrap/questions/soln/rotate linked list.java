// rotate linked list

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,2,3};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        LLRotator gr = new LLRotator();
        LL.print(gr.rotate(ll1.getHead(), 2));

    }

}

class LLRotator
{
    public Node rotate(Node head, Integer k)
    {
        if (k == 0)
        {
            return head;
        }

        Node oldHead = head;
        Node cursor = head;
        Node newEnd = null;

        while (cursor.getNext() != null)
        {
            if (k == 1)
            {
                newEnd = cursor;
            }

            cursor = cursor.getNext();
            --k;
        }

        if (k < 1)
        {
            Node newHead = newEnd.getNext();
            newEnd.setNext(null);
            cursor.setNext(oldHead);

            return newHead;
        }
        else
        {
            return oldHead;
        }
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