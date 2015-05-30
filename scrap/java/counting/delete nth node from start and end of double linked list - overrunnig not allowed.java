// delete nth node from start and end of double linked list - overrunnig not allowed

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] array = new Integer[]{1, 2, 3};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }

        DLL.print(dll.getHead());

        Deleter deleter = new Deleter();
        //DLL.print(deleter.deleteNthFromStartAndEnd(dll.getHead(), 1));
        //DLL.print(deleter.deleteNthFromStartAndEnd(dll.getHead(), 5));
        //DLL.print(deleter.deleteNthFromStartAndEnd(dll.getHead(), 4));

//        DLL.print(deleter.deleteNthFromStartAndEnd(dll.getHead(), 2));
        DLL.print(deleter.deleteNthFromStartAndEnd(dll.getHead(), 1));
    }
}

class Deleter
{
    public Node deleteNthFromStartAndEnd(Node head, final Integer N)
    {
        if (N < 1)
        {
            System.out.println("invalid N");
            return head;
        }

        Node cursor = head;
        Integer count = N;
        Node endNode = null;
        Node startNode = head;

        while(cursor != null)
        {
            if (count > 1)
            {
                startNode = startNode.getRight();
            }
            if (count <= 1)
            {
                endNode = (endNode == null) ? head : endNode.getRight();
            }

            cursor = cursor.getRight();
            --count;
        }

        if (N + count > 0)
        {
            System.out.println("not long enough");
            return head;
        }

        Node startParentNode = startNode.getLeft();
        Node endParentNode = endNode.getLeft();

        if (startParentNode == null)
        {
            if (startNode.getRight().getValue() == endNode.getValue())
            {
                head = endNode.getRight();
                head.setLeft(null);
            }
            else
            {
                head = head.getRight();
                head.setLeft(null);

                endParentNode.setRight(endNode.getRight());
                if (endNode.getRight() != null)
                {
                    endNode.getRight().setLeft(endParentNode);
                }
            }
        }
        else
        {
            if (startNode.getRight().getValue() == endNode.getValue())
            {
                startParentNode.setRight(endNode.getRight());
                if (endNode.getRight() != null)
                {
                    endNode.getRight().setLeft(startParentNode);
                }
            }
            else
            {
                startParentNode.setRight(startNode.getRight());
                startNode.getRight().setLeft(startParentNode);

                endParentNode.setRight(endNode.getRight());
                if (endNode.getRight() != null)
                {
                    endNode.getRight().setLeft(endParentNode);
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

            Node node = new Node(value);
            cursor.setRight(node);
            node.setLeft(cursor);
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