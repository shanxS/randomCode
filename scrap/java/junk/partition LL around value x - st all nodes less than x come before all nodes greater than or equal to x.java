// partition LL around value x - st all nodes less than x come before all nodes greater than or equal to x

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,9,3,7,4,6,5,8};
        LLMaker llMaker = new LLMaker();
        Node ll = llMaker.make(ar);

        llMaker.print(ll);

        LLDivider llDivider = new LLDivider();
        Node divided = llDivider.divide(ll, 7);
        llMaker.print(divided);
    }
}

class LLDivider
{
    public Node divide(Node head, Integer v)
    {
        Node firstHalf = new Node(-1);
        Node firstCursor = firstHalf;
        Node secondHalf = new Node(-1);
        Node secondCursor = secondHalf;
        Node target = null;

        Node cursor = head;
        while (cursor != null)
        {
            if (cursor.getValue() == v)
            {
                target = cursor;
                cursor = cursor.getNext();
                target.setNext(null);
            }
            else if (cursor.getValue() < v)
            {
                firstCursor.setNext(cursor);
                cursor = cursor.getNext();

                firstCursor = firstCursor.getNext();
                firstCursor.setNext(null);

            }
            else if (cursor.getValue() > v)
            {
                secondCursor.setNext(cursor);
                cursor = cursor.getNext();

                secondCursor = secondCursor.getNext();
                secondCursor.setNext(null);
            }
        }

        firstCursor.setNext(target);
        target.setNext(secondHalf.getNext());

        return firstHalf.getNext();
    }
}

class LLMaker
{
    public Node make (Integer[] ar)
    {
        Node head = new Node(ar[0]);

        Node cursor = head;
        for (Integer i=1; i<ar.length; ++i)
        {
            cursor.setNext(new Node(ar[i]));
            cursor = cursor.getNext();
        }

        return head;
    }

    public void print(Node node)
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