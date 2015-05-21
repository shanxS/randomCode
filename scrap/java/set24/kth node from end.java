// kth node from end
// r1, q4, set24

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        System.out.print(getKthNodeFromEnd(ll.getHead(), 2).getValue());
    }

    private static Node getKthNodeFromEnd(Node head, Integer k)
    {

        if (k < 0)
        {
            return null;
        }

        Node cursor = head;
        Node node = null;
        while(cursor != null)
        {
            if (k <= 0)
            {
                node = (node == null) ? head : node.getNext();
            }

            --k;
            cursor = cursor.getNext();
        }

        return node;
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