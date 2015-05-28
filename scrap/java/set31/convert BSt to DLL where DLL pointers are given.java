// convert BSt to DLL where DLL pointers are given
// wirtten, q1, set31

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20,10,30,8,25,40,5,9};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BSTToDll b2d = new BSTToDll();
        Node cursor = b2d.convert(tree);
        while(cursor.getNext() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
        System.out.println(cursor.getValue());

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getPre();
        }
    }
}

class BSTToDll
{
    public Node convert(BST tree)
    {
        Node head = tree.getHead();
        convert(head, null);

        Node cursor = head;
        while(cursor.getPre() != null)
        {
            cursor = cursor.getPre();
        }

        return cursor;
    }

    private Node convert(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftChild = convert(node.getLeft(), parent);
        if (leftChild != null)
        {
            leftChild.setNext(node);
            node.setPre(leftChild);
        }
        else if (parent != null)
        {
            parent.setNext(node);
            node.setPre(parent);
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

    public  void insert(Integer value)
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
    private Node left, right, pre, next;

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

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }

    public Node getPre()
    {
        return pre;
    }

    public void setPre(Node pre)
    {
        this.pre = pre;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node next)
    {
        this.next = next;
    }
}