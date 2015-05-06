// detect and remove cycle from linked list
// technical, q4, set11

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        ll.makeCyclic(2);
        ll.cyclicAt();

        CycleDetector cd = new CycleDetector(ll);
        //System.out.print("cycle " + cd.hasCycle());
        cd.correctCycle();
        LL.print(cd.getHead());
    }
}

class CycleDetector
{
    private Node head;

    public CycleDetector(LL ll)
    {
        this.head = ll.getHead();
    }

    public boolean hasCycle()
    {
        Node tort = head;
        Node hare = head.getNext();

        while(hare != null && hare.getNext() != null && tort.getValue() != hare.getValue())
        {
            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        return (hare == null || hare.getNext() == null) ? false : true;
    }

    public void correctCycle()
    {
        if (!hasCycle())
        {
            return;
        }

        Node tort = head;
        Node hare = head.getNext();

        while(tort.getValue() != hare.getValue())
        {
            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        Integer cycleNodeCount = 0;
        tort = tort.getNext();
        while(tort.getValue() != hare.getValue())
        {
            tort = tort.getNext();
            ++cycleNodeCount;
        }
        ++cycleNodeCount;

        Node cursor1 = head;
        while(cycleNodeCount > 0)
        {
            cursor1 = cursor1.getNext();
            --cycleNodeCount;
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

        System.out.println("end node : " + endNode.getValue());
        endNode.setNext(null);
    }

    public Node getHead()
    {
        return head;
    }
}

class LL
{
    private Node head, endNode;

    public Integer getEnd()
    {
        return endNode.getValue();
    }

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

    public void makeCyclic(int n)
    {
        Node cursor = head;
        while (n > 0 && cursor != null)
        {
            cursor = cursor.getNext();
            --n;
        }

        endNode.setNext(cursor);
    }

    public void cyclicAt()
    {
        if (endNode != null && endNode.getNext() != null)
        {
            System.out.println(endNode.getValue() + " " + endNode.getNext().getValue());
        }
        else
        {
            System.out.println("not cyclix");
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

    public static void print(Node node, Integer end)
    {
        Node cursor = node;
        while(node.getValue() != end)
        {
            System.out.print(node.getValue() + " ");
        }
        System.out.print(node.getValue() + " ");
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