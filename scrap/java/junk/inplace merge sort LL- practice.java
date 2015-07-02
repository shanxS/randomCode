public class Main
{
    public static void main(String[] er)
    {
        Integer[] a1 = {2,3,1,5,9,7,8};//{1,9,2,8,3,7,4};
        LL ll = new LL();
        for (Integer i : a1)
        {
            ll.insert(i);
        }

        MergeSorter ms = new MergeSorter();
        LL.print(ms.sort(ll));
    }
}

class MergeSorter
{
    public Node sort(LL ll)
    {
        return divide(ll.getHead(), ll.getSize());
    }

    private Node divide(Node node, Integer size)
    {
        if (size == 1)
        {
            return node;
        }

        Integer mid = size/2;
        Node nextHeadParent = getNodeAt(node, mid);
        Node nextHead = nextHeadParent.getNext();
        nextHeadParent.setNext(null);

        Node sortNode1 = divide(node, mid);
        Node sortNode2 = divide(nextHead, size - mid);

        return merge(sortNode1, mid, sortNode2, size - mid);
    }

    private Node merge(Node head1, Integer size1, Node head2, Integer size2)
    {
        Node sortHead = head1.getValue() < head2.getValue() ? head1 : head2;

        while (size1 > 0 && size2 > 0)
        {
            if (head1.getValue() < head2.getValue())
            {
                Node previousHead1 = head1;
                while (size1 > 0 && head1.getValue() < head2.getValue())
                {
                    previousHead1 = head1;
                    head1 = head1.getNext();
                    --size1;
                }

                if (size1 == 0)
                {
                    previousHead1.setNext(head2);
                }
                else
                {
                    Node nextHead1 = previousHead1.getNext();
                    previousHead1.setNext(head2);

                    head2 = head2.getNext();
                    --size2;

                    previousHead1.getNext().setNext(nextHead1);
                    head1 = previousHead1.getNext();
                    ++size1;
                }
            }
            else if (head1.getValue() > head2.getValue())
            {
                Node previousHead2 = head2;
                while (size2 > 0 && head1.getValue() > head2.getValue())
                {
                    previousHead2 = head2;
                    head2 = head2.getNext();
                    --size2;
                }

                if (size2 == 0)
                {
                    previousHead2.setNext(head1);
                }
                else
                {
                    Node nextHead2 = previousHead2.getNext();
                    previousHead2.setNext(head1);

                    head1 = head1.getNext();
                    --size1;

                    previousHead2.getNext().setNext(nextHead2);
                    head2 = previousHead2.getNext();
                    ++size2;
                }
            }
        }

        return sortHead;
    }

    private Node getNodeAt(Node node, Integer n)
    {
        while (n > 1)
        {
            node = node.getNext();
            --n;
        }

        return node;
    }
}

class LL
{
    private Node head;
    private Integer size;

    public LL()
    {
        head = null;
        size = 0;
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
            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }

        ++size;
    }

    public Node getHead()
    {
        return head;
    }

    public Integer getSize()
    {
        return size;
    }

    public static void print(Node node)
    {
        while (node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}

class Node
{
    private Node next;
    private Integer value;

    public Node (Integer value)
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