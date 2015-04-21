public class Main {

    public static void main(String[] args)
    {
        try
        {
            new Tester();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}

class Tester
{
    private Boolean ruleLessThan;

    public Tester() throws Exception {
        ruleLessThan = null;

        test();
    }

    public void test() throws Exception {
        LinkedList list1 = new LinkedList();
        list1.insert(1);
        list1.insert(3);
        list1.insert(5);
        list1.insert(7);

        LinkedList list2 = new LinkedList();
        list2.insert(2);
        list2.insert(4);
        list2.insert(6);
        list2.insert(8);

        /*LinkedList list1 = new LinkedList();
        list1.insert(7);
        list1.insert(5);
        list1.insert(3);
        list1.insert(1);

        LinkedList list2 = new LinkedList();
        list2.insert(8);
        list2.insert(6);
        list2.insert(4);
        list2.insert(2);*/

        Merger merger = new Merger(list1, list2);
        LinkedList merged = merger.merge();
        merged.print();;

        assert merged.getSize() == (list1.getSize() + list2.getSize());

        Node cursor = merged.getCursor();
        while (cursor != null && cursor.getNext() != null)
        {
            if (ruleLessThan == null)
            {
                Integer first = cursor.getValue();
                cursor = cursor.getNext();
                Integer second = cursor.getValue();

                ruleLessThan = first < second;
            }
            else
            {
                Integer first = cursor.getValue();
                cursor = cursor.getNext();
                Integer second = cursor.getValue();

                if (ruleLessThan != first < second)
                {
                    throw new Exception("not merged properly at "
                            + first + " " + second);
                }
            }
        }
    }
}

class Merger
{
    LinkedList list1, list2;

    public Merger(LinkedList list1, LinkedList list2)
    {
        this.list1 = list1;
        this.list2 = list2;
    }

    public LinkedList merge()
    {
        LinkedList mergedList = new LinkedList();
        Node cursor1 = list1.getCursor();
        Node cursor2 = list2.getCursor();

        while(cursor1 != null && cursor2 != null)
        {
            if (cursor1.getValue() > cursor2.getValue())
            {
                mergedList.insert(cursor1.getValue());
                cursor1 = cursor1.getNext();
            }
            else
            {
                mergedList.insert(cursor2.getValue());
                cursor2 = cursor2.getNext();
            }
        }

        while (cursor1 != null)
        {
            mergedList.insert(cursor1.getValue());
            cursor1 = cursor1.getNext();
        }

        while (cursor2 != null)
        {
            mergedList.insert(cursor2.getValue());
            cursor2 = cursor2.getNext();
        }

        return mergedList;
    }
}

class LinkedList
{
    private Node head;
    private Integer size;

    public LinkedList()
    {
        head = null;
        size = 0;
    }

    public Node getCursor()
    {
        return head;
    }

    void print()
    {
        Node cursor = head;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
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
            while (cursor.getNext() != null)
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
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value) {
        this.value = value;
        this.next = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}