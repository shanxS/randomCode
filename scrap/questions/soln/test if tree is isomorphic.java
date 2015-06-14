// test if tree is isomorphic

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array1 = {1, 2, 3, 4, 5, 6,
                null, null, null,
                7, 8};
        BinaryTree t1 = new BinaryTree(array1);
        BinaryTree.print(t1.getHead());
        System.out.println("====================");

        Integer[] array2 = {1, 3, 2,
                null,
                6, 4, 5,
                null, null, null, null, null, null,
                8, 8};
        BinaryTree t2 = new BinaryTree(array2);
        BinaryTree.print(t2.getHead());

        IsoTester it = new IsoTester();
        System.out.print(it.test(t1.getHead(), t2.getHead()));
    }
}

class IsoTester
{

    public Boolean test(Node head1, Node head2)
    {
        flippedInPast = false;

        return testForNode(head1, head2);
    }

    private Boolean testForNode(Node node1, Node node2)
    {
        if (node1 == null && node2 == null)
        {
            return true;
        }
        else if ((node1 != null && node2 == null) || (node1 == null && node2 != null))
        {
            return false;
        }

        if (node1.getValue().intValue() != node2.getValue().intValue())
        {
            return false;
        }


        return (testForNode(node1.getRight(), node2.getLeft()) && testForNode(node1.getLeft(), node2.getRight()))
                || (testForNode(node1.getRight(), node2.getRight()) && testForNode(node1.getLeft(), node2.getLeft()));
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