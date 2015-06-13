// Given a binary tree - remove all the half nodes
// code question: 87

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 7, 5,
                null,
                6,
                null,
                9,
                null, null,
                1, 11,
                null, null,
                4};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        HalfNodeRemover hnr = new HalfNodeRemover();
        hnr.remove(bt.getHead());

        System.out.println("---------------");

        BinaryTree.print(bt.getHead());
    }
}

class HalfNodeRemover
{
    public Node remove(Node node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            return node;
        }

        Node leftChild = remove(node.getLeft());
        Node rightChild = remove(node.getRight());

        if (leftChild != null && rightChild != null)
        {
            node.setLeft(leftChild);
            node.setRight(rightChild);
            return node;
        }
        else
        {
            return (leftChild == null) ? rightChild : leftChild;
        }
    }
}

class BinaryTree
{
    private Integer arrayLength;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.arrayLength = array.length;
        this.head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor < arrayLength; ++arrayCursor)
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

            Integer rightChildIndex = getRightChldIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
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

    private Integer getRightChldIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
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