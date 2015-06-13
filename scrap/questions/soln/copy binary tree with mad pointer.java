// copy binary tree with mad pointer

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10,8,7,6,5,9,10,3,
                        null, null,
                        4,11,12};

        BinaryTree bt = new BinaryTree(array);
        bt.coenctMad(8,12);
        bt.coenctMad(11, 7);
        bt.coenctMad(6, 5);
        BinaryTree.print(bt.getHead());
        System.out.println("-----------------");

        BinaryDuplicator bd = new BinaryDuplicator();
        Node copyHead = bd.duplicate(bt.getHead());
        BinaryTree.print(copyHead);

        System.out.println(bt.getHead() + " " + copyHead);

    }
}

class BinaryDuplicator
{
    public Node duplicate(Node head)
    {
        copyNodes(head);
        connectMad(head);

        Node copyHead = head.getLeft();
        ripTree(head);

        return copyHead;
    }

    private void ripTree(Node origNode)
    {
        if (origNode == null)
        {
            return;
        }

        Node copyNode = origNode.getLeft();

        origNode.setLeft(copyNode.getLeft());
        if (copyNode.getLeft() != null)
        {
            copyNode.setLeft(copyNode.getLeft().getLeft());
        }
        if (origNode.getRight() != null)
        {
            copyNode.setRight(origNode.getRight().getLeft());
        }

        ripTree(origNode.getLeft());
        ripTree(origNode.getRight());
    }

    private void connectMad(Node node)
    {
        if (node == null)
        {
            return;
        }

        if (node.getMad() != null)
        {
            Node copyNode = node.getLeft();
            copyNode.setMad(node.getMad().getLeft());
        }

        connectMad(node.getLeft().getLeft());
        connectMad(node.getRight());
    }

    private void copyNodes(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = new Node(node.getValue());
        copyNode.setLeft(node.getLeft());
        node.setLeft(copyNode);

        copyNodes(node.getLeft().getLeft());
        copyNodes(node.getRight());
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

            Integer rightChildIndex = getRightChldIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }

    }

    public void coenctMad(Integer value1, Integer value2)
    {
        Node node1 = fetchNode(head, value1);
        Node node2 = fetchNode(head, value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant find nodes in binary tree");
            return;
        }

        node1.setMad(node2);
    }

    private Node fetchNode(Node node, Integer value)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }

        Node leftNode = fetchNode(node.getLeft(), value);
        return (leftNode == null) ? fetchNode(node.getRight(), value) : leftNode;
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
        System.out.print(" || ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    private Integer getRightChldIndex(Integer index)
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
    private Node left, right, mad;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
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