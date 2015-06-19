// Point to next higher value node in a linked list with an arbitrary pointer

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {5,1,3,4};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        SortMad ms = new SortMad();
        Node newHead = ms.sort(ll.getHead());
        LL.print(newHead);

        System.out.println("-------------------");
        while (newHead != null)
        {
            System.out.print(newHead.getValue() + " ");
            newHead = newHead.getMad();
        }
    }
}

class SortMad
{
    public Node sort(Node head)
    {
        Integer length = getLenght(head);
        return divide(head, length);
    }

    private Node divide(Node node, Integer length)
    {
        if (length == 1)
        {
            return node;
        }

        Node firstStartNode = node;
        Integer mid = length/2;
        Node secondStartNode = getNodeAt(node, (length%2==0)?(mid+1) : (length-mid));

        firstStartNode = divide(node, mid);
        secondStartNode = divide(secondStartNode, length-mid);

        return merge(firstStartNode, secondStartNode);
    }

    private Node getNodeAt(Node node, Integer n)
    {
        while (n > 1 && node != null)
        {
            --n;
            node = node.getNext();
        }

        return node;
    }

    private Node merge(Node firstStartNode, Node secondStartNode)
    {
        Node sortedHead = (firstStartNode.getValue() < secondStartNode.getValue()) ? firstStartNode : secondStartNode;

        Node firstCursor = firstStartNode;
        Node secondCursor = secondStartNode;

        while (firstCursor != null && secondCursor != null)
        {
            if (firstCursor.getValue() < secondCursor.getValue())
            {
                Node firstCursorCache = firstCursor;
                while (firstCursor != null && firstCursor.getValue() < secondCursor.getValue())
                {
                    firstCursorCache = firstCursor;
                    firstCursor = firstCursor.getMad();
                }

                firstCursorCache.setMad(secondCursor);
            }
            else if (firstCursor.getValue() > secondCursor.getValue())
            {
                Node secondCursorCache = secondCursor;
                while (secondCursor != null && firstCursor.getValue() > secondCursor.getValue())
                {
                    secondCursorCache = secondCursor;
                    secondCursor = secondCursor.getMad();
                }

                secondCursorCache.setMad(firstCursor);
            }
        }

        return sortedHead;
    }

    private Integer getLenght(Node head)
    {
        Node cursor = head;
        Integer lenght = 0;

        while (cursor != null)
        {
            ++lenght;
            cursor = cursor.getNext();
        }

        return lenght;
    }
}

class LL
{
    private Node head;

    public LL()
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
            while (cursor.getNext() != null)
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

    public static void print(Node node)
    {
        while (node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getNext();
        }
        System.out.println();
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node next, mad;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
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