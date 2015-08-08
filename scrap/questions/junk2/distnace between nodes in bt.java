// distnace between nodes in bt

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {20,8,22,4,12,null,null,null,null,10,14};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
//        util.print(head);
        (new DistanceFinder()).find(head, 14, 22);
    }
}

class DistanceFinder
{
    private List<Node> lca1, lca2;
    private boolean targetFound;

    public void find(Node head, int t1, int t2)
    {
        lca1 = new ArrayList<>();
        targetFound = false;
        findTarget(head, t1, lca1);

        lca2 = new ArrayList<>();
        targetFound = false;
        findTarget(head, t2, lca2);

        System.out.print(calcluateDist(lca1, lca2));
    }

    private int calcluateDist(List<Node> lca1, List<Node> lca2)
    {
        int cursor = 0;
        while (cursor < lca1.size() && cursor < lca2.size()
                && lca1.get(cursor).getValue() == lca2.get(cursor).getValue())
        {
            ++cursor;
        }

        int lca1Size = 0;
        if (lca1.contains(lca2.get(lca2.size()-1)) && lca1.size() > lca2.size())
        {
            lca1Size = lca1.size() - cursor - 1;
        }
        else
        {
            lca1Size = lca1.size() - cursor;
        }

        int lca2Size = 0;
        if (lca2.contains(lca1.get(lca1.size()-1)) && lca1.size() < lca2.size())
        {
            lca2Size = lca2.size() - cursor - 1;
        }
        else
        {
            lca2Size = lca2.size() - cursor;
        }

        int dit = cursor + (lca1Size) + (lca2Size);
        return dit;
    }

    private void findTarget(Node node, int target, List<Node> lca)
    {
        if (node == null || targetFound)
        {
            return;
        }

        if (node.getValue() == target)
        {
            targetFound = true;
            lca.add(node);

            return;
        }

        lca.add(node);
        findTarget(node.getLeft(), target, lca);
        findTarget(node.getRight(), target, lca);
        if (!targetFound)
        {
            lca.remove(node);
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