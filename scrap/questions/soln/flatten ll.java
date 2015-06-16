// flatten ll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {5, 7, 8, 30};
        LL ll1 = new LL();
        for (Integer value : array1)
        {
            ll1.insertDown(value);
        }

        Integer[] array2 = {10, 20};
        LL ll2 = new LL();
        for (Integer value : array2)
        {
            ll2.insertDown(value);
        }

        Integer[] array3 = {19, 22, 50};
        LL ll3 = new LL();
        for (Integer value : array3)
        {
            ll3.insertDown(value);
        }

        Integer[] array4 = {28, 35, 40, 45};
        LL ll4 = new LL();
        for (Integer value : array4)
        {
            ll4.insertDown(value);
        }

        ll1.getHead().setLeft(ll2.getHead());
        ll2.getHead().setLeft(ll3.getHead());
        ll3.getHead().setLeft(ll4.getHead());

        LL.printDown(flatten(ll1));
    }

    private static Node flatten(LL ll)
    {
        Node previousMerged = ll.getHead();
        Node leftCursor = ll.getHead().getLeft();

        while (leftCursor != null)
        {
            previousMerged = merge(previousMerged, leftCursor);
            leftCursor = leftCursor.getLeft();
        }

        return previousMerged;
    }

    private static Node merge(Node node1, Node node2)
    {
        Node mergedHead  = (node1.getValue() < node2.getValue()) ? node1 : node2;

        recusivlyMerge(node1, node2);

        return mergedHead;
    }

    private static void recusivlyMerge(Node node1, Node node2)
    {
        if (node1 == null || node2 == null)
        {
            return;
        }

        if (node1.getValue() < node2.getValue())
        {
            while (node1.getDown() != null && node1.getDown().getValue() < node2.getValue())
            {
                node1 = node1.getDown();
            }

            Node nextNode1 = node1.getDown();
            node1.setDown(node2);
            recusivlyMerge(nextNode1, node2);
        }
        else
        {
            while (node2.getDown() != null && node2.getDown().getValue() < node1.getValue())
            {
                node2 = node2.getDown();
            }

            Node nextNode2 = node2.getDown();
            node2.setDown(node1);
            recusivlyMerge(node1, nextNode2);
        }
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

            while (cursor.getLeft() != null)
            {
                cursor = cursor.getLeft();
            }

            cursor.setLeft(new Node(value));
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
            node = node.getLeft();
        }
        System.out.println();
    }

    public void insertDown(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            Node cursor = head;
            while(cursor.getDown() != null)
            {
                cursor = cursor.getDown();
            }

            cursor.setDown(new Node(value));
        }
    }

    public void printDown()
    {
        LL.printDown(head);
    }

    public static void printDown(Node node)
    {
        Node cursor = node;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getDown();
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
    private Node left, down;
    private Integer value;

    public Node(Integer value)
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

    public Node getDown()
    {
        return down;
    }

    public void setDown(Node down)
    {
        this.down = down;
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