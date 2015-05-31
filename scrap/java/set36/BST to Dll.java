// BST to Dll
// telephonic q1, set36

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 50, 5, 11};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        BSTToDLL b2d = new BSTToDLL();
        Node cursor = b2d.convert(tree.getHead());
        while (cursor.getRigjt() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRigjt();
        }
        System.out.println(cursor.getValue() + " ");

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
    }
}

class BSTToDLL
{
    public Node convert(Node head)
    {
        Node DllHead = convertNode(head, null);

        while(DllHead.getLeft() != null)
        {
            DllHead = DllHead.getLeft();
        }

        return DllHead;
    }

    private Node convertNode(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftChild = convertNode(node.getLeft(), parent);
        if (leftChild != null)
        {
            leftChild.setRigjt(node);
            node.setLeft(leftChild);
        }
        else if (parent != null)
        {
            parent.setRigjt(node);
            node.setLeft(parent);
        }

        Node rightChild = convertNode(node.getRigjt(), node);

        return (rightChild == null) ? node : rightChild;
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

    public static  void print(Node node)
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
        if (node.getRigjt() != null)
        {
            System.out.print(node.getRigjt().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRigjt());
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
            if (node.getRigjt() != null)
            {
                insert(node.getRigjt(), value);
            }
            else
            {
                node.setRigjt(new Node(value));
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
    private Node left, rigjt;

    public Node(Integer value)
    {
        this.value = value;
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

    public Node getRigjt()
    {
        return rigjt;
    }

    public void setRigjt(Node rigjt)
    {
        this.rigjt = rigjt;
    }
}