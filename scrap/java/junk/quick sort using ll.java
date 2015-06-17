// quick sort using ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2,1,9,2,8,3,3,7};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LLQuickSort qs = new LLQuickSort();
        LL.print(qs.sort(ll.getHead()));
    }
}

class LLQuickSort
{
    Node globalHead;

    public Node sort(Node head)
    {
        Node endNode = getEndNode(head);
        globalHead = head;
        sort(head, endNode, true);

        return globalHead;
    }

    private void sort(Node startNode, Node endNode, Boolean setGlobalHead)
    {
        if (startNode == endNode || endNode == null || startNode == null)
        {
            return;
        }

        Node cursor = startNode;
        Node cursorParent = null;
        Node newHead = startNode;

        while (cursor != endNode)
        {
            if (cursor.getValue() > endNode.getValue())
            {
                if (cursor == newHead)
                {
                    newHead = cursor.getNext();
                }

                if (cursor.getNext() == endNode)
                {
                    cursor.setNext(endNode.getNext());
                    endNode.setNext(cursor);

                    cursor = endNode;
                    if (cursorParent != null)
                    {
                        cursorParent.setNext(endNode);
                    }
                }
                else
                {
                    Node nextCursor = cursor.getNext();
                    cursor.setNext(endNode.getNext());
                    endNode.setNext(cursor);

                    cursor = nextCursor;
                    if (cursorParent != null)
                    {
                        cursorParent.setNext(nextCursor);
                    }
                }
            }
            else
            {
                cursorParent = cursor;
                cursor = cursor.getNext();
            }
        }

        if (setGlobalHead)
        {
            globalHead = newHead;
        }
        sort(newHead, cursorParent, setGlobalHead);
        sort(cursor.getNext(), getEndNode(cursor.getNext()), false);
    }

    private Node getEndNode(Node node)
    {
        while (node != null && node.getNext() != null)
        {
            node = node.getNext();
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
        while(node != null)
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