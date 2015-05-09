// linkedlist is made of 1 2 and 3 - sort it in one pass
// r3, q2, set14

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{2,3,1,1,1,3,3,2};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        LL.print(ll.getHead());
        System.out.println();
        LimitedSorter ls = new LimitedSorter(ll);
        LL.print(ls.sort());
    }
}

class LimitedSorter
{
    private Node node1, node2, node3, head;

    public LimitedSorter(LL ll)
    {
        this.node1 = null;
        this.node2 = null;
        this.node3 = null;
        this.head = ll.getHead();
    }

    public Node sort()
    {
        Node cursor1 = null;
        Node cursor2 = null;
        Node cursor3 = null;
        Node lagger = null;

        Node cursor = head;
        while(cursor != null)
        {
            if (lagger != null)
            {
                lagger.setNext(null);
            }

            switch (cursor.getValue())
            {
                case 1:
                    if (node1 == null)
                    {
                        node1 = cursor;
                        cursor1 = node1;
                    }
                    else
                    {
                        cursor1.setNext(cursor);
                        cursor1 = cursor1.getNext();
                    }

                    break;

                case 2:
                    if (node2 == null)
                    {
                        node2 = cursor;
                        cursor2 = node2;
                    }
                    else
                    {
                        cursor2.setNext(cursor);
                        cursor2 = cursor2.getNext();
                    }

                    break;

                case 3:
                    if(node3 == null)
                    {
                        node3 = cursor;
                        cursor3 = node3;
                    }
                    else
                    {
                        cursor3.setNext(cursor);
                        cursor3 = cursor3.getNext();
                    }

                    break;
            }

            lagger = cursor;
            cursor = cursor.getNext();
        }

        cursor1.setNext(node2);
        cursor2.setNext(node3);

        return node1;
    }
}

class LL
{
    private Node head;

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

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        Node cursor = node;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
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