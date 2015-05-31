// test if binary tree is sum tree
// online q1, set34

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{ 26, 10, 3,4,6, null, 3
            };

        BinaeyTree tree = new BinaeyTree();
        Node head = tree.makeTree(array);
        BinaeyTree.print(head);

        SumTester st = new SumTester();
        System.out.print(st.test(head));
    }
}

class SumTester
{
    private Boolean state;

    public Boolean test(Node head)
    {
        state = true;

        testNode(head);

        return state;
    }

    private Integer testNode(Node node)
    {
        if (!state)
        {
            return 0;
        }

        if (node == null)
        {
            return 0;
        }

        if (isLeafNode(node))
        {
            return node.getValue();
        }

        Integer leftSum = testNode(node.getLeft());
        Integer rightNode = testNode(node.getRight());

        if (node.getValue() != leftSum+rightNode)
        {
            state = false;
            return 0;
        }

        return node.getValue() + leftSum + rightNode;
    }

    private boolean isLeafNode(Node node)
    {
        return (node.getRight() == null && node.getLeft() == null);
    }
}

class BinaeyTree
{
    Integer[] array;
    public Node makeTree(Integer[] array)
    {
        this.array = array;
        Node head = new Node(array[0]);

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

            list.remove(node);
        }

        return head;
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
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