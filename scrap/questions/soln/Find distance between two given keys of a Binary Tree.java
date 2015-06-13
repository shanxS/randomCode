// Find distance between two given keys of a Binary Tree
// code question: 99

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {20, 8, 22, 4, 12,
                null, null, null, null,
                10, 14};

        BinaryTree bt = new BinaryTree(array);
        DistanceFinder na = new DistanceFinder();
        System.out.print(na.findBetween(bt.getHead(), 4, 22));
    }
}

class DistanceFinder
{
    public Integer findBetween (Node head, Integer value1, Integer value2)
    {
        ArrayDeque<Node> path1 = new ArrayDeque<>();
        populatePathFor1(head, value1, path1);

        ArrayDeque<Node> path2 = new ArrayDeque<>();
        populatePathFor1(head, value2, path2);

        while (path1.peek().getValue() == path2.peek().getValue())
        {
            path1.removeFirst();
            path2.removeFirst();
        }

        return path1.size() + path2.size() + 1;
    }

    private Boolean populatePathFor1(Node node, Integer value, ArrayDeque<Node> path)
    {
        if (node == null)
        {
            return false;
        }

        path.add(node);

        if (node.getValue() == value)
        {
            return true;
        }

        if (populatePathFor1(node.getLeft(), value, path))
        {
            return true;
        }
        else if (populatePathFor1(node.getRight(), value, path))
        {
            return true;
        }
        else
        {
            path.removeLast();
            return false;
        }
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        this.arrayLength = array.length;
        this.head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor<arrayLength; ++arrayCursor)
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

            Integer rightChildIndex = getRigthChildIndex(arrayCursor);
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

    private Integer getRigthChildIndex(Integer index)
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

    public Node (Integer value)
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