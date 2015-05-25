// reflect binary tree
// r2, q3, set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 3, 2,
                null, null,
                5, 4
        };

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.print("-------------");
        Reflector r = new Reflector();
        r.reflect(bt.getHead());
        BinaryTree.print(bt.getHead());
    }
}

class Reflector
{
    public void reflect(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node leftNode = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(leftNode);

        reflect(node.getRight());
        reflect(node.getLeft());
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

            Integer leftNodeIndex = getLeftNodeIndex(arrayCursor);
            if (isValidIndex(leftNodeIndex) && isValidValue(array[leftNodeIndex]))
            {
                Node leftNode = new Node(array[leftNodeIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightNodeIndex = getRightNodeIndex(arrayCursor);
            if (isValidIndex(rightNodeIndex) && isValidValue(array[rightNodeIndex]))
            {
                Node rightNode = new Node(array[rightNodeIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private Integer getLeftNodeIndex(Integer index)
    {
        return (index*2) + 1;
    }

    private Integer getRightNodeIndex(Integer index)
    {
        return (index*2) + 2;
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