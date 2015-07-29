// postorder recursive

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,7,5,null,6,null,9,null,null,1,11,null,null,4};
        BTMaker btMaker = new BTMaker();
        Node tree = btMaker.make(ar);
        btMaker.print(tree);

        System.out.println();

        PostOrderRecursive lop = new PostOrderRecursive();
        lop.print(tree);
    }
}

class PostOrderRecursive
{
    public void print(Node head)
    {
        Map<Integer, Integer> nodeStatus = new HashMap<>();
        final Integer LEFTDONE = 1, RIGHTDONE = 2;

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (stack.size() > 0)
        {
            Node node = stack.peek();
            if (nodeStatus.get(node.getValue()) == null)
            {
                if (node.getRight() == null && node.getLeft() == null)
                {
                    System.out.print(node.getValue() + " ");
                    stack.pop();
                }
                else if (node.getLeft() != null)
                {
                    nodeStatus.put(node.getValue(), LEFTDONE);
                    stack.push(node.getLeft());
                }
                else if (node.getRight() != null)
                {
                    nodeStatus.put(node.getValue(), RIGHTDONE);
                    stack.push(node.getRight());
                }
            }
            else if (nodeStatus.get(node.getValue()) == LEFTDONE)
            {
                if (node.getRight() != null)
                {
                    nodeStatus.put(node.getValue(), RIGHTDONE);
                    stack.push(node.getRight());
                }
                else
                {
                    System.out.print(node.getValue() + " ");
                    stack.pop();
                }
            }
            else if (nodeStatus.get(node.getValue()) == RIGHTDONE)
            {
                System.out.print(node.getValue() + " ");
                stack.pop();
            }
        }
    }
}

class BTMaker
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

            Node parentNode = nodes[getParentIndex(i)];
            if (i % 2 == 0)
            {
                parentNode.setRight(new Node(ar[i]));
                nodes[i] = parentNode.getRight();
            }
            else
            {
                parentNode.setLeft(new Node(ar[i]));
                nodes[i] = parentNode.getLeft();
            }
        }

        return nodes[0];
    }

    private int getParentIndex(Integer index)
    {
        return (index-1)/2;
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