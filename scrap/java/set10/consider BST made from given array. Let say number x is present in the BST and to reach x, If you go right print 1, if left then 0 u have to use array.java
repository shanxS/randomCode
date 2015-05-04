// consider BST made from given array. Let say number x is present in the BST and to reach x, If you go right print 1, if left then 0 u have to use array
// really interesting question - did it all on my own :)
// r2, q1, set10 amazon

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{10,1,9,2,8,3,7};//{20, 11, 30, 5, 15, 25, 40, 3, 9, 1, 2};

        BST bst = new BST();
        for (Integer i = 0; i < array.length; ++i)
        {
            bst.insert(array[i]);
        }

        BST.print(bst.getHead());
        System.out.println("Encoding using bst");
        //BST.printEncoding(bst.getHead(), 3);
        //BST.printEncoding(bst.getHead(), 25);
        ArrayEncoding.printEncoding(array, 7);

        System.out.println("Encoding using array :)");
        System.out.println();
        //ArrayEncoding.printEncoding(array, 3);
        //ArrayEncoding.printEncoding(array, 25);
        ArrayEncoding.printEncoding(array, 7);
    }
}

class ArrayEncoding
{
    public static void printEncoding(Integer[] array, Integer value)
    {
        Boolean rule = null;
        Integer parent = array[0];
        rule = parent > value;

        Integer cursor = 1;
        Integer child = null;
        while(cursor < array.length && child == null)
        {
            if ((parent > array[cursor]) == rule)
            {
                child = array[cursor];
                printRuleCode(rule);
            }

            ++cursor;
        }

        rule = child > value;
        while(cursor < array.length)
        {
            if ((child > array[cursor]) == rule && (parent > child) == (parent > array[cursor]))
            {
                printRuleCode(rule);
                parent = child;
                child = array[cursor];
                rule = child > value;
            }

            if (array[cursor] == value)
            {
                System.out.print(" -1 ");
                break;
            }

            ++cursor;
        }
    }

    private static void printRuleCode(Boolean rule)
    {
        if (rule)
        {
            System.out.print(0 + " ");
        }
        else
        {System.out.print(1 + " ");}
    }
}

class BST
{
    private Node head;

    public BST()
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
            insert(head, value);
        }
    }

    public static void printEncoding(Node node, Integer value)
    {
        if (node == null)
        {
            return;
        }

        if (node.getValue() > value)
        {
            System.out.print(0 + " ");
            printEncoding(node.getLeft(), value);
        }
        else if (node.getValue() < value)
        {
            System.out.print(1 + " ");
            printEncoding(node.getRight(), value);
        }
        else
        {
            System.out.println("-1");
        }
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
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
        if (node.getValue() > value)
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
        else if (node.getValue() < value)
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