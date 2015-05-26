// level printing of binary tree using queue and recusrsion
// r2, q1, set29

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] arraay = new Integer[]{
                20,10,40,5,
                null,
                25,50,
                null,
                7
        };

        BinaryTree bt = new BinaryTree(arraay);
        BinaryTree.print(bt.getHead());
        LevelPrinter lp = new LevelPrinter();
        //lp.printRecursion(bt.getHead());
        lp.printQueue(bt.getHead());
    }
}

class LevelPrinter
{
    public void printQueue(Node head)
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();
        while(cursor < size)
        {
            Integer staleCount = size;
            for (Integer i = cursor; i < staleCount; ++i)
            {
                Node node = list.get(i);

                System.out.print(node.getValue() +  " ");

                Node leftNode = node.getLeft();
                if (leftNode != null)
                {
                    list.add(leftNode);
                }
                Node rightNode = node.getRight();
                if (rightNode != null)
                {
                    list.add(rightNode);
                }
            }
            System.out.println();

            cursor = size;
            size = list.size();
        }
    }

    public void printRecursion(Node head)
    {
        Integer level = 0;
        while (printLevel(head, level) != null)
        {
            ++level;
            System.out.println();
        }
    }

    private Node printLevel(Node node, Integer level)
    {
        if (node == null)
        {
            return null;
        }

        if (level == 0)
        {
            System.out.print(node.getValue() + " ");
            return node;
        }

        Node leftChild = printLevel(node.getLeft(), level-1);
        Node rightChils = printLevel(node.getRight(), level-1);

        if (leftChild != null)
        {
            return leftChild;
        }
        else if (rightChils != null)
        {
            return rightChils;
        }

        return null;
    }
}

class BinaryTree
{
    private Integer[] array;
    private Node head;

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
            if(isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
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

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
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