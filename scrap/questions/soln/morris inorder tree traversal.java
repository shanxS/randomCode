// morris inorder tree traversal
// code question: 105

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        BinaryTree bt = new BinaryTree(array);

        MorrisConverter mc = new MorrisConverter();
        mc.convert(bt.getHead());
    }
}

class MorrisConverter
{
    public Node convert(Node head)
    {
        Node cursor = head;
        while (cursor != null)
        {
            if (cursor.getLeft() != null)
            {
                Node inorderPre = getInorderPre(cursor);
                inorderPre.setRight(cursor);

                Node cursorLeft = cursor.getLeft();
                cursor.setLeft(null);
                cursor = cursorLeft;
            }
            else
            {
                System.out.print(cursor.getValue() + " ");
                cursor = cursor.getRight();
            }
        }

        return null;
    }

    private Node getInorderPre(Node node)
    {
        Node cursor = node.getLeft();
        while (cursor.getRight() != null)
        {
            cursor = cursor.getRight();
        }

        return cursor;
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        head = new Node(array[0]);
        arrayLength = array.length;

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

    public static void print(Node node)
    {
        if(node == null)
        {return;}

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

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
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
