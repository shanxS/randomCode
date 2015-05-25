// prune binary tree
// r4, q3, set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 12,
                null, null,null,
                10,
                null, null, null,
                13, 14,
                null,null,null,null,null,null,null,null,null,
                11,
                null,null,null,null,null,null,null,null,
                15
        };

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.print("-------------");

        Pruner p = new Pruner();
        p.prune(bt.getHead(), 45);
        BinaryTree.print(bt.getHead());


    }
}

class Pruner
{
    private Integer key;

    public void prune(Node head, Integer key)
    {
        this.key = key;

        prune(head);
    }

    private Integer prune(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer leftSum = prune(node.getLeft()) + node.getValue();
        Integer rightSum = prune(node.getRight()) + node.getValue();

        if (leftSum < key && rightSum > key)
        {
            node.setLeft(null);
            return rightSum;
        }
        else if (leftSum > key && rightSum < key)
        {
            node.setRight(null);
            return leftSum;
        }

        return leftSum + rightSum - node.getValue();
    }
}

class BinaryTree
{
    private Integer[] array;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.array = array;
        this.head = new Node(array[0]);

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

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return (2*index) + 2;
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