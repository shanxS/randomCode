// inplace merge sort LL

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

        MergeSort ms = new MergeSort();
        LL.print(ms.sort(ll.getHead()));
    }
}

class MergeSort
{
    public Node sort(Node head)
    {
        Integer length = getLength(head);
        return divide(head, length);
    }

    private Integer getLength(Node head)
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

    private Node divide(Node node, Integer length)
    {
        if (length == 1)
        {
            return node;
        }

        Integer mid = length/2;
        Node firstStartNode = node;
        Node secondStartNodeParent = getNthNode (node, (mid%2==0) ? mid : length-mid-1);
        Node secondStartNode = secondStartNodeParent.getNext();
        secondStartNodeParent.setNext(null);

        firstStartNode = divide(firstStartNode, mid);
        secondStartNode = divide(secondStartNode, length-mid);

        return merge(firstStartNode, secondStartNode);
    }

    private Node merge(Node firstStartNode, Node secondStartNode)
    {
        Node returnHead = (firstStartNode.getValue() < secondStartNode.getValue()) ? firstStartNode : secondStartNode;

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
                    firstCursor = firstCursor.getNext();
                }

                firstCursor = firstCursorCache.getNext();

                firstCursorCache.setNext(secondCursor);
            }
            else if (firstCursor.getValue() > secondCursor.getValue())
            {
                Node secondCursorCache = secondCursor;
                while (secondCursor != null && firstCursor.getValue() > secondCursor.getValue())
                {
                    secondCursorCache = secondCursor;
                    secondCursor = secondCursor.getNext();
                }

                secondCursor = secondCursorCache.getNext();

                secondCursorCache.setNext(firstCursor);
            }
        }

        return returnHead;
    }

    private Node getNthNode(Node node, Integer n)
    {
        while (n > 1 && node != null)
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