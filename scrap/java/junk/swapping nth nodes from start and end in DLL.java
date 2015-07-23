// swapping nth nodes from start and end in DLL

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {1,2,3,4,5};
        Integer[] ar = {1,2};

        DLLUtil dllUtil = new DLLUtil();
        Node head = dllUtil.make(ar);
        NodeSwapper ns = new NodeSwapper();
//        Node nHead = ns.swapNth(head, 5);
//        Node nHead = ns.swapNth(head, 4);
//        Node nHead = ns.swapNth(head, 3);
        Node nHead = ns.swapNth(head, 2);
//        Node nHead = ns.swapNth(head, 1);


        dllUtil.print(nHead);
    }
}

class NodeSwapper
{
    private Node firstParent, lastParent, head;
    private Integer firstParentN, lastParentN;

    public Node swapNth(Node head, final Integer NCache)
    {
        if (NCache < 2)
        {
            System.out.println("too small N");
            return head;
        }


        this.firstParentN = NCache-1;
        this.lastParentN = NCache + 1;
        this.head = head;
        locateParents();

        if (firstParentN >= -1)
        {
            System.out.println("too small LL");
            return head;
        }
        else if (firstParent == lastParent)
        {
            System.out.println("nothing ot do nodes are same");
            return head;
        }
        else if (firstParent.getNext() == lastParent
                || lastParent.getNext() == firstParent)
        {
            if (firstParent.getNext() == lastParent)
            {
                swapNeighbours(firstParent, lastParent);
            }
            else
            {
                swapNeighbours(lastParent, firstParent);
            }

            return this.head;
        }
        else
        {
            swapNode(firstParent, lastParent);
            return this.head;
        }

    }

    private void swapNeighbours(Node firstParent, Node lastParent)
    {
        Node lastNode = lastParent.getNext();
        Node lastNext = lastNode.getNext();

        firstParent.setNext(lastNode);
        lastNode.setPrev(firstParent);
        lastNode.setNext(lastParent);
        lastParent.setPrev(lastNode);

        lastParent.setNext(lastNext);
        if (lastNext != null)
        {
            lastNext.setPrev(lastParent);
        }
    }

    private void swapNode(Node firstParent, Node lastParent)
    {
        Node firstNode = firstParent.getNext();
        Node firstNext = firstParent.getNext().getNext();
        Node lastNode = lastParent.getNext();
        Node lastNext = lastParent.getNext().getNext();

        firstParent.setNext(lastNode);
        lastNode.setPrev(firstParent);
        lastNode.setNext(firstNext);
        firstNext.setPrev(lastNode);

        lastParent.setNext(firstNode);
        firstNode.setPrev(lastParent);
        firstNode.setNext(lastNext);
        if (lastNext != null)
        {
            lastNext.setPrev(firstNode);
        }

    }

    private void locateParents()
    {
        Node cursor = head;

        while(cursor != null)
        {
            if (firstParentN == 1)
            {
                firstParent = cursor;
            }

            if (lastParentN == 1)
            {
                lastParent = head;
            }
            else if (lastParentN < 1)
            {
                lastParent = lastParent.getNext();
            }

            cursor = cursor.getNext();
            --firstParentN;
            --lastParentN;
        }
    }
}

class DLLUtil
{
    public Node make(Integer[] ar)
    {
        Node head = new Node(ar[0]);
        Node cursor = head;

        for(Integer i=1; i<ar.length; ++i)
        {
            Node nextNode = new Node(ar[i]);
            cursor.setNext(nextNode);
            nextNode.setPrev(cursor);

            cursor = nextNode;
        }

        return head;
    }

    public void print(Node head)
    {
        Node cursor = head;
        while (cursor.getNext() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println(cursor.getValue() + " ");

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getPrev();
        }
        System.out.println();
    }
}

class Node
{
    private Node next, prev;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getPrev()
    {
        return prev;
    }

    public void setPrev(Node prev)
    {
        this.prev = prev;
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