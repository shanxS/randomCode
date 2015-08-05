// Merge Two Balanced Binary Search Trees

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar1 = {100,50,300,20,70};
        BST bst1 = new BST();
        for (Integer i : ar1)
        {
            bst1.insert(i);
        }
        bst1.print(bst1.getHead());
        System.out.println("---------------");

        Integer[] ar2 = {80,40,120};
        BST bst2 = new BST();
        for (Integer i : ar2)
        {
            bst2.insert(i);
        }
        bst1.print(bst1.getHead());
        System.out.println("---------------");

        Node merged = (new Merger()).merge(bst1.getHead(), bst2.getHead());
        bst1.print(merged);
    }
}

class Merger
{
    private Stack<Node> stack1, stack2;
    List<Node> seq;
    public Node merge(Node head1, Node head2)
    {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        seq = new ArrayList<>();

        pushFor(stack1, head1);
        pushFor(stack2, head2);


        while(stack1.size() > 0 || stack2.size() > 0)
        {
            if (stack1.size() > 0 && stack2.size() > 0)
            {
                if (stack2.peek().getValue() < stack1.peek().getValue())
                {
                    Node node = stack2.pop();
                    seq.add(node);
                    pushFor(stack2, node.getRight());
                }
                else
                {
                    Node node = stack1.pop();
                    seq.add(node);
                    pushFor(stack1, node.getRight());
                }
            }

            while (stack2.size() > 0)
            {
                Node node = stack2.pop();
                seq.add(node);
                pushFor(stack2, node.getRight());
            }

            while (stack1.size() > 0)
            {
                Node node = stack1.pop();
                seq.add(node);
                pushFor(stack1, node.getRight());
            }
        }

        return makeTree(0, seq.size()-1);

    }

    private Node makeTree(int start, int end)
    {
        if (start == end)
        {
            return new Node(seq.get(start).getValue());
        }
        else if (start+1 == end)
        {
            Node returnNode = new Node(seq.get(start).getValue());
            returnNode.setRight(new Node(seq.get(end).getValue()));

            return returnNode;
        }

        Integer mid = start + ((end-start)/2);
        Node thisNode = new Node(seq.get(mid).getValue());
        thisNode.setRight(makeTree(mid + 1, end));
        thisNode.setLeft(makeTree(start, mid-1));

        return thisNode;
    }

    private void pushFor(Stack<Node> stack, Node node)
    {
        Node cursor = node;
        while (cursor != null)
        {
            stack.push(cursor);
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

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if(node.getLeft() != null)
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private int value;

    public Node(Integer i)
    {
        value = i;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
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