// find sum of path between 2 nodes in binary tree
// code question: 

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {6,3,9,
        null,null,null,
        0,
                null,null,null,null, null,null,
        4, -1,
                null,null,null,null,null,null,null,null, null,null,null,null, null,null,
        10};

        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("---------------------------");

        MaxPathSumFinder msf = new MaxPathSumFinder();
        System.out.print(msf.findSum(bt.getHead(), 3, 10));
    }
}

class MaxPathSumFinder
{
    private Integer globalSum;
    private Integer value1, value2;

    public Integer findSum(Node head, Integer value1, Integer value2)
    {
        globalSum = null;
        this.value1 = value1;
        this.value2 = value2;

        subTreeSum(head);

        return globalSum;
    }

    private Integer subTreeSum(Node node)
    {
        if (node == null || globalSum != null)
        {
            return null;
        }

        if (node.getValue() == value1 || node.getValue() == value2)
        {
            return node.getValue();
        }

        Integer leftSubTreeSum = subTreeSum(node.getLeft());
        Integer rightSubTreeSum = subTreeSum(node.getRight());

        if (leftSubTreeSum != null && rightSubTreeSum != null)
        {
            globalSum = leftSubTreeSum + rightSubTreeSum + node.getValue();
        }
        else if (leftSubTreeSum != null)
        {
            return node.getValue() + leftSubTreeSum;
        }
        else if (rightSubTreeSum != null)
        {
            return node.getValue() + rightSubTreeSum;
        }

        return null;
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

            Integer rightChlidIndex = getRightChildIndex(arrayCursor);
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
        return (index * 2) + 1;
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