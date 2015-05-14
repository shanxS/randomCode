// linked list loop detector and resolver
// r2, q1, set19

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

        ll.makeLoopAt(5);
        LoopDetectorAndResolver loopDetectorAndResolver = new LoopDetectorAndResolver(ll);
        System.out.print(loopDetectorAndResolver.hasLoop());
        loopDetectorAndResolver.resolveLoop();

    }
}


class LoopDetectorAndResolver
{
    private Node head;
    private Node loopNode;

    public LoopDetectorAndResolver(LL ll)
    {
        this.head = ll.getHead();
        this.loopNode = null;
    }

    public Boolean hasLoop()
    {
        Node tort = head;
        Node hare = tort.getNext();

        while(tort != null && hare != null)
        {
            if (tort.getValue() == hare.getValue())
            {
                loopNode = tort;
                return true;
            }

            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        return false;
    }

    public void resolveLoop()
    {
        Node cursor = loopNode.getNext();
        Integer loopLength = 1;
        while(cursor.getValue() != loopNode.getValue())
        {
            ++loopLength;
            cursor = cursor.getNext();
        }

        cursor = head;
        Node secondCursor = cursor;
        while (loopLength > 1)
        {
            secondCursor = secondCursor.getNext();
            --loopLength;
        }

        cursor = head;
        while (cursor.getValue() != secondCursor.getNext().getValue())
        {
            cursor = cursor.getNext();
            secondCursor = secondCursor.getNext();
        }
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

    private Node findNode(Integer target)
    {
        Node cursor = head;

        while(cursor != null)
        {
            if (cursor.getValue() == target)
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
            System.out.print(node.getValue() + " ");
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