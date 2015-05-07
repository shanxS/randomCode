// practice - detect and remvoe cycle form linked list
// technical, q4, set11

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        ll.cycleAt();
        ll.makeCyclic(4);
        ll.cycleAt();

        CycleDetector cd = new CycleDetector(ll);
        System.out.println("detected cycle " + cd.hasCycle());
        LL.print(cd.correctCycle());
    }
}

class CycleDetector
{
    private Node head;

    public CycleDetector(LL ll)
    {
        head = ll.getHead();
    }

    public Node correctCycle()
    {
        if (!hasCycle())
        {
            return head;
        }

        Node tort = head;
        Node hare = head.getNext();

        while(hare != null && hare.getNext() != null && hare.getValue() != tort.getValue())
        {
            hare = hare.getNext().getNext();
            tort = tort.getNext();
        }

        Integer cycleSize = 1;
        tort = tort.getNext();
        while (tort.getValue() != hare.getValue())
        {
            tort = tort.getNext();
            ++cycleSize;
        }

        Node cursor1 = head;
        while(cycleSize > 0)
        {
            cursor1 = cursor1.getNext();
            --cycleSize;
        }

        Node endNode = cursor1;
        cursor1 = cursor1.getNext();
        Node cursor2 = head.getNext();
        while(cursor1.getValue() != cursor2.getValue())
        {
            cursor1 = cursor1.getNext();
            cursor2 = cursor2.getNext();
            endNode = endNode.getNext();
        }

        endNode.setNext(null);

        return head;
    }

    public boolean hasCycle()
    {
        Node tort = head;
        Node hare = head.getNext();

        while(hare != null && hare.getNext() != null && hare.getValue() != tort.getValue())
        {
            hare = hare.getNext().getNext();
            tort = tort.getNext();
        }

        return (hare == null || hare.getNext() == null) ? false : true;
    }
}

class LL
{
    private Node head;
    private Node endNode;

    public LL()
    {
        this.head = null;
        this.endNode = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            endNode = head;
        }
        else
        {
            Node cursor = head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
            endNode = cursor.getNext();
        }
    }

    public void makeCyclic (Integer value)
    {
        Node cursor = head;
        while(cursor != null && cursor.getValue() != value)
        {
            cursor = cursor.getNext();
        }

        if (cursor == null)
        {
            System.out.println("cant make cycle");
            return;
        }

        endNode.setNext(cursor);
    }

    public void cycleAt()
    {
        if (endNode.getNext() == null)
        {
            System.out.println("no cycle");
            return;
        }

        System.out.println("cycle at " + endNode.getValue() + " " + endNode.getNext().getValue());
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

    public static void print(Node node, Integer limit)
    {
        Node cursor = node;

        while(cursor != null && cursor.getValue() != limit)
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
        this.next = null;
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