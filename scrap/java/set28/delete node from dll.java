// delete node from dll
// r2, q2, set28

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
//        DLL dll = new DLL();
//        for (Integer value : array)
//        {
//            dll.insert(value);
//        }
//
//        DLL.print(dll.getHead());
//        System.out.println("-----------");
//
//        dll.delete(4);
//        DLL.print(dll.getHead());
//        System.out.println("-----------");
//
//        dll.delete(7);
//        DLL.print(dll.getHead());
//        System.out.println("-----------");
//
//
//        dll.delete(1);
//        DLL.print(dll.getHead());
//        System.out.println("-----------");

        Integer[] array = new Integer[]{1};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }

        DLL.print(dll.getHead());
        System.out.println("-----------");

        dll.delete(1);
        DLL.print(dll.getHead());
        System.out.println("-----------");
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
            while(cursor.getRight() != null)
            {
                cursor = cursor.getRight();
            }

            Node thisNode = new Node(value);
            thisNode.setLeft(cursor);
            cursor.setRight(thisNode);
        }
    }

    public void delete(Integer value)
    {
        Node node = getNode(value);

        if (node == null)
        {
            System.out.print("cant find ndoe");
            return;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            head = null;
        }
        else if (node.getLeft() != null && node.getRight() == null)
        {
            Node leftNode = node.getLeft();
            leftNode.setRight(null);
        }
        else if (node.getLeft() == null && node.getRight() != null)
        {
            head = head.getRight();
            head.setLeft(null);
        }
        else if (node.getLeft() != null && node.getRight() != null)
        {
            Node leftNode = node.getLeft();
            Node rightNode = node.getRight();

            leftNode.setRight(rightNode);
            rightNode.setLeft(leftNode);
        }
    }

    private Node getNode(Integer value)
    {
        Node cursor = head;
        while (cursor != null)
        {
            if (cursor.getValue() == value)
            {
                return cursor;
            }

            cursor = cursor.getRight();
        }

        return null;
    }

    public static void print(Node head)
    {
        if (head == null)
        {
            System.out.print("cant print head is null");
            return;
        }

        Node cursor = head;
        while(cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() +  " ");
            cursor = cursor.getRight();
        }
        System.out.print(cursor.getValue() +  " ");
        System.out.println();
        while(cursor.getLeft() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
        System.out.print(cursor.getValue() +  " ");
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