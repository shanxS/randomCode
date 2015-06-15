// insert node in sorted ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2,4,5,6,8};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        InsertSorted is = new InsertSorted();
        LL.print(is.insert(ll.getHead(), 9));

    }

}

class InsertSorted
{
    public Node insert(Node head, Integer value)
    {
        if (value < head.getValue())
        {
            Node newHead = new Node(value);
            newHead.setNext(head);
            return newHead;
        }

        Node cursor = head;
        while(cursor.getNext() != null && cursor.getValue() < value)
        {
            cursor = cursor.getNext();
        }

        if (cursor.getValue() < value)
        {
            cursor.setNext(new Node(value));
        }
        else
        {
            Node nextCursor = cursor.getNext();
            Node newNode = new Node(value);
            cursor.setNext(newNode);
            newNode.setNext(nextCursor);
        }

        return head;
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