// bottom view of BT

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {20,8,22,5,3,4,25,null,null,10,14};
        Integer[] ar = {1,2,3,4,5,
        null,null,null,null,
        6,7};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.make(ar);
        btUtil.print(tree);
        System.out.println("--------");

        BottomPrinter bp = new BottomPrinter();
        bp.print(tree);
    }
}

class BottomPrinter
{
    private Map<Integer, Integer> horizDepthDistance;
    private Map<Integer, Node> horizNode;
    public void print(Node head)
    {
        horizDepthDistance = new HashMap<>();
        horizNode = new HashMap<>();
        findDepthLeaves(head, 0, 0);
        for (Map.Entry<Integer, Node> entry : horizNode.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue().getValue() + " ");
        }
    }

    private void findDepthLeaves(Node node, Integer depth, Integer horiz)
    {
        if (node == null)
        {
            return;
        }

        if (isLeaf(node))
        {
            Integer prevDepth = horizDepthDistance.get(horiz);
            if (prevDepth == null || prevDepth < depth)
            {
                horizDepthDistance.put(horiz, depth);
                horizNode.put(horiz, node);
            }

            return;
        }

        findDepthLeaves(node.getLeft(), depth+1, horiz-1);
        findDepthLeaves(node.getRight(), depth+1, horiz+1);
    }

    private boolean isLeaf(Node node)
    {
        return node.getLeft() == null && node.getRight() == null;
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

            Node parentNode = nodes[getParentIndex(i)];
            if (i%2 == 0)
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
    final private Integer value;

    public Node (Integer v)
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