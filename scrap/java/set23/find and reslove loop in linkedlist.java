// find and reslove loop in linkedlist
// online q3, set23

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for(Integer value : array)
        {
            ll.insert(value);
        }

        LoopDetectorAndResolver ldr = new LoopDetectorAndResolver(ll);
        System.out.println(ldr.hasLoop());
        ll.makeLoop(3);
        System.out.println(ldr.hasLoop());
        ldr.resolveLoop();
        System.out.println(ldr.hasLoop());
        LL.print(ll.getHead());
    }
}

class LoopDetectorAndResolver
{
    private Node head;
    private Node tort, hare;

    public LoopDetectorAndResolver(LL ll)
    {
        this.head = ll.getHead();
        this.tort = null;
        this.hare = null;
    }

    public void resolveLoop()
    {
        if (!hasLoop())
        {
            return;
        }

        Node cursor = tort.getNext();
        Integer count = 1;
        while(cursor.getNext().getValue() != tort.getValue())
        {
            cursor = cursor.getNext();
            ++count;
        }

        Node cursor2 = head;
        cursor = cursor2.getNext();
        while(count > 1)
        {
            cursor = cursor.getNext();
            --count;
        }

        while(cursor.getNext().getValue() != cursor2.getValue())
        {
            cursor = cursor.getNext();
            cursor2 = cursor2.getNext();
        }

        cursor.setNext(null);
    }


    public Boolean hasLoop ()
    {
        tort = head;
        hare = head.getNext();

        while(hare != null)
        {
            if (hare.getValue() == tort.getValue())
            {
                return true;
            }
            hare = hare.getNext().getNext();
            tort = tort.getNext();
        }

        return false;
    }

}

class LL
{
    private Node head;

    public LL()
    {
        head = null;
    }

    public void makeLoop(Integer value)
    {
        Node node = getNode(value);
        if (node == null)
        {
            System.out.print("cant find ndoe");
        }

        Node cursor = head;
        while(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }

        cursor.setNext(node);
    }

    private Node getNode(Integer value)
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