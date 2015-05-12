// boundary traversal nodes of tree
// r1, q3, set17

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30,20,50,10,25,35,55,45,51};
        BinaryTree bt = new BinaryTree();
        for (Integer value : array)
        {
            bt.insert(value);
        }
        BinaryTree.print(bt.getHead());
        BoundaryPrinter bp = new BoundaryPrinter(bt);
        bp.printBondary();
    }
}

class BoundaryPrinter
{
    private Node head;
    private List<Node> nodes, leftNodes, rightNodes;

    public BoundaryPrinter(BinaryTree tree)
    {
        this.head = tree.getHead();
        this.nodes = new ArrayList<>();
        this.leftNodes = new ArrayList<>();
        this.rightNodes = new ArrayList<>();
    }

    public void printBondary()
    {
        nodes.add(head);
        leftNodes.add(head);

        while(nodes.size() > 0)
        {
            Integer staleCount = nodes.size();

            while(staleCount > 0)
            {
                Node node = nodes.get(0);
                nodes.remove(node);

                if (node.getLeft() != null)
                {
                    nodes.add(node.getLeft());
                }

                if (node.getRight() != null)
                {
                    nodes.add(node.getRight());
                }
                --staleCount;
            }

            if (nodes.size() > 1)
            {
                leftNodes.add(nodes.get(0));
                rightNodes.add(nodes.get(nodes.size()-1));
                for (Integer cursor = 1; cursor < nodes.size()-1; ++cursor)
                {
                    if (isLeafNode(nodes.get(cursor)))
                    {leftNodes.add(nodes.get(cursor));}
                }
            }
        }

        for (Node node : leftNodes)
        {
            System.out.print(node.getValue() + " ");
        }
        for (Integer cursor = rightNodes.size()-1; cursor >= 0; --cursor)
        {
            System.out.print(rightNodes.get(cursor).getValue() + " ");
        }

        System.out.println();
    }

    private boolean isLeafNode(Node node)
    {
        return (node.getRight() == null && node.getLeft() == null) ? true : false;
    }
}

class BinaryTree
{
    private Node head;
    private boolean ruleLeftLessThan;

    public BinaryTree()
    {
        this.head = null;
        this.ruleLeftLessThan = true;
    }

    public static void print(Node node)
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

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }

    }

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == ruleLeftLessThan)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if ((node.getValue() > value) != ruleLeftLessThan)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
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

    public Node (Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}