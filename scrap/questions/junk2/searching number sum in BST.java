// searching number sum in BST

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 50, 200, 40, 70, 150, 30, 180};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }
        tree.print(tree.getHead());

        System.out.print((new Searcher()).find(tree.getHead(), 220));
    }
}

class Searcher
{
    Stack<Node> minStack, maxStack;
    public boolean find(Node head, Integer sum)
    {
        minStack = new Stack<>();
        maxStack = new Stack<>();
        setupMinStack(head);
        setupMaxStack(head);

        while (minStack.size()>0 && maxStack.size()>0)
        {
            Integer thisSum = minStack.peek().getValue() + maxStack.peek().getValue();
            if (thisSum.intValue() == sum.intValue())
            {
                return true;
            }
            else if (thisSum > sum)
            {
                Node maxNode = maxStack.pop();
                setupMaxStack(maxNode.getLeft());
            }
            else
            {
                Node minNode = minStack.pop();
                setupMinStack(minNode.getRight());
            }
        }

        return false;
    }

    private void setupMaxStack(Node head)
    {
        Node cursor = head;
        while (cursor != null)
        {
            maxStack.push(cursor);
            cursor = cursor.getRight();
        }
    }

    private void setupMinStack(Node head)
    {
        Node cursor = head;
        while (cursor != null)
        {
            minStack.push(cursor);
            cursor = cursor.getLeft();
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

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
    }

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    public void print(Node node)
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    final private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
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