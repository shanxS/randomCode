// flatten another ll

public class Main
{
    public static void main(String[] er)
    {
        Node head = makeStructre();
        flatten(head);

        LL.print(head);
    }

    private static void flatten(Node head)
    {
        Node endCursor = head;
        while (endCursor.getNext() != null)
        {
            endCursor = endCursor.getNext();
        }

        Node cursor = head;
        while (cursor != null)
        {
            endCursor = merge(cursor.getDown(), endCursor);
            cursor = cursor.getNext();
        }
    }

    private static Node merge(Node downNode, Node horizNode)
    {
        if (downNode != null)
        {
            horizNode.setNext(downNode);
            while(horizNode.getNext() != null)
            {
                horizNode = horizNode.getNext();
            }


        }

        return horizNode;
    }

    public static Node makeStructre()
    {
        LL a = new LL();
        a.insert(19); a.insert(15);
        LL b = new LL();
        b.insert(9); b.insert(8);
        b.getHead().setDown(a.getHead());
        LL c = new LL();
        c.insert(17); c.insert(6);
        c.getHead().setDown(b.getHead());

        LL d = new LL();
        d.insert(3);
        LL e = new LL();
        e.insert(16);
        e.getHead().setDown(d.getHead());
        Node n13 = new Node(13);
        n13.setDown(e.getHead());

        Node n2 = new Node(2);
        Node n20 = new Node(20);
        n20.setDown(n2);
        Node n4 = new Node(4);
        n4.setNext(n20);
        n20.setNext(n13);

        Node n11 = new Node(11);
        Node n7 = new Node(7);
        n7.setDown(c.getHead());
        n7.setNext(n11);

        LL h = new LL();
        h.insert(10); h.insert(5);
        h.insert(12);
        Node hCursor = h.getHead();
        while(hCursor.getNext() != null)
        {
            hCursor = hCursor.getNext();
        }
        hCursor.setNext(n7);
        hCursor.setDown(n4);

        return h.getHead();
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

    public static void print(Node node)
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
    private Node next, down;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Node getDown()
    {
        return down;
    }

    public void setDown(Node down)
    {
        this.down = down;
    }

    public void insert(Integer value)
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