// topological sort for tree
// code question 39
// https://www.cs.usfca.edu/~galles/visualization/TopoSortDFS.html
// http://www.geeksforgeeks.org/topological-sorting/

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 50};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        tree.printTopoSort();

    }
}

class BST
{
    private Node head;
    private Stack<Node> stack;

    public BST()
    {
        this.head = null;
        this.stack = new Stack<>();
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

    public void printTopoSort()
    {
        setStack(head);

        while (stack.size() > 0)
        {
            System.out.print(stack.pop().getValue() + " ");
        }
    }

    private void setStack(Node node)
    {
        if (node == null)
        {
            return;
        }

        setStack(node.getLeft());
        setStack(node.getRight());

        stack.push(node);
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
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node (Integer value)
    {
        this.value = value;
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