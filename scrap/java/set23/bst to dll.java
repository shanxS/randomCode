// bst to dll
// r4, q9, set23

import org.omg.PortableInterceptor.INACTIVE;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{20,10,30,5,15,25,40,26,35};
        BST tree = new BST();
        for (Integer vlaue : array)
        {
            tree.insert(vlaue);
        }
        BSTToDLL b2d = new BSTToDLL(tree);
        b2d.convert();
    }
}

class BSTToDLL
{
    private Node head;

    public BSTToDLL(BST tree) {
        this.head = tree.getHead();
    }

    public Node convert()
    {
        Node tenp = inorder(head, null);
        return tenp;
    }

    private Node inorder(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftChild = inorder(node.getLeft(), parent);

        if (leftChild != null)
        {
            leftChild.setRight(node);
            node.setLeft(leftChild);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightChild = inorder(node.getRight(), node);

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

    private void insert(Node node, Integer value) {
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

    public Node getHead() {
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

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}