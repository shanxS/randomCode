// Given a Binary Tree, convert it to a Binary Search Tree. The conversion must be done in such a way that keeps the original structure of Binary Tree. 
// code question: 71

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {10, 2, 7, 8, 4,
//                null, null, null, null,
//                3};
        Integer[] array = {10,30,15,20,null,null,5};
        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("----------------");

        BinaryTreeToBST b2bst = new BinaryTreeToBST();
        b2bst.convert(bt.getHead());
        BinaryTree.print(bt.getHead());

    }
}

class BinaryTreeToBST
{
    private List<Integer> numbers;

    public void convert(Node head)
    {
        numbers = new ArrayList<>();
        populateNumbersInorder(head);
        Collections.sort(numbers);

        rePopulateTree(head);
    }

    private void rePopulateTree(Node node)
    {
        if (node == null)
        {
            return;
        }

        rePopulateTree(node.getLeft());
        node.setValue(numbers.get(0));
        numbers.remove((int) 0);
        rePopulateTree(node.getRight());
    }

    private void populateNumbersInorder(Node node)
    {
        if (node == null)
        {
            return;
        }

        populateNumbersInorder(node.getLeft());
        numbers.add(node.getValue());
        populateNumbersInorder(node.getRight());
    }
}

class BinaryTree
{
    final private Integer arrayLength;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.arrayLength = array.length;

        List<Node> list = new ArrayList<>();
        this.head = new Node(array[0]);
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