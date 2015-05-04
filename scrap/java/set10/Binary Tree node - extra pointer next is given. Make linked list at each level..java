// in Binary Tree node - extra pointer "next" is given. Make linked list at each level.
// telephonic1, q1, set10 amazon

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(11);
        bst.insert(25);
        bst.insert(40);

        bst.print();

        BSTConnetor bstConnetor = new BSTConnetor(bst);
        bstConnetor.connect();

        Node cursor = bst.getHead();
        System.out.println("printing link list");
        System.out.println();
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}

class BSTConnetor
{
    private Node head;
    private List<Node> que;

    public BSTConnetor(BST tree)
    {
        head = tree.getHead();
        que = new ArrayList<>();
    }

    public void connect()
    {
        populateQue();

        for (Integer i=0; i<que.size()-1; ++i)
        {
            Node parentNode = que.get(i);
            Node childNode = que.get(i+1);

            parentNode.setNext(childNode);
        }
    }

    private void populateQue()
    {
        que.add(head);
        Integer startIndex = 0;
        Integer endIndex = que.size();

        while(startIndex < endIndex)
        {
            addChild(startIndex, endIndex);
            startIndex = endIndex;
            endIndex = que.size();
        }

    }

    private void addChild(Integer startIndex, Integer endIndex)
    {
        for (Integer i=startIndex; i<endIndex; ++i)
        {
            Node node = que.get(i);

            if (node.getLeft() !=  null)
            {
                que.add(node.getLeft());
            }

            if (node.getRight() != null)
            {
                que.add(node.getRight());
            }
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
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


    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right, next;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.next = null;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node next)
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