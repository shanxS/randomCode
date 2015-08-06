// copy bst with mad

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {20,30,40,10,70,null,50,null,null,11};
        BTUtil util = new BTUtil();
        Tree tree = util.makeFor(ar);

        Map<Integer, Integer> connected = new HashMap<>();
        connected.put(10,50);
        connected.put(11,40);
        connected.put(30, 20);
        util.connectMad(connected, tree);

        util.print(tree.head);
        System.out.println("--------------");
        util.print((new Copier()).copy(tree.head));
    }
}

class Copier
{
    public Node copy(Node head)
    {
        makeCopies(head);
        connectMad(head);

        Node duplicateHead = head.getLeft();
        split(head, duplicateHead);

        return duplicateHead;
    }

    private void split(Node node, Node duplicateNode)
    {
        if (node == null)
        {
            return;
        }

        Node origLeft = duplicateNode.getLeft();
        if (origLeft != null)
        {
            node.setLeft(origLeft);
            duplicateNode.setLeft(origLeft.getLeft());
            split(node.getLeft(), duplicateNode.getLeft());
        }

        Node origRight = node.getRight();
        if (origRight != null)
        {
            Node duplicateRight = origRight.getLeft();
            duplicateNode.setRight(duplicateRight);

            split(origRight, duplicateRight);
        }
    }

    private void connectMad(Node node)
    {
        if (node == null)
        {
            return;
        }

        if (node.getMad() != null)
        {
            Node copyNode = node.getLeft();
            Node madCopy = node.getMad().getLeft();

            copyNode.setMad(madCopy);
        }

        connectMad(node.getLeft().getLeft());
        connectMad(node.getRight());

    }

    private void makeCopies(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = new Node(node.getValue());
        copyNode.setLeft(node.getLeft());
        node.setLeft(copyNode);

        makeCopies(copyNode.getLeft());
        makeCopies(node.getRight());
    }
}

class BTUtil
{

    public Tree makeFor(Integer[] ar)
    {
        Map<Integer, Node> map = new HashMap<>();

        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);
        map.put(ar[0], nodes[0]);

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

            map.put(ar[i], nodes[i]);
        }

        return new Tree(nodes[0], map);
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
        System.out.print("| ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public void connectMad(Map<Integer, Integer> connected, Tree tree)
    {
        for (Map.Entry<Integer, Integer> entry : connected.entrySet())
        {
            Node from = tree.valueMap.get(entry.getKey());
            Node to = tree.valueMap.get(entry.getValue());

            from.setMad(to);
        }
    }
}

class Tree
{
    public Node head;
    public Map<Integer, Node> valueMap;

    public Tree(Node head, Map<Integer, Node> valueMap)
    {
        this.head = head;
        this.valueMap = valueMap;
    }
}

class Node
{
    private Node left, right, mad;
    private Integer value;

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

    public void setValue(Integer value)
    {
        this.value = value;
    }
}