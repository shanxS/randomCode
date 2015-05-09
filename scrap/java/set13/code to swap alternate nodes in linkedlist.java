// code to swap alternate nodes in linkedlist
// r1, q2, set14

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2};//{1,2,3};//{1,2,3,4,5,6};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        LL.print(ll.getHead());

        AlternateSwapper as = new AlternateSwapper(ll);
        LL.print(as.swap());
    }
}

class AlternateSwapper
{
    private Node head;

    public AlternateSwapper(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node swap()
    {
        if (head == null)
        {
            return null;
        }

        Node first = head;
        if (first.getNext() == null || first.getNext().getNext() == null)
        {
            return first;
        }

        Node mid = first.getNext();
        Node last = mid.getNext();
        head = last;
        Node lagger = null;

        while(last != null)
        {
            first.setNext(last.getNext());
            last.setNext(mid);
            mid.setNext(first);

            if(lagger != null)
            {
                lagger.setNext(last);
            }

            lagger = first;
            first = first.getNext();
            if (first != null)
            {
                mid = first.getNext();
                if (mid != null)
                {
                    last = mid.getNext();
                }
            }
            else
            {
                mid = null;
                last = null;
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