ancestors in bt

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,6,7,8,
        null,null,
        9,
        null,null,
        10};

        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);

        (new AncestorFinder()).find(head, 9);
    }
}

class AncestorFinder
{
    private Stack<Node> stack;
    private Set<Node> leftDone, rightDone;

    public void find(Node head, int target)
    {
        stack = new Stack<>();
        leftDone = new HashSet<>();
        rightDone = new HashSet<>();

        leftPush(head);

        while (stack.size() > 0)
        {
            if (stack.peek().getValue() == target)
            {
                printStack();
                break;
            }

            Node topNode = stack.pop();
            if (leftDone.contains(topNode) && rightDone.contains(topNode))
            {
                System.out.println("spitting " + topNode.getValue() + " ");
            }
            else if (leftDone.contains(topNode) && !rightDone.contains(topNode))
            {
                rightPush(topNode);
            }
        }
    }

    private void printStack()
    {
        System.out.println("found ");
        while (stack.size() > 0)
        {
            System.out.print(stack.pop().getValue() + " ");
        }
    }

    private void rightPush(Node node)
    {
        stack.push(node);
        rightDone.add(node);

        leftPush(node.getRight());
    }

    private void leftPush(Node node)
    {
        Node cursor = node;
        while (cursor != null)
        {
            stack.push(cursor);
            leftDone.add(cursor);

            cursor = cursor.getLeft();
        }
    }
}

class BTUtil
{

    public Node makeFor(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {continue;}

            Node parent = nodes[getParentIndex(i)];
            if (i%2 == 0)
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

    public void setValue(Integer value)
    {
        this.value = value;
    }
}