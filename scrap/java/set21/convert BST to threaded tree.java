// convert BST to threaded tree
// r4, q3, set21

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[] {50,30,60,20,40,70,35,45,65};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        Threader td = new Threader(tree);
        td.convert();
        td.print();
    }
}

class Threader
{
    private Node head, predecessor;
    private Boolean connectPredecessor;

    public Threader(BST tree)
    {
        this.head = tree.getHead();
        this.predecessor = null;
        this.connectPredecessor = false;
    }

    public void convert()
    {
        connect(head);
    }

    public void print()
    {
        Node cursor = head;

        while(cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }


        Boolean followingThread = false;
        while(cursor != null)
        {
            if (cursor.getIsThreaded())
            {
                System.out.print(cursor.getValue() + " ");
                followingThread = true;
                cursor = cursor.getRight();
            }
            else
            {

                if (followingThread)
                {
                    System.out.print(cursor.getValue() + " ");
                    followingThread = false;

                    cursor = cursor.getRight();
                } else
                {
                    if (cursor.getLeft() == null)
                    {
                        System.out.print(cursor.getValue() + " ");
                        cursor = cursor.getRight();
                    } else
                    {
                        cursor = cursor.getLeft();
                    }
                }
            }
        }
    }

    private void connect(Node node)
    {
        if (node == null)
        {
            return;
        }

        connect(node.getLeft());

        if (connectPredecessor == true)
        {
            predecessor.setRight(node);
            predecessor.setIsThreaded(true);

            predecessor = null;
            connectPredecessor = false;
        }
        if (node.getRight() == null && node.getLeft() == null)
        {
            connectPredecessor = true;
            predecessor = node;
        }

        connect(node.getRight());
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.ruleLeftLessThan = true;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());

    }

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == ruleLeftLessThan)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if ((node.getValue() > value) != ruleLeftLessThan)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;
    private Boolean isThreaded;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.isThreaded = false;
    }

    public Boolean getIsThreaded()
    {
        return isThreaded;
    }

    public void setIsThreaded(Boolean isThreaded)
    {
        this.isThreaded = isThreaded;
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}