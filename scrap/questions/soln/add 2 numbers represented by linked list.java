// add 2 numbers represented by linked list

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {9,9,9};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();


        Integer[] array2 = {1};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }
        LL.print(ll2.getHead());
        System.out.println();


        Adder gr = new Adder();
        LL.print(gr.add(ll2.getHead(), ll1.getHead()));

    }

}

class Adder
{
    Integer carry;
    public Node add(Node head1, Node head2)
    {
        carry = 0;
        Integer length1 = getLength(head1);
        Integer length2 = getLength(head2);

        Node returnHead;
        if (length1 > length2)
        {
            returnHead = head1;
            addFor(head1, head2, length1-length2);
        }
        else
        {
            returnHead = head2;
            addFor(head2, head1, length2-length1);
        }



        if (carry > 0)
        {
            Node node = new Node(carry);
            node.setNext(returnHead);
            returnHead = node;
        }

        return returnHead;
    }

    private void addFor(Node node1, Node node2, Integer depth)
    {
        if (node1 == null && node2 == null)
        {
            return;
        }
        else if (depth > 0)
        {
            addFor(node1.getNext(), node2, depth-1);

            Integer thisValue = node1.getValue() + carry;
            carry = thisValue/10;
            node1.setValue(thisValue % 10);
        }
        else if (depth == 0)
        {
            addFor(node1.getNext(), node2.getNext(), 0);

            Integer thisValue = node1.getValue() + node2.getValue() + carry;
            carry = thisValue/10;
            node1.setValue(thisValue % 10);
        }


    }

    private Integer getLength(Node node)
    {
        Integer count = 0;
        while (node != null)
        {
            node = node.getNext();
            ++count;
        }

        return count;
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