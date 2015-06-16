// make new ll from common nodes of 2 sorted ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,2,3,4,8};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        Integer[] array2 = {2,4,6,8};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }
        LL.print(ll2.getHead());
        System.out.println();

        LLIntersectorGetter dr = new LLIntersectorGetter();
        LL.print(dr.getIntersectedLL(ll1.getHead(), ll2.getHead()));

    }

}

class LLIntersectorGetter
{
    public Node getIntersectedLL(Node node1, Node node2)
    {
        Node dummyHead = new Node(-1);

        findIntersection(node1, node2, dummyHead);

        return dummyHead.getNext();
    }

    private void findIntersection(Node node1, Node node2, Node resulatant)
    {
        if (node1 == null || node2 == null)
        {
            return;
        }

        if (node1.getValue() == node2.getValue())
        {
            resulatant.setNext(new Node(node1.getValue()));
            findIntersection(node1.getNext(), node2.getNext(), resulatant.getNext());
        }
        else if (node1.getValue() > node2.getValue())
        {
            findIntersection(node1, node2.getNext(), resulatant);
        }
        else
        {
            findIntersection(node1.getNext(), node2, resulatant);
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