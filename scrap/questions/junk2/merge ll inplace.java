// merge ll inplace

public class Main
{
    public static void main(String[] er)
    {
        LLUtil util = new LLUtil();

        Integer[] ar1 = {1,2,3,4,8,10,12};
        Node ll1 = util.make(ar1);

        Integer[] ar2 = {5,11,13};
        Node ll2 = util.make(ar2);

        Node merged = util.merge(ll1, ll2);
        util.print(merged);
    }
}

class LLUtil
{
    public Node make (Integer[] ar)
    {
        Node head = new Node(ar[0]);
        Node end  = head;
        Integer cursor = 1;
        while (cursor < ar.length)
        {
            end.setNext(new Node(ar[cursor]));
            end = end.getNext();
            ++cursor;
        }

        return head;
    }

    public Node merge(Node ll1, Node ll2)
    {
        Node mergedHead = (ll1.getValue() < ll2.getValue()) ? ll1 : ll2;
        Node cursor1 = mergedHead;
        Node cursor2 = (ll1.getValue() > ll2.getValue()) ? ll1 : ll2;;

        while(cursor1 != null && cursor2 != null)
        {
            while (cursor1.getNext() != null && cursor1.getNext().getValue() < cursor2.getValue())
            {
                cursor1 = cursor1.getNext();
            }

            if (cursor1.getNext() == null)
            {
                cursor1.setNext(cursor2);
                break;
            }
            else
            {
                Node nextNode1 = cursor1.getNext();
                cursor1.setNext(cursor2);

                while(cursor1.getNext() != null && cursor1.getNext().getValue() < nextNode1.getValue())
                {
                    cursor1 = cursor1.getNext();
                }

                if (cursor1.getNext() == nextNode1)
                {
                    cursor1.setNext(nextNode1);
                    break;
                }
                else
                {
                    Node nextNode2 = cursor1.getNext();
                    cursor1.setNext(nextNode1);
                    cursor2 = nextNode2;
                }
            }
        }

        return mergedHead;
    }

    public void print(Node head)
    {
        Node cursor = head;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}

class Node
{
    private Integer value;
    private Node next;

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