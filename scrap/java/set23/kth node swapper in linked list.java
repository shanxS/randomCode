// kth node swapper in linked list
// online q4, set23

public class Main
{
    public  static void main(String[] er)
    {
        //Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        //Integer[] array = new Integer[]{1,2,3,4,5,6};
        Integer[] array = new Integer[]{1,2,3,4,5};
        
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        Swapper s = new Swapper(ll);
        s.swap(3);
    }
}

class Swapper
{
    private Node head;

    public Swapper(LL ll)
    {
        this.head = ll.getHead();
    }

    public void swap(Integer k)
    {
        Integer parentCount = k - 1;
        Node leader = head;
        Node lastParent = head;
        Node firstParent = head;

        while (leader != null)
        {
            if (parentCount > 1)
            {
                firstParent = firstParent.getNext();
            } else if (parentCount < -1)
            {
                lastParent = lastParent.getNext();
            }
            --parentCount;
            leader = leader.getNext();
        }

        if (parentCount + 1 >= (-1) * (k - 1))
        {
            System.out.print("list not long");
            return;
        }

        Node first = firstParent.getNext();
        Node firstNext = first.getNext();
        Node last = lastParent.getNext();
        Node lastNext = last.getNext();

        if (firstParent.getNext().getValue() == lastParent.getValue())
        {
            firstParent.setNext(last);
            lastParent.setNext(lastNext);
            last.setNext(lastParent);
        }
        else
        {
            last.setNext(firstNext);
            first.setNext(lastNext);

            lastParent.setNext(first);
            firstParent.setNext(last);
        }

        LL.print(head);

    }
}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue());
            cursor = cursor.getNext();
        }
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