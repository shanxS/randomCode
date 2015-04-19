// merge sort for linked list

public class Main {

    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {3,7,8,5,2,1,9,5,4};
        LinkedList ll = new LinkedList();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        MergeSort ms = new MergeSort(ll);
        ms.sort().print();

    }
}

class Node implements Comparable
{
    private Integer m_value;
    private Node m_next;

    public Node(Integer value)
    {
        m_value = value;
        m_next = null;
    }

    public Integer getValue() {
        return m_value;
    }

    public void setValue(Integer m_value) {
        this.m_value = m_value;
    }

    public Node getNext() {
        return m_next;
    }

    public void setNext(Node m_next) {
        this.m_next = m_next;
    }

    @Override
    public int compareTo(Object o) {
        Node node = (Node) o;
        if (getValue() > node.getValue())
        {
            return 1;
        }
        else if (getValue() < node.getValue())
        {
            return -1;
        }

        return 0;
    }
}

class LinkedList
{
    private Node m_head;
    private Integer m_size;

    public LinkedList(Integer value)
    {
        m_head = null;
        m_size = 0;

        insert(value);
    }

    public LinkedList()
    {
        m_head = null;
        m_size = 0;
    }

    public void insert(Node node)
    {
        insert(node.getValue());
    }

    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            Node cursor = m_head;
            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }

        ++m_size;
    }

    public Integer getSize()
    {
        return m_size;
    }

    public LinkedList getSubList(int start, int size) {
        LinkedList subList = new LinkedList();

        Integer counter = 0;
        Node cursor = m_head;
        while(counter < size)
        {
            if (counter >= start)
            {
                subList.insert(cursor.getValue());
            }

            ++counter;
            cursor = cursor.getNext();
        }

        return subList;
    }

    public Node getCursor() {
        return m_head;
    }

    public void print() {
        Node cursor = m_head;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }

        System.out.println();
    }
}

class MergeSort
{
    private LinkedList m_ll;

    public MergeSort(LinkedList ll)
    {
        m_ll = ll;
    }

    public LinkedList sort()
    {
        return sort(m_ll);
    }

    private LinkedList sort(LinkedList ll)
    {
        if (ll.getSize() == 1)
        {
            return ll;
        }

        LinkedList firstSortedList = sort(ll.getSubList(0, ll.getSize()/2));
        LinkedList secondSortedList = sort(ll.getSubList(ll.getSize()/2, ll.getSize()));

        return merge(firstSortedList, secondSortedList);
    }

    private LinkedList merge(LinkedList firstSortedList, LinkedList secondSortedList) {
        LinkedList mergedList = new LinkedList();

        Node firstCursor = firstSortedList.getCursor();
        Node secondCursor = secondSortedList.getCursor();

        while (firstCursor != null
                && secondCursor != null)
        {
            if (firstCursor.compareTo(secondCursor) > 0)
            {
                mergedList.insert(firstCursor);
                firstCursor = firstCursor.getNext();
            }
            else
            {
                mergedList.insert(secondCursor);
                secondCursor = secondCursor.getNext();
            }
        }

        while(firstCursor != null)
        {
            mergedList.insert(firstCursor);
            firstCursor = firstCursor.getNext();
        }

        while(secondCursor != null)
        {
            mergedList.insert(secondCursor);
            secondCursor = secondCursor.getNext();
        }

        return mergedList;
    }

}
