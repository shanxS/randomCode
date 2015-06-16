// delete n nodes after m nodes in ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.print();
        delete(ll.getHead(), 2, 3);
        ll.print();
    }

    private static void delete(Node head, Integer m, Integer n)
    {
        Node cursor = head;
        Integer mCount = m;
        Integer nCount = n;
        while (cursor != null)
        {
            if (mCount == 1 && cursor.getNext() != null)
            {
                Node nextNode  = cursor.getNext();
                nCount = n;
                while(nextNode.getNext() != null && nCount > 1)
                {
                    nextNode = nextNode.getNext();
                    --nCount;
                }

                cursor.setNext(nextNode.getNext());
                cursor = cursor.getNext();
                mCount = m;
            }
            else
            {
                cursor = cursor.getNext();
                --mCount;
            }
        }
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

    private static void print(Node node)
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