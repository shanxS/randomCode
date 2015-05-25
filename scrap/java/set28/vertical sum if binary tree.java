// vertical sum if binary tree
// r1, q1,set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{30, 20, 50, 10, 25, 40, 55};
        Integer[] array = new Integer[]{
                1,2,3,4,5,8,
                null,null,
                12,11,6,9,10,
                null,null,null,null,null,null,null,null,
                13, 7
        };
        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());

        VerticalSumCalculator vsc = new VerticalSumCalculator();
        vsc.calculate(bt);
    }
}

class VerticalSumCalculator
{
    private Node dll;

    public Node calculate(BinaryTree tree)
    {
        Node head = tree.getHead();
        dll = new Node(head.getValue());
        calculate(head, dll);

        return dll;
    }

    private void calculate(Node treeNode, Node dllNode)
    {
        if (treeNode == null)
        {
            return;
        }

        if (treeNode.getLeft() != null)
        {
            if (dllNode.getLeft() != null)
            {
                Integer previousValue = dllNode.getLeft().getValue();
                dllNode.getLeft().setValue(previousValue + treeNode.getLeft().getValue());
            }
            else
            {
                Node leftDllNode = new Node(treeNode.getLeft().getValue());
                leftDllNode.setRight(dllNode);
                dllNode.setLeft(leftDllNode);
            }

            calculate(treeNode.getLeft(), dllNode.getLeft());
        }

        if (treeNode.getRight() != null)
        {
            if (dllNode.getRight() != null)
            {
                Integer previousValue = dllNode.getRight().getValue();
                dllNode.getRight().setValue(previousValue + treeNode.getRight().getValue());
            }
            else
            {
                Node rightDllNode = new Node(treeNode.getRight().getValue());
                rightDllNode.setLeft(dllNode);
                dllNode.setRight(rightDllNode);
            }

            calculate(treeNode.getRight(), dllNode.getRight());
        }
    }
}

class BinaryTree
{
    private Integer[] array;
    private Node head;

    public static void print(Node node)
    {
        if (node ==  null)
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
        for (Integer arrayCursor = 0; arrayCursor<array.length; ++arrayCursor)
        {
            if (!isValidEntry(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIndex) && isValidEntry(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidEntry(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return (2*index) + 2;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidEntry(Integer value)
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