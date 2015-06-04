// sum numbers represented as array
// set 186, r1, q1

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = new Integer[]{9,9,1};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }

        Integer[] array2 = new Integer[]{9};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }

        Summer summer = new Summer();
        LL.print(summer.sum(ll1, ll2));
    }
}

class Summer
{
    private Integer carry;

    public Node sum(LL ll1, LL ll2)
    {
        carry = 0;

        Node previousSum = null;
        if (ll1.getLength() > ll2.getLength())
        {
            previousSum = sumNodes(ll1.getHead(), ll2.getHead(), ll1.getLength() - ll2.getLength());
        }
        else
        {
            previousSum = sumNodes(ll2.getHead(), ll1.getHead(), ll2.getLength() - ll1.getLength());
        }

        while(carry != 0)
        {
            Node node = new Node(carry%10);
            carry = carry/10;

            node.setNext(previousSum);
            previousSum = node;
        }

        return previousSum;
    }

    private Node sumNodes(Node node1, Node node2, Integer depth)
    {
        if (node1 == null || node2 == null)
        {
            return null;
        }

        if (depth > 0)
        {
            Node previousSum  = sumNodes(node1.getNext(), node2, depth-1);
            Integer thisSum = node1.getValue() + carry;
            carry = thisSum/10;
            Node thisNode = new Node(thisSum % 10);
            thisNode.setNext(previousSum);

            return thisNode;
        }
        else
        {
            Node previousSum  = sumNodes(node1.getNext(), node2.getNext(), depth-1);
            Integer thisSum = node1.getValue() + node2.getValue() + carry;
            carry = thisSum/10;
            Node thisNode = new Node(thisSum % 10);
            thisNode.setNext(previousSum);

            return thisNode;
        }
    }
}

class LL
{
    private Integer length;
    private Node head;

    public LL()
    {
        head = null;
        length = 0;
    }

    public Integer getLength()
    {
        return length;
    }

    public static void print(Node head)
    {
        Node cursor = head;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println();
    }

    public Node getHead()
    {
        return head;
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

        ++length;
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value)
    {
        this.value = value;
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