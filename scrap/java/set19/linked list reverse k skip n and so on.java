// linked list reverse k skip n and so on
// r5, q1, set19

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9,10,11};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        PartialReverse pr = new PartialReverse(ll);
        LL.print(pr.reverse(3,2));
    }
}


class PartialReverse
{
    private Node head;

    public PartialReverse(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node reverse(Integer rCount, Integer skipCount)
    {
        return reverse(head, rCount, skipCount);
    }

    private Node reverse(Node node, Integer rCount, Integer sCount)
    {
        if (node == null)
        {
            return null;
        }
        Node cursor = node;

        if (cursor.getNext() == null)
        {
            return node;
        }
        Node next = cursor.getNext();

        if (next.getNext() == null)
        {
            if (rCount == 2)
            {
                next.setNext(cursor);
                cursor.setNext(null);

                return next;
            }
            else
            {
                return cursor;
            }
        }
        Node nextNext = next.getNext();

        Integer reverserCount = 1;
        while(reverserCount < rCount && nextNext != null)
        {
            next.setNext(cursor);
            cursor = next;
            next = nextNext;

            nextNext = nextNext.getNext();
            ++reverserCount;
        }

        node.setNext(next);

        Integer skipCount = 1;
        while(skipCount < sCount && next != null)
        {
            next = next.getNext();
            ++skipCount;
        }

        if (next != null)
        {
            next.setNext(reverse(next.getNext(), rCount, sCount));
        }

        return cursor;
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

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
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