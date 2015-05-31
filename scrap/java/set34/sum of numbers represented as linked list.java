// sum of numbers represented as linked list
// online, q1, set34

public class Main
{
    public static void main(String[] er)
    {
        Integer[] number1 = new Integer[]{9,9};
        LL ll1 = new LL();
        for (Integer value : number1)
        {
            ll1.insert(value);
        }

        Integer[] number2 = new Integer[]{8};
        LL ll2 = new LL();
        for (Integer value : number2)
        {
            ll2.insert(value);
        }

        Summer summer = new Summer();
        Node sum = summer.sum(ll1, ll2);
        LL.print(sum);
    }
}

class Summer
{
    private Integer carry;

    public Node sum(LL ll1, LL ll2)
    {
        carry = 0;
        Node postNodes = null;
        if (ll1.getLength() > ll2.getLength())
        {
            postNodes = doSum(ll1.getHead(), ll2.getHead(), ll1.getLength() - ll2.getLength());
        }
        else
        {
            postNodes = doSum(ll2.getHead(), ll1.getHead(), ll2.getLength() - ll1.getLength());
        }


        if (carry != 0)
        {
            Node node = new Node(carry);
            node.setNext(postNodes);
            return node;
        }
        else
        {
            return postNodes;
        }
    }

    private Node doSum(Node node1, Node node2, Integer depth)
    {
        if (node1 == null || node2 == null)
        {
            return null;
        }

        Node postNodes = null;
        if (depth != 0)
        {
            postNodes = doSum(node1.getNext(), node2, depth-1);
        }
        else
        {
            postNodes = doSum(node1.getNext(), node2.getNext(), depth);
        }

        Node result = null;
        if (depth <= 0)
        {
            Integer thisSum = node1.getValue() + node2.getValue() + carry;
            carry = thisSum / 10;
            result = new Node(thisSum % 10);
        }
        else
        {
            Integer thisSum = node1.getValue() + carry;
            carry = thisSum / 10;
            result = new Node(thisSum % 10);
        }

        result.setNext(postNodes);

        return result;
    }
}

class LL
{
    private Node head;
    private Integer length;

    public LL()
    {
        head = null;
        length = 0;
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

    public Integer getLength()
    {
        return length;
    }

    public Node getHead()
    {
        return head;
    }

    public static void print(Node head)
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