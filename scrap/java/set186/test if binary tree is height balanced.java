// test if binary tree is height balanced
// set186, r2. q1
// code question 42

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5,
                null,
                7,
                null, null,
                6,
                null};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        HeightTester ht = new HeightTester();
        System.out.print(ht.test(bt.getHead()));

    }
}

class HeightTester
{
    public Boolean test(Node head)
    {
        if (testNode(head) == -1)
        {
            return false;
        }
        return true;
    }

    private Integer testNode(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer leftDepth = testNode(node.getLeft());
        Integer rightDepth = testNode(node.getRight());

        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth-rightDepth) > 1)
        {
            return -1;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }
}

class BinaryTree
{
    private Node head;
    final private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        head = new Node(array[0]);
        arrayLength = array.length;
        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer cursor=0; cursor<array.length; ++cursor)
        {
            if (!isValidValue(array[cursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(cursor);
            if (isValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChlidIndex = getRightChlidIndex(cursor);
            if (isValidIndex(rightChlidIndex) && isValidValue(array[rightChlidIndex]))
            {
                Node rightNode = new Node(array[rightChlidIndex]);
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

    private Integer getRightChlidIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
    }

    private Boolean isValidValue(Integer value)
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
    private Integer value;
    private Node left, right;

    public Node (Integer value)
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
}