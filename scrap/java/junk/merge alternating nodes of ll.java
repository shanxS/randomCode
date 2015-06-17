// merge alternating nodes of ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,3,5,7};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }

        Integer[] array2 = {2,4,6,8};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }

        AlternateMerger qs = new AlternateMerger();
        qs.merger(ll1.getHead(), ll2.getHead());
        ll1.print();
    }
}

class AlternateMerger
{
    public void merger(Node node1, Node node2)
    {
        if (node1 == null || node2 == null)
        {
            return;
        }

        Node nextNode2 = node2.getNext();
        node2.setNext(node1.getNext());
        node1.setNext(node2);

        merger(node1.getNext().getNext(), nextNode2);
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

            while (cursor.getNext() != null)
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

    public static void print(Node node)
    {
        while(node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getNext();
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