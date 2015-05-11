// swapping alternate nodes of linked list
// written, q1

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        Swapper swapper = new Swapper(ll);
        LL.print(swapper.swap());
    }
}

class Swapper
{
    private Node head;

    public Swapper(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node swap()
    {
        Node cursor = head;
        Node lagger = null;
        head = cursor.getNext();

        while(cursor != null && cursor.getNext() != null)
        {
            Node next = cursor.getNext();
            cursor.setNext(next.getNext());
            next.setNext(cursor);

            if (lagger != null)
            {
                lagger.setNext(next);
            }
            lagger = cursor;
            cursor = cursor.getNext();
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