public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3};//, 4, 5, 6, 7};
//        SingleLL sLL = new SingleLL();
//        for (Integer value : array)
//        {
//            sLL.insert(value);
//        }
//
//        NthPrinterFromStart np = new NthPrinterFromStart();
//        np.printNthWithoutSkipping(sLL.getHead(), 5);
//        np.printNthWITHSkipping(sLL.getHead(), 5);
//        np.printKthWithZeroIndexing(sLL.getHead(), 8);
//        np.printWithOneIndexing(sLL.getHead(), 8);
//
//        NthPrinterFromBack nb = new NthPrinterFromBack();
//        nb.printNthFromBackOneBaseIndexing(sLL.getHead(), 1);
//        nb.printNthFromBackOneBaseIndexing(sLL.getHead(), 7);
//        nb.printNthFromBackOneBaseIndexing(sLL.getHead(), 8);

        Dll dll = new Dll();
        for (Integer value : array)
        {
            dll.insert(value);
        }
        dll.printNthNodeFromFrontAndBack(1);
        dll.printNthNodeFromFrontAndBack(2);
        dll.printNthNodeFromFrontAndBack(3);
    }
}

class Dll
{
    private Node head;

    public Dll()
    {
        this.head = null;
    }

    public void printNthNodeFromFrontAndBack(Integer N)
    {
        Integer counter = N;
        Node firstNode = head;
        Node endNode = null;
        Node cursor = head;

        while (cursor != null)
        {
            if (counter > 1)
            {
                firstNode = (firstNode == null) ? head : firstNode.getRight();
            }
            if (counter <= 1)
            {
                endNode = (endNode == null) ? head : endNode.getRight();
            }

            --counter;
            cursor = cursor.getRight();
        }

        if (counter > 1)
        {
            System.out.println("printNthNodeFromFrontAndBack not long enough");
            return;
        }

        System.out.println("printNthNodeFromFrontAndBack " + firstNode.getValue() + " " + endNode.getValue());

    }

    public static void print(Node head)
    {
        Node cursor = head;
        while(cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.print(cursor.getValue() + " ");

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
    }

    public void insert(Integer value)
    {
        if(head == null)
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
}

class NthPrinterFromBack
{
    public void printNthFromBackOneBaseIndexing(Node head, Integer N)
    {
        Node cursor = head;
        Node node = null;
        while(cursor!=null)
        {
            if (N == 1)
            {
                node = head;
            }
            else if (node != null)
            {
                node = node.getRight();
            }

            cursor = cursor.getRight();
            --N;
        }

        if (N >= 1)
        {
            System.out.println("printNthFromBackOneBaseIndexing not long enough");
            return;
        }

        System.out.println("nth from back with 1 based indexing " + node.getValue());
    }
}

class NthPrinterFromStart
{
    public void printNthWithoutSkipping(Node head, Integer n)
    {
        Node cursor = head;

        while(cursor != null
                && n > 1)
        {
            cursor = cursor.getRight();
            --n;
        }

        System.out.println("print without skip " + cursor.getValue());
    }

    public void printWithOneIndexing(Node head, Integer n)
    {
        Node cursor = head;

        while (n > 1 && cursor != null)
        {
            cursor = cursor.getRight();
            --n;
        }

        if (cursor == null)
        {
            System.out.println("printWithOneIndexing not long enough");
            return;
        }

        System.out.println("print with 1 indexing " + cursor.getValue());
    }

    public void printKthWithZeroIndexing(Node head, Integer n)
    {
        Node cursor = null;

        while(n > 0)
        {
            cursor = (cursor == null) ? head : cursor.getRight();
            --n;
            if (cursor == null)
            {
                break;
            }
        }

        if (cursor == null)
        {
            System.out.println(" printKthWithZeroIndexing not long enough");
            return;
        }

        System.out.println("print with 0 indexing " + cursor.getValue());
    }

    public void printNthWITHSkipping(Node head, Integer n)
    {
        Node cursor = head;

        while(cursor != null
                && n > 0)
        {
            if ( n==1 )
            {
                System.out.print("print with skip " + cursor.getValue() + " ");
            }

            cursor = cursor.getRight();
            --n;
        }

        System.out.println("after skipping we are at " + cursor.getValue());
    }
}

class SingleLL
{
    private Node head;

    public SingleLL()
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

            cursor.setRight(new Node(value));
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