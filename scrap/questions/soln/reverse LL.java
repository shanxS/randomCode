// reverse LL

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1, 8, 3, 7, 4, 5};
        Integer[] array = {1};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.print();
        System.out.println("------------------------");

        LL.print(reverse(ll.getHead()));

    }

    private static Node reverse(Node node)
    {
        Node current = node;
        Node previous = null;
        Node next = current.getNext();

        while (next != null)
        {
            current.setNext(previous);
            previous = current;
            current = next;
            next = next.getNext();
        }
        current.setNext(previous);

        return current;
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