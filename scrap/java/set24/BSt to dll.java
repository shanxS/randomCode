// BSt to dll
// telephonic q1, set 24

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{50,30,80,20,40,70,100,25,35,90};
        Integer[] array = new Integer[]{50};
        BST tree = new BST();
        for(Integer vlaue : array)
        {
            tree.insert(vlaue);
        }

        BST.print(tree.getHead());

        BSTToDLL b2d = new BSTToDLL();
        b2d.convert(tree);
    }
}

class BSTToDLL
{
    private Node head;
    
    public Node convert(BST tree)
    {
        this.head = tree.getHead();
        convert(head, null);
        
        return head;
    }

    private Node convert(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftChild = convert(node.getLeft(), parent);

        if(leftChild != null)
        {
            leftChild.setRight(node);
            node.setLeft(leftChild);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightChild = convert(node.getRight(), node);

        return (rightChild == null) ? node : rightChild;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
            if(node.getLeft()!= null)
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
