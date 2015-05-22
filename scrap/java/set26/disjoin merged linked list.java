// disjoin merged linked list
// r2, q2, set26

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = new Integer[]{1,2,3,4,5,6};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }

        Integer[] array2 = new Integer[]{7,8,9,10};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }

        ll1.connect(ll2, 4);

        LLSeparator lls = new LLSeparator();
        lls.separate(ll1, ll2);

    }
}

class LLSeparator
{
    public void separate(LL ll1, LL ll2)
    {
        Node endNode = getEndNode(ll1);
        endNode.setNext(ll2.getHead());

        Node endNodeLoop = getEndNodeLoop(ll1);
        endNodeLoop.setNext(null);
    }

    private Node getEndNodeLoop(LL ll1)
    {
        Node tort = ll1.getHead();
        Node hare = tort.getNext();
        while(hare != null && hare.getValue() != tort.getValue())
        {
            tort = tort.getNext();
            hare = hare.getNext().getNext();
        }

        tort = tort.getNext();
        Integer length=1;
        while (tort.getNext().getValue() != hare.getValue())
        {
            tort = tort.getNext();
            ++length;
        }

        Node cursor1 = ll1.getHead();
        while(length > 0)
        {
            cursor1 = cursor1.getNext();
            --length;
        }

        Node cursor2 = ll1.getHead();
        while (cursor1.getNext().getValue() != cursor2.getValue())
        {
            cursor1 = cursor1.getNext();
            cursor2 = cursor2.getNext();
        }

        return cursor1;
    }

    private Node getEndNode(LL ll)
    {
        Node cursor = ll.getHead();
        while(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }

        return cursor;
    }
}

class LL
{
    private Node head;

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

    public void connect(LL ll, Integer value)
    {
        Node node = head;
        while(node != null && node.getValue() != value)
        {
            node = node.getNext();
        }

        if (node == null)
        {
            System.out.print("cant find node");
        }

        Node endLL = ll.getHead();
        while(endLL.getNext() != null)
        {
            endLL = endLL.getNext();
        }

        endLL.setNext(node);
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