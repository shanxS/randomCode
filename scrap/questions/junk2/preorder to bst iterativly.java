// preorder to bst iterativly

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10, 5, 1, 7, 40, 50};


        BSTUtil util = new BSTUtil();
        util.print(util.makeFromPreorder(ar));
    }
}

class BSTUtil
{
    public Node makeFromPreorder(Integer[] ar)
    {
        Stack<Node> stack = new Stack<>();

        Node head = new Node(ar[0]);
        stack.push(head);
        for (Integer i=1; i<ar.length; ++i)
        {
            if (stack.size() > 0 && stack.peek().getValue() < ar[i])
            {
                Node lastPopped = stack.pop();
                while (stack.size() > 0 && stack.peek().getValue() < ar[i])
                {
                    lastPopped = stack.pop();
                }

                lastPopped.setRight(new Node(ar[i]));
                stack.push(lastPopped.getRight());
            }
            else
            {
                Node node = new Node(ar[i]);
                if (stack.size() >  0)
                {
                    stack.peek().setLeft(node);
                }

                stack.push(node);
            }
        }

        return head;
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
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
