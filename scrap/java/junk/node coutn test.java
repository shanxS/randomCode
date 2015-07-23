// node coutn test

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,6};
        LLUtil llUtil = new LLUtil();

        System.out.print(llUtil.getNth(llUtil.make(ar), 4).getValue());
    }
}

class LLUtil
{
    public Node make(Integer[] ar)
    {
        Node head = new Node(ar[0]);
        Node cursor = head;
        for (Integer i = 1; i < ar.length; ++i)
        {
            cursor.setNext(new Node(ar[i]));
            cursor = cursor.getNext();
        }

        return head;
    }

    public Node getNth(Node head, Integer n)
    {
        Node cursor = head;

        while (cursor != null && n > 1)
        {
            cursor = cursor.getNext();
            --n;
        }

        return cursor;
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
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