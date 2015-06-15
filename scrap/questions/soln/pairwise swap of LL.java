// pairwise swap of LL

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,4};
//        Integer[] array = {1,4,1,1,4,4};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LL.print(ll.getHead());
        System.out.println();

        PairwiseSwapper dr = new PairwiseSwapper();
        LL.print(dr.swap(ll.getHead()));

    }

}

class PairwiseSwapper
{
    public Node swap(Node head)
    {
        if (head == null)
        {
            return null;
        }
        Node current = head;

        if (current.getNext() == null)
        {
            return head;
        }
        Node next = current.getNext();

        Node previous = null;

        Integer count = 2;
        while(count > 0)
        {
            current.setNext(previous);

            previous = current;
            current = next;
            if (next != null)
            {
                next = next.getNext();
            }

            --count;
        }

        head.setNext(swap(current));

        return previous;
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