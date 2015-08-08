// tree from in and level order

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        int[] in = {4, 8, 10, 12, 14, 20, 22};
        List<Integer> inList = new ArrayList<>();
        for (Integer i : in)
        {
            inList.add(i);
        }

        int[] level = {20, 8, 22, 4, 12, 10, 14};
        List<Integer> levelList = new ArrayList<>();
        for (Integer i : level)
        {
            levelList.add(i);
        }

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.make(inList, levelList);
        btUtil.print(head);
    }
}

class BTUtil
{
    private List<Integer> level, in;
    public Node make(List<Integer> in, List<Integer> level)
    {
        this.level = level;
        this.in = in;

        return makeFor(0, in.size()-1);
    }

    private Node makeFor(int start, int end)
    {
        if (start > end)
        {
            return null;
        }
        if (end == start)
        {
            return new Node(in.get(start));
        }

        int topLeevlNodeIndex = getTopLevelNodeIndex(start, end);
        Node thisNode = new Node(in.get(topLeevlNodeIndex));
        Node leftNode = makeFor(start, topLeevlNodeIndex-1);
        Node rightNode = makeFor(topLeevlNodeIndex + 1, end);

        thisNode.setLeft(leftNode);
        thisNode.setRight(rightNode);

        return thisNode;
    }

    private int getTopLevelNodeIndex(int start, int end)
    {
        int minLevelIndex = in.size();
        int minInIndex = -1;

        int cursor = start;
        while (cursor <= end)
        {
            int levelIndex = level.indexOf(in.get(cursor));
            if (minLevelIndex > levelIndex)
            {
                minLevelIndex = levelIndex;
                minInIndex = cursor;
            }

            ++cursor;
        }

        return minInIndex;
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