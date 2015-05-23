// LCA of binary tree
// r3, q3, set27

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 9, 4, 5, 11,
                null,
                10, 8, 7, 12, 6
        };

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        LCAFinder lcaF = new LCAFinder();
        System.out.print("-----" + lcaF.find(bt, 12, 11).getValue());
    }
}

class LCAFinder
{
    List<Node> nodes1, nodes2;
    public Node find(BinaryTree tree, Integer value1, Integer value2)
    {
        this.nodes1 = new ArrayList<>();
        find(tree.getHead(), nodes1, value1);

        this.nodes2 = new ArrayList<>();
        find(tree.getHead(), nodes2, value2);

        Integer cursor1=0, cursor2=0;
        while(cursor1 < nodes1.size() && cursor2 < nodes2.size())
        {
            if (nodes2.get(cursor2).getValue() != nodes1.get(cursor1).getValue())
            {
                break;
            }
            ++cursor1;
            ++cursor2;
        }

        return nodes1.get(cursor1-1);
    }

    private Boolean find(Node node, List<Node> nodes, Integer value)
    {
        if (node == null)
        {
            return false;
        }

        nodes.add(node);

        if (node.getValue() == value)
        {
            return true;
        }
        if (find(node.getLeft(), nodes, value)
                || find(node.getRight(), nodes, value))
        {
            return true;
        }

        nodes.remove(node);
        return false;
    }
}

class BinaryTree
{
    private Node head;
    private Integer[] array;

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

    public BinaryTree(Integer[] array)
    {
        this.array = array;
        this.head = new Node(array[0]);

        List<Node> nodes = new ArrayList<>();
        nodes.add(head);

        for (Integer arrayCursor = 0; arrayCursor < array.length; ++arrayCursor)
        {
            if (!isValidEntry(array[arrayCursor]))
            {
                continue;
            }

            Node node = nodes.get(0);

            Integer leftChildIndex = getLeftChlidIndex(arrayCursor);
            if(isValidIndex(leftChildIndex) && isValidEntry(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                nodes.add(leftNode);
            }

            Integer rightChildIndex = getRightChlidIndex(arrayCursor);
            if(isValidIndex(rightChildIndex) && isValidEntry(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                nodes.add(rightNode);
            }

            nodes.remove(0);
        }
    }

    private Integer getRightChlidIndex(Integer index)
    {
        return (2*index) + 2;
    }

    private Integer getLeftChlidIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private boolean isValidEntry(Integer value)
    {
        return (value != null);
    }


    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
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