// remove duplicates form unsorted ll

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,4,3,5,1,1,2,2,3,6,3,4,3,3};
//        Integer[] array = {1,4,1,1,4,4};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LL.print(ll.getHead());
        System.out.println();

        DuplicateRemover dr = new DuplicateRemover();
        dr.remove(ll.getHead());
        LL.print(ll.getHead());

    }

}

class DuplicateRemover
{
    public void remove(Node head)
    {
        Node cursor = head;
        List<Integer> values = new ArrayList<>();
        values.add(cursor.getValue());

        while(cursor != null && cursor.getNext() != null)
        {
            if (values.contains(cursor.getNext().getValue()))
            {
                Node duplicateNodeCursor = cursor.getNext();

                while (duplicateNodeCursor != null && values.contains(duplicateNodeCursor.getValue()))
                {
                    Integer duplicateValue = duplicateNodeCursor.getValue();
                    while (duplicateNodeCursor.getNext() != null && duplicateNodeCursor.getNext().getValue() == duplicateValue)
                    {
                        duplicateNodeCursor = duplicateNodeCursor.getNext();
                    }
                    duplicateNodeCursor = duplicateNodeCursor.getNext();
                }


                cursor.setNext(duplicateNodeCursor);
                if (duplicateNodeCursor != null)
                {
                    values.add(cursor.getNext().getValue());
                }

            }
            else
            {
                values.add(cursor.getNext().getValue());
            }

            cursor = cursor.getNext();
        }
    }

}

class LL
{
    private Node head;

    public void makeLoop(Integer value)
    {
        Node endLoopNode = head;
        while (endLoopNode != null)
        {
            if (endLoopNode.getValue() == value)
            {
                break;
            }

            endLoopNode  = endLoopNode.getNext();
        }

        Node lastNode = head;
        while(lastNode.getNext() != null)
        {
            lastNode = lastNode.getNext();
        }

        lastNode.setNext(endLoopNode);
    }

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

    public void print()
    {
        LL.print(head);
    }

    public static void print(Node head)
    {
        Node cursor = head;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
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