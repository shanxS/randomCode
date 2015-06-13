// Check if two nodes are cousins in a Binary Tree
// code question: 94

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

        Integer[] array = {6,3,8,1,5,7,11,
        null,null,
                null,null,
                null,null,
        9,13};

//        Integer[] array = {20,8,22,4,12,
//        null,null,
//                null,null,
//        10,14};

        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("---------------------------");

        CousinFinder bv = new CousinFinder();
//        System.out.print(bv.areCousins(bt.getHead(), 1, 7));
//        System.out.print(bv.areCousins(bt.getHead(), 5, 7));
        System.out.print(bv.areCousins(bt.getHead(), 11, 7));
    }
}

class CousinFinder
{
    Node parent1, parent2;
    Integer depth1, depth2;
    Integer value1, value2;

    public Boolean areCousins(Node head, Integer value1, Integer value2)
    {
        this.parent1 = null;
        this.parent2 = null;
        this.depth1 = null;
        this.depth2 = null;
        this.value1 = value1;
        this.value2 = value2;

        setup(head, null, 1);

        if (depth1 == depth2 && (parent1 != null && parent2 != null) && (parent1.getValue() != parent2.getValue()))
        {
            return true;
        }

        return false;
    }

    private void setup(Node node, Node parent, Integer depth)
    {
        if (node == null || (depth1 != null && depth2 != null))
        {
            return;
        }

        if (node.getValue() == value1)
        {
            depth1 = depth;
            parent1 = parent;
        }
        else if (node.getValue() == value2)
        {
            depth2 = depth;
            parent2 = parent;
        }

        setup(node.getLeft(), node, depth+1);
        setup(node.getRight(), node, depth+1);
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