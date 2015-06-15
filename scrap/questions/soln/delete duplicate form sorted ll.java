// delete duplicate form sorted ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,1,1,2,2,3,3,3};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LL.print(ll.getHead());

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

        while (cursor != null && cursor.getNext() != null)
        {
            if (cursor.getValue() == cursor.getNext().getValue())
            {
                Node repeatedNode = cursor.getNext();
                while(repeatedNode.getNext() != null && repeatedNode.getValue() == repeatedNode.getNext().getValue())
                {
                    repeatedNode = repeatedNode.getNext();
                }

                cursor.setNext(repeatedNode.getNext());
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