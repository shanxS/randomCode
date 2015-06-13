// Print nodes between two given level numbers of a binary tree
// code question: 

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {20, 8, 22, 5, 3, 4, 25,
//                null, null,
//                10, 14};

//        Integer[] array = {1,2,3,
//        null,
//        4,
//                null,null,
//                null,null,null,
//        5,
//                null,null,null,null,
//                null,null,null,null,null,null,null,
//        6};

//        Integer[] array = {6,3,8,1,5,7,11,
//        null,null,
//                null,null,
//                null,null,
//        9,13};

        Integer[] array = {20,8,22,4,12,
        null,null,
                null,null,
        10,14};

        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("---------------------------");

        LevelRangePrinter bv = new LevelRangePrinter();
        bv.print(bt.getHead(), 2, 4);

    }
}

class LevelRangePrinter
{
    public void print(Node head, Integer low, Integer hign)
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();
        Integer level = 1;

        while(cursor < size)
        {
            if (level >= low && level <= hign)
            {
                for (Integer i=cursor; i<size; ++i)
                {
                    System.out.print(list.get(i).getValue() + " ");
                }
                System.out.println();
            }

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

            cursor = size;
            size = list.size();
            ++level;
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