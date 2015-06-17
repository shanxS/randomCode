// Given a linked list of line segments - remove middle points

public class Main
{
    public static void main(String[] er)
    {
        LL ll = new LL();
//        ll.insert(0, 10);
//        ll.insert(1, 10);
//        ll.insert(5, 10);
//        ll.insert(7, 10);
//        ll.insert(7, 5);
//        ll.insert(20, 5);
//        ll.insert(40, 5);
        ll.insert(2,3);
        ll.insert(4,3);
        ll.insert(6,3);
        ll.insert(10,3);
        ll.insert(12,3);

        PointRemover pr = new PointRemover();
        pr.remove(ll.getHead());
        ll.print();
    }
}

class PointRemover
{
    private enum Status {horiz, vertical, none}

    public void remove(Node head)
    {
        Node cursor = head;

        while(cursor != null)
        {
            Status status = findStatus(cursor);
            if (status != Status.none)
            {
                searchAndDestroy(cursor, status);
            }

            cursor = cursor.getNext();
        }
    }

    private void searchAndDestroy(Node cursor, Status status)
    {
        Node endNode = cursor;
        while (endNode.getNext() != null)
        {
            if (status == Status.horiz)
            {
                if (endNode.getY() == endNode.getNext().getY())
                {
                    endNode = endNode.getNext();
                }
                else
                {
                    break;
                }
            }
            else if (status == Status.vertical)
            {
                if (endNode.getX() == endNode.getNext().getX())
                {
                    endNode = endNode.getNext();
                }
                else
                {
                    break;
                }
            }
        }

        cursor.setNext(endNode);
    }

    private Status findStatus(Node cursor)
    {
        if (cursor == null || cursor.getNext() == null)
        {
            return Status.none;
        }
        else if (cursor.getX() == cursor.getNext().getX())
        {
            return Status.vertical;
        }
        else if (cursor.getY() == cursor.getNext().getY())
        {
            return Status.horiz;
        }

        return Status.none;
    }
}

class LL
{
    private Node head;

    public LL()
    {
        head = null;
    }

    public void insert(Integer x, Integer y)
    {
        if (head == null)
        {
            head = new Node(x, y);
        }
        else
        {
            Node cursor = head;
            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(x, y));
        }
    }

    public void print()
    {
        Node cursor = head;

        while(cursor != null)
        {
            System.out.println(cursor.getX() + ", " + cursor.getY());
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
    private Integer x, y;
    private Node next;

    public Node(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
    }

    public Integer getX()
    {
        return x;
    }

    public void setX(Integer x)
    {
        this.x = x;
    }

    public Integer getY()
    {
        return y;
    }

    public void setY(Integer y)
    {
        this.y = y;
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
