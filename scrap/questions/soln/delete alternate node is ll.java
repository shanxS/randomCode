// delete alternate node is ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,2,3,4,8};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        AlternateDeleter dr = new AlternateDeleter();
//        LL.print(dr.delete(ll1.getHead(), dr.keepFirst));
        LL.print(dr.delete(ll1.getHead(), dr.removeFirst));

    }

}

class AlternateDeleter
{
    public Boolean keepFirst = true;
    public Boolean removeFirst = false;

    public Node delete(Node head, Boolean shouldKeepFirst)
    {
        if (shouldKeepFirst)
        {
            perform(head);
            return head;
        }
        else
        {
            perform(head.getNext());
            return head.getNext();
        }
    }

    private void perform(Node node)
    {
        if (node == null)
        {
            return;
        }

        if (node.getNext() != null)
        {
            node.setNext(node.getNext().getNext());
            perform(node.getNext());
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