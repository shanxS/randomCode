// print kth node from end and beggining for linked list
// online q4, set22

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }
        ll.printKth(3);
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

    public void printKth (Integer k)
    {
        Integer counter = k;
        Node end = head;
        Node lastK = head;
        Node startK = head;

        while (end != null)
        {
            if (counter > 1)
            {
                startK = startK.getNext();
            }
            else if (counter <= 0)
            {
                lastK = lastK.getNext();
            }

            end = end.getNext();
            --counter;
        }

        if (counter > k*(-1))
        {
            System.out.print("ll not large neogh");
            return;
        }

        System.out.println(startK.getValue() + " " + lastK.getValue());
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

