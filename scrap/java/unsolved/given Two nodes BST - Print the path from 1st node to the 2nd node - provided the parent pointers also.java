// given Two nodes BST - Print the path from 1st node to the 2nd node - provided the parent pointers also
// unsolved q8

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{70, 50, 100, 25, 55, 80, 150, 75, 110, 160, 105};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        PathPrinter pp = new PathPrinter();
        //pp.print(tree.findNode(105), tree.findNode(75));
        //pp.print(tree.findNode(105), tree.findNode(25));
        //pp.print(tree.findNode(100), tree.findNode(70));
        pp.print(tree.findNode(75), tree.findNode(70));

    }
}

class PathPrinter
{
    public void print(Node node1, Node node2)
    {
        if (node2.getValue() < node1.getValue())
        {
            Node tmp = node2;
            node2 = node1;
            node1 = tmp;
        }

        List<Node> list1 = new ArrayList<>();
        list1.add(node1);

        List<Node> list2 = new ArrayList<>();
        list2.add(node2);

        TreeSet<Integer> cache = new TreeSet<>();
        cache.add(node1.getValue());
        cache.add(node2.getValue());

        while(node1.getParent() != null || node2.getParent() != null)
        {
            if (node1.getParent() != null)
            {
                Node parent = node1.getParent();
                if (!cache.add(parent.getValue()))
                {
                    break;
                }
                list1.add(parent);
                node1 = parent;
            }

            if (node2.getParent() != null)
            {
                Node parent = node2.getParent();
                if (!cache.add(parent.getValue()))
                {
                    break;
                }
                list2.add(parent);
                node2 = parent;
            }
        }

        for (Node node : list1)
        {
            System.out.print(node.getValue() + " ");
        }
        Collections.reverse(list2);
        for (Node node : list2)
        {
            System.out.print(node.getValue() + " ");
        }
    }

}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                Node leftNode = new Node(value);
                node.setLeft(leftNode);
                leftNode.setParent(node);
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                Node rightNode = new Node(value);
                node.setRight(rightNode);
                rightNode.setParent(node);
            }
        }
    }

    public Node findNode(Integer target)
    {
        return find(head, target);
    }

    private Node find(Node node, Integer target)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue().intValue() == target.intValue())
        {
            return node;
        }
        else if (node.getValue() > target)
        {
            return find(node.getLeft(), target);
        }
        else if (node.getValue() < target)
        {
            return find(node.getRight(), target);
        }

        return null;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node parent, left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
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