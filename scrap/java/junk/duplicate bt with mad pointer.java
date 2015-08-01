// duplicate bt with mad pointer

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,4,6,7,null,8,9};
        Map<Integer, Integer> connections = getConnections();

        BTUtil btUtil = new BTUtil();
        Tree tree = btUtil.make(ar);
        btUtil.connectMad(connections, tree);
        btUtil.print(tree.getHead());

        BTCopier btCopier = new BTCopier();
        System.out.println("------------");
        btUtil.print(btCopier.copy(tree.getHead()));
    }

    public static Map<Integer, Integer> getConnections()
    {
        Map<Integer, Integer> connections = new HashMap<>();

        connections.put(7,4);
        connections.put(6,2);
        connections.put(8,9);

        return connections;
    }
}

class BTCopier
{
    public Node copy(Node head)
    {
        BTUtil btUtil = new BTUtil();

        duplicateNodes(head);

        connectMad(head);
        System.out.println("after connect");
        btUtil.print(head);
        System.out.println("------------");

        Node origHead = head;
        Node dupHead = head.getLeft();
        split(origHead, dupHead);

        return dupHead;
    }

    private void split(Node orig, Node dup)
    {
        if (orig == null)
        {
            return;
        }

        Node origLeft = dup.getLeft();
        if (origLeft != null)
        {
            orig.setLeft(origLeft);
            dup.setLeft(origLeft.getLeft());
            split(origLeft, dup.getLeft());
        }

        Node origRight = orig.getRight();
        if (origRight != null)
        {
            Node dupRight = origRight.getLeft();
            dup.setRight(dupRight);
            split(origRight, dupRight);
        }
    }


    private void connectMad(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = node.getLeft();
        if (node.getMad() != null)
        {
            copyNode.setMad(node.getMad().getLeft());
        }

        connectMad(copyNode.getLeft());
        connectMad(node.getRight());
    }

    private void duplicateNodes(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node prevLeft = node.getLeft();
        Node copyNode = new Node(node.getValue());
        node.setLeft(copyNode);
        copyNode.setLeft(prevLeft);

        duplicateNodes(prevLeft);
        duplicateNodes(node.getRight());
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
        System.out.print(" | ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public void connectMad(Map<Integer, Integer> connections, Tree tree)
    {
        for (Map.Entry<Integer, Integer> entry : connections.entrySet())
        {
            Node parentNode = tree.getNodeRef().get(entry.getKey());
            Node childNode = tree.getNodeRef().get(entry.getValue());
            parentNode.setMad(childNode);
        }
    }

    public Tree make(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        Map<Integer, Node> nodeRef = new HashMap<>();
        nodes[0] = new Node(ar[0]);
        nodeRef.put(ar[0], nodes[0]);

        for (Integer i=1; i<ar.length; ++ i)
        {
            if(ar[i] == null)
            {
                continue;
            }

            Node parentNode = nodes[getParentIndex(i)];
            Node newNode = new Node(ar[i]);
            nodeRef.put(ar[i], newNode);
            if (i%2 == 0)
            {
                parentNode.setRight(newNode);
                nodes[i] = newNode;
            }
            else
            {
                parentNode.setLeft(newNode);
                nodes[i] = newNode;
            }
        }


        return new Tree(nodeRef, nodes[0]);
    }

    private int getParentIndex(Integer index)
    {
        return (index-1)/2;
    }
}

class Node
{
    private Node left, right, mad;
    final private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
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

class Tree
{
    private Map<Integer, Node> nodeRef;
    private Node head;

    public Tree(Map<Integer, Node> nodeRef, Node head)
    {
        this.nodeRef = nodeRef;
        this.head = head;
    }

    public Map<Integer, Node> getNodeRef()
    {
        return nodeRef;
    }

    public void setNodeRef(Map<Integer, Node> nodeRef)
    {
        this.nodeRef = nodeRef;
    }

    public Node getHead()
    {
        return head;
    }

    public void setHead(Node head)
    {
        this.head = head;
    }
}