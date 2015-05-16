// detect and resolve loops in linkedlist
// r1, q1, set21

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LoopDetector ld = new LoopDetector();
        System.out.println(ld.hasLoop(ll));
        ll.makeLoopAt(4);
        System.out.println(ld.hasLoop(ll));

        LoopResolver lr = new LoopResolver();
        lr.resolve(ll);
        System.out.println(ld.hasLoop(ll));

    }
}

class LoopResolver
{
    public void resolve(LL ll)
    {
        final Node head = ll.getHead();

        Node tort = ll.getHead();
        Node hare = ll.getHead().getNext();

        while (tort != null && hare != null)
        {
            if (tort.getValue() == hare.getValue())
            {
                break;
            }

            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        tort = tort.getNext();
        Node start = head;
        Node end = head.getNext();
        while (tort.getNext().getValue() != hare.getValue())
        {
            tort = tort.getNext();
            end = end.getNext();
        }

        while (end.getNext().getValue() != start.getValue())
        {
            start = start.getNext();
            end = end.getNext();
        }

        end.setNext(null);

    }
}

class LoopDetector
{
    public Boolean hasLoop(LL ll)
    {
        Node tort = ll.getHead();
        Node hare = ll.getHead().getNext();

        while(tort != null && hare !=null)
        {
            if (tort.getValue() == hare.getValue())
            {
                return true;
            }

            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        return false;
    }
}


class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public void makeLoopAt(Integer value)
    {
        Node node = findNode(value);
        Node cursor = head;
        while(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }
        cursor.setNext(node);
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

    public void setHead(Node head)
    {
        this.head = head;
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
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