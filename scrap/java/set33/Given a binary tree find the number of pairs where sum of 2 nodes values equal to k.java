// Given a binary tree find the number of pairs where sum of 2 nodes values equal to k
// code question 18
// r1, q2, set33

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{32, 20, 40, 15, 25, 35, 50, 5, 45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        SumFinder sf = new SumFinder();
        //System.out.print(sf.find(tree.getHead(), 20));
        //System.out.print(sf.find(tree.getHead(), 32));
        System.out.print(sf.find(tree.getHead(), 64));
    }
}

class SumFinder
{
    private Integer target;
    private Stack<Node> leftStack, rightStack;
    private Node head;

    public Boolean find(Node head, Integer target)
    {
        this.target = target;
        this.head = head;
        populateStacks();

        while(leftStack.size() > 0
                && rightStack.size() > 0)
        {
            Node leftNode = leftStack.peek();
            Node rightNode = rightStack.peek();

            Integer thisSum = leftNode.getValue() + rightNode.getValue();
            if (thisSum < target)
            {
                leftStack.pop();
                if (leftNode.getRight() != null)
                {
                    leftStack.push(leftNode.getRight());
                }
            }
            else if (thisSum > target)
            {
                rightStack.pop();
                if (rightNode.getLeft() != null)
                {
                    rightStack.push(rightNode.getLeft());
                }
            }
            else
            {
                if (leftNode == rightNode)
                {
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    private void populateStacks()
    {
        leftStack = new Stack<>();
        Node leftCursor = head;
        while(leftCursor != null)
        {
            leftStack.push(leftCursor);
            leftCursor = leftCursor.getLeft();
        }

        rightStack = new Stack<>();
        Node rightCursor = head;
        while(rightCursor != null)
        {
            rightStack.push(rightCursor);
            rightCursor = rightCursor.getRight();
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
            System.out.print(node.getRight().getRight());
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