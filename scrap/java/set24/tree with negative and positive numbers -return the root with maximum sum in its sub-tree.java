//  tree with negative and positive numbers -return the root with maximum sum in its sub-tree
// r3, q4, set24

import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static Integer nodeValue = null;
    private static Integer maxSubTreeSum = Integer.MIN_VALUE;

    public  static void main(String[] er)
    {
        Integer[] array = new Integer[]{2,-1,4,null,3,6,-4,null,null,null,null,null,null,null,11};
        BTMaker maker = new BTMaker();
        Node head = maker.makeBT(array);
        BTMaker.print(head);

        getSubTreeSum(head);
        System.out.print("ndoe:" + nodeValue + " value:" + maxSubTreeSum);
    }

    private static Integer getSubTreeSum(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer leftSubTreeSum = getSubTreeSum(node.getLeft());
        if (leftSubTreeSum > maxSubTreeSum)
        {
            maxSubTreeSum = leftSubTreeSum;
            nodeValue = node.getValue();
        }

        Integer rightSubTreeSum = getSubTreeSum(node.getRight());
        if (rightSubTreeSum > maxSubTreeSum)
        {
            maxSubTreeSum = rightSubTreeSum;
            nodeValue = node.getValue();
        }

        return node.getValue() + leftSubTreeSum + rightSubTreeSum;
    }
}

class BTMaker
{
    final private Integer invalidEntry = null;
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

    public Node makeBT(Integer[] array)
    {
        this.array = array;
        this.head = new Node(array[0]);
        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer i=0, listCursor = 0; i<array.length; ++i)
        {
            if (array[i] == invalidEntry)
            {
                continue;
            }

            Node node = list.get(listCursor);
            Node leftChild = getLeftChild(i);
            if (leftChild != null)
            {
                node.setLeft(leftChild);
                list.add(leftChild);
            }

            Node rightChild = getRightChild(i);
            if (rightChild != null)
            {
                node.setRight(rightChild);
                list.add(rightChild);
            }

            ++listCursor;
        }

        return head;
    }

    private Node getRightChild(Integer parentIndex)
    {
        Integer rightChildIndex = getRightChildIndex(parentIndex);
        if (isValidIndex(rightChildIndex) && isValidEntry(array[rightChildIndex]))
        {
            return new Node(array[rightChildIndex]);
        }

        return null;
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return (2*parentIndex)+2;
    }

    private Node getLeftChild(Integer parentIndex)
    {
        Integer leftChildIndex = getLeftChildIndex(parentIndex);
        if (isValidIndex(leftChildIndex) && isValidEntry(array[leftChildIndex]))
        {
            return new Node(array[leftChildIndex]);
        }

        return null;
    }

    private boolean isValidEntry(Integer entry)
    {
        return (entry == invalidEntry) ? false : true;
    }

    private boolean isValidIndex(Integer index)
    {
        if (index < 0 || index >= array.length)
        {
            return false;
        }

        return true;
    }

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (2*parentIndex)+1;
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