// given BST find node closest to given number
// r2, q2, set27

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,23};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        NearestFinder nf = new NearestFinder(tree);
        //nf.findInorder(21);
        nf.findInorder(9);
    }
}

class NearestFinder
{
    private Node head;
    private Integer nodeValue;
    private Integer absDifference;
    private Integer target;

    public NearestFinder(BST tree)
    {
        this.head = tree.getHead();
        this.nodeValue = null;
        this.absDifference = null;
        this.target = null;
    }

    public Boolean findInorder(Integer value)
    {
        this.target = value;
        Boolean result = findInorder(head);
        return result;
    }

    private Boolean findInorder(Node node)
    {
        if (node == null)
        {
            return false;
        }

        Boolean found = findInorder(node.getLeft());

        if (found)
        {
            return found;
        }
        else if (nodeValue == null)
        {
            nodeValue = node.getValue();
            absDifference = Math.abs(target - nodeValue);

        }
        else
        {
            Integer thisAbsDifference =  Math.abs(target - node.getValue());
            if (thisAbsDifference < absDifference)
            {
                absDifference = thisAbsDifference;
                nodeValue = node.getValue();
            }
            else
            {
                return true;
            }
        }

        return findInorder(node.getRight());
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