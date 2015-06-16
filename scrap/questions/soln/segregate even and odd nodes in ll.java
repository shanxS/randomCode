// segregate even and odd nodes in ll

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array1 = {2,3,4,5,6};
//        Integer[] array1 = {1,2,3,4,5,6};
        Integer[] array1 = {2,3,4,5};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        EvenOddSeggregator gr = new EvenOddSeggregator();
        LL.print(gr.segregate(ll1.getHead()));

    }

}

class EvenOddSeggregator
{
    public Node segregate(Node head)
    {
        Node lastNode = head;
        while (lastNode.getNext() != null)
        {
            lastNode = lastNode.getNext();
        }
        Integer lastNodeValue = lastNode.getValue();

        Node dummyHead = new Node(-1);
        dummyHead.setNext(head);
        Node cursor = dummyHead;
        while (cursor.getNext().getValue() != lastNodeValue)
        {
            if (cursor.getNext().getValue() % 2 == 0)
            {
                Node nextNode = cursor.getNext();
                cursor.setNext(nextNode.getNext());

                lastNode.setNext(nextNode);
                lastNode = lastNode.getNext();
                lastNode.setNext(null);
            }
            else
            {
                cursor = cursor.getNext();
            }
        }
        if (cursor.getNext().getValue() % 2 == 0)
        {
            Node nextNode = cursor.getNext();
            cursor.setNext(nextNode.getNext());

            lastNode.setNext(nextNode);
            lastNode = lastNode.getNext();
            lastNode.setNext(null);
        }

        return dummyHead.getNext();
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