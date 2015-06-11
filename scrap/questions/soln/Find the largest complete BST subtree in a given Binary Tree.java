// Find the largest complete BST subtree in a given Binary Tree

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {5, 2, 4, 1, 3};
        Integer[] array = {50,30,60,5,20,45,70,
            null, null, null, null, null, null,
            65, 80};
        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        BSTFinder finder = new BSTFinder();
        System.out.print(finder.find(bt.getHead()));
    }
}

class BSTFinder
{
    private Integer maxSize;
    private Node maxBSTHead;

    public Integer find(Node head)
    {
        maxSize = Integer.MIN_VALUE;
        maxBSTHead = null;

        findForNode(head);

        return maxSize;
    }

    

    private MinMaxPair findForNode(Node node)
    {
        if (node.getRight() == null && node.getLeft() == null)
        {
            return new MinMaxPair(node.getValue(), node.getValue(), true, 1);
        }

        MinMaxPair leftResult = findForNode(node.getLeft());
        MinMaxPair rightResult = findForNode(node.getRight());

        if (leftResult.isBST && rightResult.isBST
                && leftResult.max < node.getValue()
                && rightResult.min  > node.getValue())
        {
            Integer thisSize = leftResult.size + rightResult.size + 1;
            if (thisSize > maxSize)
            {
                maxBSTHead = node;
                maxSize = thisSize;
            }

            return new MinMaxPair(leftResult.min, rightResult.max, true, thisSize);
        }

        return new MinMaxPair(null, null, false, null);
    }

    private class MinMaxPair
    {
        Integer min, max, size;
        boolean isBST;

        public MinMaxPair(Integer min, Integer max, boolean isBST, Integer size)
        {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
            this.size = size;
        }
    }
}

class BinaryTree
{
    final private Integer arrayLength;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        arrayLength = array.length;
        head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor < arrayLength; ++arrayCursor)
        {
            if (!isValidVaiue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIndex) && isValidVaiue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidVaiue(array[rightChildIndex]))
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
        return (index*2) + 1;
    }

    private boolean isValidVaiue(Integer value)
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