// counting practice

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8};
        LL ll = new LL();
        for(Integer value : array)
        {
            ll.insert(value);
        }

        Counter c = new Counter(ll);
        c.printKthParent(4);
    }
}


class Counter
{
    private Node head;

    public Counter(LL ll)
    {
        this.head = ll.getHead();
    }

    public void printKthParent(final Integer k)
    {
        Node firstParent = null;
        Node lastParent = null;
        Node cursor = head;
        Integer counter = k-1;

        while (cursor != null)
        {
            if (counter > 0)
            {
                firstParent = (firstParent == null) ? (head) : (firstParent.getNext());
            }
            else if (counter < -1 * (k-1))
            {
                lastParent = (lastParent == null) ? firstParent.getNext() : lastParent.getNext();
            }

            --counter;
            cursor = cursor.getNext();
        }

        if (lastParent == null || firstParent == null)
        {
            System.out.print("not long enough");
        }
        else
        {
            System.out.print(firstParent.getValue() + " "
                    + lastParent.getValue() + " "
                    + ((counter*-1) + k-1));
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

    public Node (Integer value)
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