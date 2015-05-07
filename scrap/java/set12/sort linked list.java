// sort linked list 
// r3, q2

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {3,2,2,1,2,3,1};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        ll.print();
        LLSort llSort = new LLSort(ll);
        System.out.println();
        llSort.sort().print();
    }
}


class LLSort
{
    private LL ll;

    public LLSort(LL ll)
    {
        this.ll = ll;
    }


    public LL sort()
    {
        return sort(ll);
    }

    private LL sort(LL ll)
    {
        if (ll.getSize() == 1)
        {
            return ll;
        }

        LL pll1 = ll.getPartialLL(0, ll.getSize()/2);
        pll1 = sort(pll1);

        LL pll2 = ll.getPartialLL(ll.getSize()/2, ll.getSize());
        pll2 = sort(pll2);

        return merge(pll1, pll2);
    }

    private LL merge(LL list1, LL list2)
    {
        LL merged = new LL();

        Node cursor1 = list1.getHead();
        Node cursor2 = list2.getHead();

        while(cursor1 != null && cursor2 != null)
        {
            if (cursor1.getValue() < cursor2.getValue())
            {
                merged.insert(cursor1.getValue());
                cursor1 = cursor1.getNext();
            }
            else if (cursor1.getValue() > cursor2.getValue())
            {
                merged.insert(cursor2.getValue());
                cursor2 = cursor2.getNext();
            }
            else if (cursor1.getValue() == cursor2.getValue())
            {
                merged.insert(cursor2.getValue());
                merged.insert(cursor1.getValue());
                cursor1 = cursor1.getNext();
                cursor2 = cursor2.getNext();
            }
        }

        while (cursor1 != null)
        {
            merged.insert(cursor1.getValue());
            cursor1 = cursor1.getNext();
        }

        while (cursor2 != null)
        {
            merged.insert(cursor2.getValue());
            cursor2 = cursor2.getNext();
        }

        return merged;
    }
}

class LL
{
    private Node head;
    private Integer size;

    public LL()
    {
        this.head = null;
        this.size = 0;
    }

    public void print()
    {
        Node cursor = head;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
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

        ++size;
    }

    public Integer getSize()
    {
        return size;
    }

    public LL getPartialLL(Integer start, Integer end)
    {
        if (start < 0 || end > getSize())
        {
            return null;
        }

        LL ll = new LL();

        Node cursor = head;
        Integer count = 0;

        while(count != start)
        {
            cursor = cursor.getNext();
            ++count;
        }

        while (count < end)
        {
            ll.insert(cursor.getValue());
            cursor = cursor.getNext();
            ++count;
        }

        return ll;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
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
}