// reverse k nodes of linked list
// written test q2, set7, amazon

public class Main {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);
        ll.insert(7);
        ll.insert(8);

        ll.print();

        LinkedListReverser llr = new LinkedListReverser(ll);
        llr.reverse(2);
        ll.print();
    }
}

class LinkedListReverser
{
    private LinkedList linkedList;
    private Integer partialCount;

    public LinkedListReverser(LinkedList ll)
    {
        linkedList = ll;
    }

    public void reverse(Integer partialCount) {
        this.partialCount = partialCount;
        Node head = linkedList.getHead();

        head = reverse(head);
        linkedList.setHead(head);
    }

    private Node reverse(Node node) {
        if (node == null)
        {
            return null;
        }
        Node current = node;

        Node next = current.getNext();
        if (next == null)
        {
            return current;
        }

        Node nextNext = next.getNext();
        if (nextNext == null)
        {
            current.setNext(null);
            next.setNext(current);
            return next;
        }

        Integer count = partialCount;
        while(count > 1 && next != null)
        {
            next.setNext(current);
            current = next;
            next = nextNext;

            if (nextNext != null) {
                nextNext = nextNext.getNext();
            }

            --count;
        }

        if (next != null)
        {
            node.setNext(reverse(next));
        }
        else
        {
            node.setNext(null);
        }

        return current;
    }
}

class LinkedList
{
    private Node head;

    public LinkedList()
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
        Node cursor = head;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }

        System.out.println();
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}