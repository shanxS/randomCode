// connect node exra pointer with inorder predecessor

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        BinaryTree bt = new BinaryTree(array);
        bt.print();

        InorderConnector ic = new InorderConnector();
        ic.connect(bt.getHead(), null);

        Node cursor = bt.getHead();
        while (cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}

class InorderConnector
{
    public Node connect(Node node, Node parent)
    {
        if (node == null)
        {
            return null;
        }

        Node leftNode = connect(node.getLeft(), parent);
        if (leftNode != null)
        {
            leftNode.setNext(node);
        }
        else if (parent != null)
        {
            parent.setNext(node);
        }

        Node rightNode = connect(node.getRight(), node);
        return (rightNode == null) ? node : rightNode;

    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        this.head = new Node (array[0]);
        this.arrayLength = array.length;

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
        return (index >= 0 && index < arrayLength);
    }

    public Node getHead()
    {
        return head;
    }

    public void print()
    {
        print(head);
    }

    private void print(Node node)
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
    private Node left, right, next;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node next)
    {
        this.next = next;
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