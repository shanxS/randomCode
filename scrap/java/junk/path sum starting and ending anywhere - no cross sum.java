// path sum starting and ending anywhere - no cross sum

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,20,30,60,70,50,40};
        BST tree = new BST(ar);
        tree.print(tree.getHead());

        SumFinder sf = new SumFinder();
        sf.find(tree.getHead(), 80);
    }
}

class SumFinder
{
    private Integer sum;
    public void find(Node head, Integer sum)
    {
        this.sum = sum;
        findFor (head, new ArrayList<Integer>());
    }

    private void findFor(Node node, List<Integer> path)
    {
        if (node == null)
        {
            return;
        }

        List<Integer> thisPath = new ArrayList<>(path);
        thisPath.add(node.getValue());
        Integer thisSum = 0;
        for (Integer i=thisPath.size()-1; i>=0; --i)
        {
            thisSum += thisPath.get(i);
            if (thisSum == sum)
            {
                print(thisPath.subList(i, thisPath.size()));
            }
        }

        findFor(node.getLeft(), thisPath);
        findFor(node.getRight(), thisPath);
    }

    private void print(List<Integer> integers)
    {
        for (Integer i : integers)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

class BST
{
    private Node head;

    public BST(Integer [] ar)
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
            if (i%2 != 0)
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
            else
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
        }

        head = nodes[0];
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
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