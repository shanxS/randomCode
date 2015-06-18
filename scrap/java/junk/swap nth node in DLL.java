// swap nth node in DLL

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1, 2, 3, 4, 5, 6};
        Integer[] array = {1,2};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }
        dll.print();

        NodeSwapper ns = new NodeSwapper();
        DLL.print(ns.swap(dll.getHead(), 1));
    }
}

class NodeSwapper
{
    public Node swap(Node head, Integer n)
    {
        if (n < 1)
        {
            return head;
        }
        else if (n == 1)
        {
            Node lastParent = null;
            Node cursor = head;
            while(cursor.getNext() != null)
            {
                lastParent = cursor;
                cursor = cursor.getNext();
            }

            if (cursor == head)
            {
                return null;
            }
            else
            {
                if (head == lastParent)
                {
                    Node lastNode = lastParent.getNext();
                    head.setPre(lastNode);
                    head.setNext(null);
                    lastNode.setNext(head);
                    lastNode.setPre(null);

                    return lastNode;
                }
                else
                {
                    Node lastNode = lastParent.getNext();
                    Node headNextNode = head.getNext();

                    head.setNext(null);
                    head.setPre(lastParent);
                    lastParent.setNext(head);

                    lastNode.setPre(null);
                    lastNode.setNext(headNextNode);
                    headNextNode.setPre(lastNode);

                    return lastNode;
                }
            }
        }
        else
        {
            final Integer nCache = n;

            Node startNodeParent = head;
            Node endNodeParent = head;
            Node cursor = head;
            while (cursor.getNext() != null)
            {
                if (n == 2)
                {
                    startNodeParent = cursor;
                }
                else if (n == 1)
                {
                    endNodeParent = head;
                }
                else if (n < 1)
                {
                    endNodeParent = endNodeParent.getNext();
                }

                cursor = cursor.getNext();
                --n;
            }

            if ( n <= -1*(nCache-1))
            {
                if (n == -1*(nCache-1))
                {
                    Node endNode = endNodeParent.getNext();

                    Node endNodeNext = endNode.getNext();
                    endNode.setNext(endNodeParent);
                    endNode.setPre(startNodeParent);
                    endNodeParent.setNext(endNodeNext);
                    endNodeParent.setPre(endNode);
                }
                else
                {
                    Node endNode = endNodeParent.getNext();
                    Node startNode = startNodeParent.getNext();

                    Node endNodeNext = endNode.getNext();
                    endNode.setNext(startNode.getNext());
                    endNode.getNext().setPre(endNode);

                    startNode.setNext(endNodeNext);
                    if (endNodeNext != null)
                    {
                        endNodeNext.setPre(startNode);
                    }

                    startNodeParent.setNext(endNode);
                    endNode.setPre(startNodeParent);

                    endNodeParent.setNext(startNode);
                    startNode.setPre(endNodeParent);
                }
            }
        }

        return head;
    }
}

class DLL
{
    private Node head;

    public DLL()
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

            Node newNode = new Node(value);
            cursor.setNext(newNode);
            newNode.setPre(cursor);
        }
    }

    public void print()
    {
        DLL.print(head);
    }

    public static void print(Node head)
    {
        Node cursor = head;
        while (cursor.getNext() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println(cursor.getValue());

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getPre();
        }
        System.out.println("-------------------------------");
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node pre, next;
    private Integer value;

    public Node (Integer value)
    {
        this.value = value;
    }

    public Node getPre()
    {
        return pre;
    }

    public void setPre(Node pre)
    {
        this.pre = pre;
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