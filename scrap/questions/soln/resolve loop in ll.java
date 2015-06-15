// resolve loop in ll

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1,2,3,4,5};
        Integer[] array = {1};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        ll.makeLoop(1);

        ResolveLoop rl = new ResolveLoop();
        rl.resolve(ll.getHead());

        LL.print(ll.getHead());

    }

}

class ResolveLoop
{
    public void resolve(Node head)
    {
        Integer loopLenght = getLoopLength(head);

        Node leadNode = head;
        while (loopLenght > 1)
        {
            leadNode = leadNode.getNext();
            --loopLenght;
        }

        Node lagNode = head;
        while (leadNode.getNext().getValue() != lagNode.getValue())
        {
            lagNode = lagNode.getNext();
            leadNode = leadNode.getNext();
        }

        leadNode.setNext(null);
    }

    private Integer getLoopLength(Node head)
    {
        Node fastNode = head.getNext();
        Node slowNode = head;

        while(fastNode.getValue() != slowNode.getValue())
        {
            slowNode = slowNode.getNext();
            fastNode = fastNode.getNext().getNext();
        }

        Integer length = 1;
        slowNode = slowNode.getNext();
        while(fastNode.getValue() != slowNode.getValue())
        {
            slowNode = slowNode.getNext();
            ++length;
        }

        return length;
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