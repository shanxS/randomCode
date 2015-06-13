// Extract Leaves of a Binary Tree in a Doubly Linked List
// code question: 101

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {6,3,5,2,9,
                        null,
                        4,
                        null,null,
                        7,10};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.println("-------------------");

        LeavesToDLL ns = new LeavesToDLL();
        ns.find(bt.getHead());

        System.out.println("-------------------");
        BinaryTree.print(bt.getHead());

    }
}

class LeavesToDLL
{
    private Node dllHead;

    public void find(Node head)
    {
        dllHead = new Node(Integer.MIN_VALUE);

        findForNode(head, dllHead);

        Node cursor = dllHead;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
    }

    private Boolean findForNode(Node node, Node llNode)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            if (llNode.getValue() == Integer.MIN_VALUE)
            {
                llNode.setValue(node.getValue());
            }
            else
            {
                Node cursor = llNode;
                while (cursor.getRight() != null)
                {
                    cursor = cursor.getRight();
                }

                cursor.setRight(node);
                node.setLeft(llNode);

            }

            return true;
        }

        if (findForNode(node.getLeft(), llNode))
        {
            node.setLeft(null);
        }

        if (findForNode(node.getRight(), llNode))
        {
            node.setRight(null);
        }

        return false;
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

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

            Integer rightChildIndex = getRigthChildIndex(arrayCursor);
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

    private Integer getRigthChildIndex(Integer index)
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

    public Node (Integer value)
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