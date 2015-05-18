// loop detector and resolver in linked list
// online q3, set22

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[] {1,2,3,4,5,6,7,8,9};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LoopDetectorAndReslover loopDetectorAndReslover = new LoopDetectorAndReslover(ll);
        System.out.println(loopDetectorAndReslover.hasLoop());
        ll.loop(5);
        System.out.println(loopDetectorAndReslover.hasLoop());
        loopDetectorAndReslover.resolve();
        System.out.println(loopDetectorAndReslover.hasLoop());
    }
}

class LoopDetectorAndReslover
{
    private Node head;
    private Node tort, hare;

    public LoopDetectorAndReslover(LL ll)
    {
        this.head = ll.getHead();
        this.tort = null;
        this.hare = null;
    }

    public Boolean hasLoop()
    {
        tort = head;
        hare = head.getNext();

        while(hare != null && hare.getNext() != null)
        {
            if (hare.getValue() == tort.getValue())
            {
                return true;
            }

            tort = tort.getNext();
            hare = hare. getNext().getNext();
        }

        return false;
    }

    public void resolve()
    {
        if (!hasLoop())
        {
            return;
        }

        Node cursor = tort;
        tort = tort.getNext();
        Integer lenght = 1;

        while(tort.getValue() != cursor.getValue())
        {
            ++lenght;
            tort = tort.getNext();
        }

        cursor = head;
        while(lenght > 1)
        {
            cursor = cursor.getNext();
            --lenght;
        }

        Node cursor2 = head;
        while(cursor.getNext().getValue() != cursor2.getValue())
        {
            cursor = cursor.getNext();
            cursor2 = cursor2.getNext();
        }

        cursor.setNext(null);
    }

}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public void loop (Integer value)
    {
        Node node = head;
        while(node != null)
        {
            if (node.getValue() == value)
            {
                break;
            }
            node = node.getNext();
        }

        if (node == null)
        {
            System.out.print("cant find node");
            return;
        }

        Node cursor = head;
        while(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }

        cursor.setNext(node);
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