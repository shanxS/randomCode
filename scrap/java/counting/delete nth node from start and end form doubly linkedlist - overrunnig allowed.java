// delete nth node from start and end form doubly linkedlist - overrunnig allowed

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }

        DLL.print(dll.getHead());

        Deleter deleter = new Deleter();
        //DLL.print(deleter.delteNthNodeFromStartAndEnd(dll.getHead(), 1));
        //DLL.print(deleter.delteNthNodeFromStartAndEnd(dll.getHead(), 4));
        //DLL.print(deleter.delteNthNodeFromStartAndEnd(dll.getHead(), 7));
        DLL.print(deleter.delteNthNodeFromStartAndEnd(dll.getHead(), 5));
    }
}

class Deleter
{
    private Node head;

    public Node delteNthNodeFromStartAndEnd(Node head, final Integer N)
    {
        if (N < 1)
        {
            System.out.println("N to small");
            return head;
        }

        this.head = head;
        Node cursor = head;
        Node startNode = head;
        Node endNode = null;
        Integer count = N;

        while(cursor != null)
        {
            if (count > 1)
            {
                startNode = startNode.getRight();
            }

            if (count <= 1)
            {
                endNode = (endNode == null) ? (head) : (endNode.getRight());
            }

            cursor = cursor.getRight();
            --count;
        }

        if (endNode == null)
        {
            System.out.println("not ling enough");
        }

        if (startNode.getValue() == endNode.getValue())
        {
            deleteNode(startNode);
        }
        else
        {
            deleteNode(startNode);
            deleteNode(endNode);
        }

        return this.head;
    }

    private void deleteNode(Node node)
    {
        Node parentNode = node.getLeft();

        if (parentNode == null)
        {
            head = node.getRight();
            head.setLeft(null);
        }
        else
        {
            Node nextChild = node.getRight();
            parentNode.setRight(nextChild);
            if (nextChild != null)
            {
                nextChild.setLeft(parentNode);
            }
        }
    }
}

class DLL
{
    private Node head;

    public DLL()
    {
        this.head = null;
    }

    public static void print(Node head)
    {
        Node cursor = head;

        while(cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println(cursor.getValue() + " ");

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
        System.out.println();
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
            while(cursor.getRight() != null)
            {
                cursor = cursor.getRight();
            }

            Node newNode = new Node(value);
            cursor.setRight(newNode);
            newNode.setLeft(cursor);
        }
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}