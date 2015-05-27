// given linked list of char - test if palindrome
// r1, q1, set30

public class Main
{
    public static void main(String[] er)
    {
        //String str = "aba";
        //String str = "abba";
        //String str = "abc";
        //String str = "abna";
        //String str = "aa";
        //String str = "a";
        String str = "ca";
        LL ll = new LL();
        for (Character c : str.toCharArray())
        {
            ll.insert(c);
        }

        Node head = ll.getHead();
        Node cursor = head;
        Integer length = 0;
        while(cursor != null)
        {
            ++length;
            cursor = cursor.getNext();
        }
        --length;

        Node midNode;
        if (length%2 == 0)
        {
            midNode = ll.reverseTo(length/2).getNext();
        }
        else
        {
            midNode = ll.reverseTo((length+1)/2);
        }

        head = ll.getHead();
        cursor = head;
        while(midNode != null
                && midNode.getData() == cursor.getData())
        {
            midNode = midNode.getNext();
            cursor = cursor.getNext();
        }

        if (midNode != null)
        {
            System.out.print("not plain");
        }
        else
        {
            System.out.print("plain");
        }
    }
}

class LL
{
    private Node head;

    public LL()
    {
        this.head = null;
    }

    public void insert(Character c)
    {
        if (head == null)
        {
            head = new Node(c);
        }
        else
        {
            Node cursor = head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(c));
        }
    }

    public Node reverseTo(Integer mid)
    {
        if (head == null)
        {
            return null;
        }
        Node current = head;

        if (head.getNext() == null)
        {
            return head;
        }
        Node next = head.getNext();

        Node previous = null;

        Integer count = 0;
        while (next != null && count<mid)
        {
            current.setNext(previous);
            previous = current;
            current = next;
            next = next.getNext();

            ++count;
        }

        head.setNext(current);
        head = previous;

        return current;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Character data;
    private Node next;

    public Node(Character data)
    {
        this.data = data;
        this.next = null;
    }

    public Character getData()
    {
        return data;
    }

    public void setData(Character data)
    {
        this.data = data;
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