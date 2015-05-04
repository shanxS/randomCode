// summing 2 link list of Integers
// r1, q2 set10 will be solved along same lines

public class Main
{
    public static void main(String[] args)
    {
        LL l1 = new LL();
        l1.insert(1);
        l1.insert(1);
        l1.insert(1);
        l1.insert(1);
        LL.print(l1.getHead());

        LL l2 = new LL();
        l2.insert(1);
        l2.insert(9);
        l2.insert(9);
        l2.insert(9);
        l2.insert(9);
        LL.print(l2.getHead());

        LLSummer llSummer = new LLSummer(l1, l2);
        llSummer.sum();
    }
}

class LLSummer
{
    private Node head1, head2;
    private Node result;
    private Integer carry;

    public LLSummer(LL l1, LL l2)
    {
        head1 = l1.getHead();
        head2 = l2.getHead();
        result = null;
        carry = 0;
    }

    public void sum()
    {
        Integer length1 = getLength(head1);
        Integer length2 = getLength(head2);

        if (length1 > length2)
        {
            addList(head1, length1 - length2, length2, head2);
        }
        else if (length1 < length2)
        {
            addList(head2, length2 - length1, length2, head1);
        }
        else
        {
            addList(head1, 0, length2, head2);
        }

        if (carry != 0)
        {
            addDiscreteSum(0);
        }

        System.out.println("sum: ");
        LL.print(result);
    }

    private void addList(Node head1, int startInDepth, int finalDepth, Node head2)
    {
        if (startInDepth > 1)
        {
            addList(head1.getNext(), startInDepth-1, finalDepth, head2);
        }
        else if (finalDepth > 1)
        {
            addList(head1.getNext(), 0, finalDepth-1, head2.getNext());
        }

        if (startInDepth == 1)
        {
            Integer discreteSum = head1.getValue() + head2.getValue();
            addDiscreteSum(discreteSum);
        }
        else
        {
            addDiscreteSum(head1.getValue());
        }
    }

    private void addDiscreteSum(Integer discreteSum)
    {
        Integer nodeValue = (discreteSum + carry)%10;
        carry = (discreteSum + carry)/10;

        if (result == null)
        {
            result = new Node(nodeValue);
        }
        else
        {
            Node node = new Node(nodeValue);
            node.setNext(result);
            result = node;
        }
    }

    private Integer getLength(Node node)
    {
        Integer length = 0;

        while(node != null)
        {
            ++length;
            node = node.getNext();
        }

        return length;
    }
}

class LL
{
    private Node head;

    public LL()
    {
        head = null;
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