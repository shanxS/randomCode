// recursive level order print

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,7,5,null,6,null,9,null,null,1,11,null,null,4};
        BTMaker btMaker = new BTMaker();
        Node tree = btMaker.make(ar);
        btMaker.print(tree);

        System.out.println();

        LevelOrderPrint lop = new LevelOrderPrint();
        lop.print(tree);
    }
}

class LevelOrderPrint
{
    public void print(Node head)
    {
        Queue<Node> que = new ArrayDeque<>();
        que.add(head);

        while (que.size() > 0)
        {
            Node thisNode = que.remove();
            System.out.print(thisNode.getValue() + " ");

            if (thisNode.getLeft() != null)
            {
                que.add(thisNode.getLeft());
            }
            if (thisNode.getRight() != null)
            {
                que.add(thisNode.getRight());
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