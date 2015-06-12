// reverse BST
// code question: 82

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = {30, 10, 40, 5, 35, 50};
        Integer[] array = {30,10,40,5,20,35,50,15,45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        BSTInvertor invertor = new BSTInvertor();
        BSTInvertor.printTree(invertor.invert(tree.getHead()));

    }
}

class BSTInvertor
{
    private List<Node> reverseHeads;
    final private boolean LEFTDIRECTION = true;
    final private boolean RIGHTDIRECTION = false;

    public List<Node> invert(Node head)
    {
        reverseHeads = new ArrayList<>();

        invertNode(head.getLeft(), head, LEFTDIRECTION);
        invertNode(head.getRight(), head, RIGHTDIRECTION);

        head.setLeft(null);
        head.setRight(null);

        return reverseHeads;
    }

    public static void printTree(List<Node> heads)
    {
        for (Node head : heads)
        {
            System.out.print("----------------------");
            printTree(head);
        }
    }

    private static void printTree(Node node)
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

        printTree(node.getLeft());
        printTree(node.getRight());
    }

    private void invertNode(Node node, Node parent, boolean parentDirection)
    {
        if (node == null)
        {
            return;
        }

        if (isLeafNode(node))
        {
            if (parentDirection == LEFTDIRECTION)
            {
                node.setRight(parent);
                parent.setLeft(null);
            }
            else if (parentDirection == RIGHTDIRECTION)
            {
                node.setLeft(parent);
                parent.setRight(null);
            }

            reverseHeads.add(node);

            return;
        }

        invertNode(node.getLeft(), node, LEFTDIRECTION);
        invertNode(node.getRight(), node, RIGHTDIRECTION);

        if (parentDirection == LEFTDIRECTION)
        {
            node.setRight(parent);
            parent.setLeft(null);
        }
        else if (parentDirection == RIGHTDIRECTION)
        {
            node.setLeft(parent);
            parent.setRight(null);
        }
    }

    private boolean isLeafNode(Node node)
    {
        return (node.getLeft() == null && node.getRight() == null);
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