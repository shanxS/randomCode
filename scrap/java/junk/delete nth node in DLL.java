// delete nth node in DLL

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }
        dll.print();

        DLLOps dllOps = new DLLOps();
        DLL.print(dllOps.deleteNth(dll.getHead(), 3));
    }
}

class DLLOps
{
    public Node deleteNth(Node head, Integer n)
    {
        if (n == 1)
        {
            head = head.getNext();
            return head;
        }

        Node node = head;

        while(n > 2 && node != null)
        {
            node = node.getNext();
            --n;
        }

        if (n == 2 && node != null && node.getNext() != null)
        {
            node.setNext(node.getNext().getNext());
            if (node.getNext() != null)
            {
                node.getNext().setPre(node);
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
        this.head = null;
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

            Node nextNode = new Node(value);
            cursor.setNext(nextNode);
            nextNode.setPre(cursor);
        }
    }

    public void print()
    {
        DLL.print(head);
    }

    public static void print(Node node)
    {
        Node cursor = node;
        while (cursor.getNext() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println(cursor.getValue() + " ");

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getPre();
        }
        System.out.println();
        System.out.println("------------------");
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node next, pre;
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

    public Node getPre()
    {
        return pre;
    }

    public void setPre(Node pre)
    {
        this.pre = pre;
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