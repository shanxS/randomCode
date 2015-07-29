// recursive inorder traversal

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

        InorderRecursive lop = new InorderRecursive();
        lop.print(tree);
    }
}

class InorderRecursive
{
    public void print(Node head)
    {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s2.push(head);

        while (s1.size() > 0 || s2.size() > 0)
        {
            if (s2.size() > 0)
            {
                Node s2Node = s2.pop();
                if (s2Node.getLeft() != null)
                {
                    s1.push(s2Node);
                    s2.push(s2Node.getLeft());
                }
                else
                {
                    System.out.print(s2Node.getValue() + " ");
                    if (s2Node.getRight() != null)
                    {
                        s2.push(s2Node.getRight());
                    }
                }
            }
            else if (s1.size() > 0)
            {
                Node s1Node = s1.pop();
                System.out.print(s1Node.getValue() + " ");
                if (s1Node.getRight() != null)
                {
                    s2.push(s1Node.getRight());
                }
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