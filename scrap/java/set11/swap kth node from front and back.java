// swap kth node from front and back
// technical, q3, set11

public class Main
{

    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        LL ll = new LL();
        for (Integer i : array)
        {
            ll.insert(i);
        }

        NodeSwapper ns = new NodeSwapper(ll);
        //ns.swap(2);
        //ns.swap(3);
        ns.swap(4);
        LL.print(ns.getHead());
    }
}

class NodeSwapper
{
    private Node head, firstParent, secondParent;

    public NodeSwapper(LL ll)
    {
        this.head = ll.getHead();
    }

    public void swap(Integer k)
    {
        if (!setParents(k-1))
        {
            System.out.println("cant set parents");
        }

        Node node1 = firstParent.getNext();
        Node node2 = secondParent.getNext();

        firstParent.setNext(node2);
        secondParent.setNext(node1);

        Node tmp = node1.getNext();
        node1.setNext(node2.getNext());
        node2.setNext(tmp);
    }

    private Boolean setParents(Integer n)
    {
        firstParent = null;
        secondParent = null;
        Node lastNode = head;

        while (lastNode.getNext() != null)
        {
            if (n == 0)
            {
                firstParent = lastNode;
            }

            if (n == -2)
            {
                secondParent = head;
            }

            if (secondParent != null)
            {
                secondParent = secondParent.getNext();
            }

            --n;
            lastNode = lastNode.getNext();
        }

        if (firstParent == null || secondParent == null)
        {
            return false;
        }

        return true;
    }

    public Node getHead()
    {
        return head;
    }
}

class LL
{
    private Node head;

    public LL ()
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
            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;
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
}