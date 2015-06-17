// copy ll with mad pointer

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1,2,3,4,5,6};
        LL ll = new LL();
        for (Integer value : array1)
        {
            ll.insert(value);
        }
        ll.connectMad(2,5);
        ll.connectMad(5, 1);
        ll.connectMad(6, 1);
        ll.print();

        Duplicator isort = new Duplicator();
        LL.print(isort.copy(ll.getHead()));
    }
}



class Duplicator
{

    public Node copy(Node head)
    {
        copyNodes(head);
        connectMad(head);
        return ripCopy(head);
    }

    private Node ripCopy(Node origHead)
    {
        Node copyHead = origHead.getNext();
        Node origCursor = origHead;
        while (origCursor != null)
        {
            Node copyCursor = origCursor.getNext();
            origCursor.setNext(copyCursor.getNext());

            if (copyCursor.getNext() != null)
            {
                copyCursor.setNext(copyCursor.getNext().getNext());
            }

            origCursor = origCursor.getNext();
        }

        return copyHead;
    }

    private void connectMad(Node node)
    {
        while (node != null)
        {
            if (node.getMad() != null)
            {
                Node copyNode = node.getNext();
                copyNode.setMad(node.getMad().getNext());
            }

            node = node.getNext().getNext();
        }
    }

    private void copyNodes(Node node)
    {
        while (node != null)
        {
            Node copyNode = new Node (node.getValue());
            copyNode.setNext(node.getNext());
            node.setNext(copyNode);

            node = copyNode.getNext();
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

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        Node cursor = node;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            if (cursor.getMad() != null)
            {
                System.out.print(cursor.getMad().getValue() + " ");
            }
            System.out.println();
            cursor = cursor.getNext();
        }
        System.out.println("-------------------");
    }

    public void connectMad(Integer parentValue, Integer childValue)
    {
        Node parent = getNode (parentValue);
        Node child = getNode (childValue);

        parent.setMad(child);
    }

    private Node getNode(Integer value)
    {
        Node cursor = head;
        while (cursor != null)
        {
            if (cursor.getValue() == value)
            {
                return cursor;
            }

            cursor = cursor.getNext();
        }

        return null;
    }
}

class Node
{
    private Node next, mad;
    private Integer value;

    public Node (Integer value)
    {
        this.value = value;
    }

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
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