// product of 2 linked list
// written, q2, set21

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array1 = new Integer[]{1,0,2};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insert(value);
        }
        LL.print(ll1.getHead());
        System.out.println();

        Integer[] array2 = new Integer[]{9,9,9};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insert(value);
        }
        LL.print(ll2.getHead());
        System.out.println();

        AddOneOff add = new AddOneOff();
        LL.print(add.add(ll1, ll2, 2));
        System.out.println();

        Multiply multiply = new Multiply();
        LL.print(multiply.act(ll1, ll2));

    }
}

class Multiply
{
    private Node head1, head2, result;
    private Integer carry = 0;

    public Node act(LL ll1, LL ll2)
    {
        head1 = ll1.getHead();
        head2 = ll2.getHead();
        result = null;
        carry = 0;

        Node endNode1 = getEnd(head1);
        Integer length1 = getLength(head1);
        Node previousProductNode = null;
        AddOneOff addOneOff = new AddOneOff();
        Integer off = 0;
        while (length1 > 0)
        {
            Node thisProductNode = multiply(endNode1, head2);
            if (previousProductNode != null)
            {
                Node node = addOneOff.add(previousProductNode, thisProductNode, off);
                previousProductNode = null;
                previousProductNode = node;
            }
            else
            {
                previousProductNode = thisProductNode;
            }

            endNode1 = endNode1.getPrevious();
            --length1;
            ++off;
        }

        return previousProductNode;
    }

    private Node multiply(Node node, Node llHead)
    {
        Node reverseCursorll = getEnd(llHead);
        Integer lengthll = getLength(llHead);
        Node thisResult = null;
        Integer carry = 0;
        while(lengthll > 0)
        {
            Integer value = carry + (node.getValue() * reverseCursorll.getValue());
            carry = value / 10;

            if (thisResult == null)
            {
                thisResult = new Node(value%10);
            }
            else
            {
                Node thisNode = new Node(value%10);
                thisResult.setPrevious(thisNode);
                thisNode.setNext(thisResult);
                thisResult = thisResult.getPrevious();
            }

            --lengthll;
        }

        if (carry != 0)
        {
            Node thisNode = new Node(carry);
            thisResult.setPrevious(thisNode);
            thisNode.setNext(thisResult);
            thisResult = thisResult.getPrevious();
        }

        return thisResult;

    }

    private Integer getLength(Node node)
    {
        Integer length = 0;
        Node cursor = node;

        while(cursor != null)
        {
            cursor = cursor.getNext();
            ++length;
        }

        return length;
    }

    private Node getEnd(Node node)
    {
        Node cursor = node;
        while(cursor.getNext() != null)
        {
            cursor  = cursor.getNext();
        }

        return cursor;
    }

}

class AddOneOff
{
    private Node head1, head2, result;
    private Integer carry;


    public Node add(LL ll1, LL ll2, Integer off)
    {
        return add(ll1.getHead(), ll2.getHead(), off);
    }

    public Node add(Node node1, Node node2, Integer off)
    {
        head1 = node1;
        head2 = node2;
        result = null;
        carry = 0;

        Node end1 = getEnd(head1);
        Integer length1 = getLength(head1);
        while(off > 0)
        {
            addToResult(end1.getValue());
            end1 = end1.getPrevious();
            --length1;
            --off;
        }

        Node end2 = getEnd(head2);
        Integer length2 = getLength(head2);

        while (length1 > 0
                && length2 > 0)
        {
            Integer value = carry + end1.getValue() + end2.getValue();
            carry = value / 10;
            addToResult(value % 10);

            end1 = end1.getPrevious();
            --length1;
            end2 = end2.getPrevious();
            --length2;
        }

        while (length1 > 0)
        {
            Integer value = carry + end1.getValue();
            carry = value / 10;
            addToResult(value % 10);

            end1 = end1.getPrevious();
            --length1;
        }

        while (length2 > 0)
        {
            Integer value = carry + end2.getValue();
            carry = value / 10;
            addToResult(value % 10);

            end2 = end2.getPrevious();
            --length2;
        }

        if (carry != 0)
        {
            addToResult(carry);
        }

        return result;
    }

    private Integer getLength(Node node)
    {
        Integer length = 0;
        Node cursor = node;

        while(cursor != null)
        {
            cursor = cursor.getNext();
            ++length;
        }

        return length;
    }

    private void addToResult(int value)
    {
        if (result == null)
        {
            result = new Node(value);
        }
        else
        {
            Node node = new Node(value);
            result.setPrevious(node);
            node.setNext(result);
            result = node;
        }
    }

    private Node getEnd(Node node)
    {
        Node cursor = node;
        while(cursor.getNext() != null)
        {
            cursor  = cursor.getNext();
        }

        return cursor;
    }

}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
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

            Node node = new Node(value);
            cursor.setNext(node);
            node.setPrevious(cursor);
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
    private Node previous;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.next = null;;
        this.previous = null;
    }

    public Node getPrevious()
    {
        return previous;
    }

    public void setPrevious(Node previous)
    {
        this.previous = previous;
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