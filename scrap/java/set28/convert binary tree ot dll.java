// convert binary tree ot dll
// r1, q3, set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{
                1, 2, 3, 4, 5,
                null,
                8,
                null, null, null,
                6,
                null, null,
                9,
                null, null, null, null, null, null, null,
                7,
                null, null, null, null, null, null,
                10
        };

        BST tree = new BST(array);
        BST.print(tree.getHead());
        BST.printInorder(tree.getHead());

        BSTToDLL b2d = new BSTToDLL();
        b2d.convert(tree.getHead());

        Node cursor = tree.getHead();
        while(cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }
        System.out.println("--------------------");
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
    }
}

class BSTToDLL
{
    public void convert(Node head)
    {
        convertSubTree(head, null);
    }

    private Node convertSubTree(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = convertSubTree(node.getLeft(), parent);
        if (leftNode != null)
        {
            leftNode.setRight(node);
            node.setLeft(leftNode);
        }
        else if (parent != null)
        {
            parent.setRight(node);
            node.setLeft(parent);
        }

        Node rightNode = convertSubTree(node.getRight(), node);

        return (rightNode == null) ? node : rightNode;

    }
}

class BST
{
    private Node head;
    private Integer[] array;

    public BST(Integer[] array)
    {
        this.head = new Node(array[0]);
        this.array = array;
        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor=0; arrayCursor<array.length; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);
            Integer leftChildIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }
            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(0);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return (2*index) + 2;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
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

    public static void printInorder(Node node)
    {
        if (node == null)
        {
            return;
        }

        printInorder(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInorder(node.getRight());
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