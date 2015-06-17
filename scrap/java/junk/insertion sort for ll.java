// insertion sort for ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {6,5,3,1,8,7,2,4};
        LL ll = new LL();
        for (Integer value : array1)
        {
            ll.insert(value);
        }
        InsertionSort isort = new InsertionSort();
        LL.print(isort.sort(ll.getHead()));
    }
}


class InsertionSort
{

    // assumign atleast 2 nodes
    public Node sort(Node head)
    {
        Node sortStart = head;
        Node sortEnd = head.getNext();
        Node cursor = sortEnd.getNext();

        if (sortEnd.getValue() < sortStart.getValue())
        {
            sortStart.setNext(sortEnd.getNext());
            sortEnd.setNext(sortStart);

            sortStart = sortEnd;
            sortEnd = sortStart.getNext();
            sortEnd.setNext(null);
        }

        while (cursor != null)
        {
            Node nextNode = cursor.getNext();

            if (cursor.getValue() > sortEnd.getValue())
            {
                sortEnd.setNext(cursor);
                sortEnd = sortEnd.getNext();
                sortEnd.setNext(null);
            }
            else if (cursor.getValue() < sortStart.getValue())
            {
                cursor.setNext(sortStart);
                sortStart = cursor;
            }
            else
            {
                Node sortedCursor  = sortStart;
                while (sortedCursor.getNext().getValue() < cursor.getValue())
                {
                    sortedCursor = sortedCursor.getNext();
                }

                cursor.setNext(sortedCursor.getNext());
                sortedCursor.setNext(cursor);
            }

            cursor = nextNode;
        }

        return sortStart;
    }
}

class LL
{
    private Node head;

    public LL()
    {
        head = null;
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

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        Node cursor = node;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node (Integer value)
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