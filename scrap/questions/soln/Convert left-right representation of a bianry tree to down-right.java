// Convert left-right representation of a bianry tree to down-right
// code question: 88

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10, 8, 7, 6, 5, 9, 10, 3,
                null, null,
                4, 11, 12};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.println("-----------------");

        LeftRightToDownRight converter = new LeftRightToDownRight();
        converter.convert(bt.getHead());
        LeftRightToDownRight.print(bt.getHead());
    }
}

class LeftRightToDownRight
{
    public void convert(Node head)
    {
        List<Node> list = new ArrayList<>();
        list.add(head);
        Integer cursor = 0;
        Integer size = list.size();

        while(cursor < size)
        {
            for (Integer i=cursor; i<size; ++i)
            {
                Node node = list.get(i);

                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }
            }

            for (Integer i=cursor; i<size-1; ++i)
            {
                Node parent = list.get(i);
                Node child = list.get(i+1);
                parent.setRight(child);
                parent.setLeft(null);
            }
            list.get(size-1).setRight(null);
            list.get(size-1).setLeft(null);

            if (size < list.size())
            {
                Node parentNode = list.get(cursor);
                parentNode.setLeft(list.get(size));
            }

            cursor = size;
            size = list.size();
        }
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        Node cursor = node.getRight();
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println();

        print(node.getLeft());
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        this.head = new Node(array[0]);
        this.arrayLength = array.length;

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor=0; arrayCursor<arrayLength; ++arrayCursor)
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

            list.remove(node);
        }
    }

    private Integer getRightChildIndex(Integer index)
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