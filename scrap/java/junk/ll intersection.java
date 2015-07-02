// ll intersection

public class Main
{
    public static void main(String[] er)
    {
        Integer[] a1 = {1,5,6,7,10,12};
        LL l1 = new LL();
        for (Integer i : a1)
        {
            l1.insert(i);
        }

        Integer[] a2 = {3,6,7,8,9,10,11};
        LL l2 = new LL();
        for (Integer i : a2)
        {
            l2.insert(i);
        }

        LLIntersection lu = new LLIntersection();
        LL.print(lu.intersection(l1, l2));
    }
}

class LLIntersection
{
    public Node intersection(LL l1, LL l2)
    {
        LL llIntersect = new LL();

        Node cursor1 = l1.getHead();
        Node cursor2 = l2.getHead();

        while (cursor1 != null && cursor2 != null)
        {
            while (cursor1 != null && cursor1.getValue() < cursor2.getValue())
            {
                cursor1 = cursor1.getNext();
            }

            while (cursor2 != null && cursor1.getValue() > cursor2.getValue())
            {
                cursor2 = cursor2.getNext();
            }

            if (cursor1 != null && cursor2 != null && cursor1.getValue() == cursor2.getValue())
            {
                llIntersect.insert(cursor1.getValue());
                cursor1 = cursor1.getNext();
                cursor2 = cursor2.getNext();
            }
        }

        return llIntersect.getHead();
    }
}

class LL
{
    private Node head;

    public LL()
    {}

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            Node cursor = head;
            while (cursor.getNext() != null)
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

    public static void print(Node node)
    {
        while (node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
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