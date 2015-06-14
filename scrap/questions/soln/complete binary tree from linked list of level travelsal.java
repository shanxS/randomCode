// complete binary tree from linked list of level travelsal

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        LL ll = new LL();
        for (Integer i = 1; i <= 12; ++i)
        {
            ll.insert(i);
        }
        LL.print(ll.getHead());

        BinaryTree bt = new BinaryTree(ll.getHead());
        bt.print();
    }
}

class BinaryTree
{
    private Node head;

    public BinaryTree(LLNode llHead)
    {
        head = new Node(llHead.getValue());
        List<Node> list = new ArrayList<>();
        list.add(head);

        LLNode cursor = llHead.getNext();

        while(cursor != null)
        {
            Node node = list.get(0);

            Node leftNode = new Node(cursor.getValue());
            list.add(leftNode);
            node.setLeft(leftNode);

            cursor = cursor.getNext();
            if (cursor != null)
            {
                Node rightNode = new Node(cursor.getValue());
                node.setRight(rightNode);
                list.add(rightNode);

                cursor = cursor.getNext();
            }

            list.remove(node);
        }
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

class LL
{
    private LLNode head;

    public LL()
    {
        this.head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new LLNode(value);
        }
        else
        {
            LLNode cursor = head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new LLNode(value));
        }
    }

    public static void print(LLNode node)
    {
        LLNode cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }

    public LLNode getHead()
    {
        return head;
    }
}

class LLNode
{
    private LLNode next;
    private Integer value;

    public LLNode(Integer value)
    {
        this.value = value;
    }

    public LLNode getNext()
    {
        return next;
    }

    public void setNext(LLNode next)
    {
        this.next = next;
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