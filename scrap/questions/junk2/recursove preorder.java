// recursove preorder

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,20,30,40,50,60,70, null,null, 90,100};
        BTUtil btUtil= new BTUtil();
        Node head = btUtil.make(ar);
        (new RecursivePreoreder()).print(head);
    }
}

class RecursivePreoreder
{
    Stack<Node> stack;
    public void print(Node head)
    {
        stack = new Stack<>();
        pushFor(head);
        while (stack.size() > 0)
        {
            Node top = stack.pop();
            if(top.getRight() != null)
            {
                pushFor(top.getRight());
            }
        }
    }

    private void pushFor(Node node)
    {
        Node cursor = node;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            stack.push(cursor);
            cursor = cursor.getLeft();
        }
    }
}

class BTUtil
{
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

    public Node make(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parent = nodes[getParentIndex(i)];
            if(i %2 == 0)
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
            else
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
        }


        return nodes[0];
    }

    private int getParentIndex(Integer i)
    {
        return (i-1)/2;
    }
}

class Node
{
    private Node left, right;
    final private int value;

    public Node(Integer i)
    {
        value = i;
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