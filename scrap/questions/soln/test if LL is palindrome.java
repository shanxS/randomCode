// test if LL is palindrome

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,4,3,2,2};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        TestPalindrome rl = new TestPalindrome();
        System.out.print(rl.test(ll.getHead()));

    }

}

class TestPalindrome
{

    public Boolean test(Node head)
    {
        Node lastStartNode = fetchLastStartNode(head);
        Node startNode = head;

        Node reverseCursor = reverse(lastStartNode);

        while(reverseCursor != null && startNode.getValue() == reverseCursor.getValue())
        {
            reverseCursor = reverseCursor.getNext();
            startNode = startNode.getNext();
        }

        return reverseCursor == null;
    }

    private Node reverse(Node node)
    {
        Node current = node;
        Node previoud = null;
        Node next = current.getNext();

        while(next != null)
        {
            current.setNext(previoud);
            previoud = current;
            current = next;

            next = next.getNext();
        }
        current.setNext(previoud);

        return current;
    }

    private Node fetchLastStartNode(Node node)
    {
        Integer length = 0;
        Node midNode = node;
        Node fastNode = node;

        while(fastNode != null && fastNode.getNext() != null)
        {
            midNode = midNode.getNext();
            fastNode = fastNode.getNext().getNext();
            ++length;
        }

        if (fastNode != null)
        {
            midNode = midNode.getNext();
        }

        return midNode;
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