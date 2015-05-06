// print tree in sprial/zigzag order using 2 stacks
// tech1, q1, set11

import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 15, 30, 10, 18, 25, 40, 2, 19};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());
        SpiralPrinter sp = new SpiralPrinter(tree);
        sp.print();

    }
}

class SpiralPrinter
{
    private final Node head;
    private Stack<Node> stack1, stack2;

    public SpiralPrinter(BST tree)
    {
        this.head = tree.getHead();
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();

        stack1.push(head);
    }

    public void print()
    {
        Boolean flag = false;

        while(stack1.size() > 0 || stack2.size() > 0)
        {
            while (stack1.size() > 0)
            {
                Node node = stack1.pop();
                System.out.print(node.getValue() + " ");

                if (node.getLeft() != null)
                {
                    stack2.push(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    stack2.push(node.getRight());
                }
            }

            while(stack2.size() > 0)
            {
                Node node = stack2.pop();

                System.out.print(node.getValue() + " ");

                if (node.getRight() != null)
                {
                    stack1.push(node.getRight());
                }
                if (node.getLeft() != null)
                {
                    stack1.push(node.getLeft());
                }
            }
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