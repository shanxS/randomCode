// copying list with random pointer

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        LL ll = new LL();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);
        ll.insert(7);

        ll.connectMad(2, 1);
        ll.connectMad(1, 3);
        ll.connectMad(7, 5);

        LL.print(ll.getHead());

        Copier copier = new Copier(ll);
        System.out.println("-------------------");
        LL.print(copier.copy());
    }

}

class Copier
{
    private Node head;

    public Copier(LL ll)
    {
        this.head = ll.getHead();
    }

    public Node copy()
    {
        Node cursor = head;

        while(cursor != null)
        {
            Node copyNode = new Node(cursor.getValue());
            copyNode.setNext(cursor.getNext());
            cursor.setNext(copyNode);

            cursor = copyNode.getNext();
        }


        cursor = head;
        while(cursor != null)
        {
            Node copyNode = cursor.getNext();

            if (cursor.getMad() != null)
            {
                copyNode.setMad(cursor.getMad().getNext());
            }

            cursor = copyNode.getNext();
        }

        cursor = head;
        Node head2 = cursor.getNext();
        Node cursor2 = head2;
        while(cursor != null)
        {
            cursor.setNext(cursor2.getNext());

            if (cursor2.getNext() != null)
            {
                cursor2.setNext(cursor2.getNext().getNext());
            }
            else
            {
                cursor2.setNext(null);
            }

            cursor = cursor.getNext();
            cursor2 = cursor2.getNext();

        }

        return head2;
    }
}

class LL
{
    private Node head;
    private Map<Integer,Node> valueNodeMap;

    public LL()
    {
        head = null;
        valueNodeMap = new HashMap<>();
    }

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " - ");

            if (cursor.getMad() != null)
            {
                System.out.print(cursor.getMad().getValue());
            }

            System.out.println();

            cursor = cursor.getNext();
        }
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            valueNodeMap.put(value, head);
        }
        else
        {
            Node cursor = head;

            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));

            valueNodeMap.put(value, cursor.getNext());
        }
    }

    public void connectMad(int parentVlaue, int childValue)
    {
        Node parent = valueNodeMap.get(parentVlaue);
        Node child = valueNodeMap.get(childValue);

        parent.setMad(child);
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node next, mad;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
        this.mad = null;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }
}