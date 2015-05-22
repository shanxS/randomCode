//  binary tree - if we draw a line from root then we have to print all nodes on that line
// r1, q2, set26

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{50,30,60,20,40,55,25};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        HeadDropPrinter printer = new HeadDropPrinter();
        printer.print(tree.getHead(), 0);
    }
}

class HeadDropPrinter
{
    public void print(Node node, Integer parentDistance)
    {
        if (node == null)
        {
            return;
        }

        if (parentDistance == 0)
        {
            System.out.print(node.getValue() + " ");
        }

        print(node.getLeft(), parentDistance-1);
        print(node.getRight(), parentDistance+1);
    }

}

class BST
{
    private Node head;
    
    public BST()
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
            insert(head, value);
        }
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
    private Node left, right;
    private Integer value;
    
    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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