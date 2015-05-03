// Find each pair in BST - which adds up to given number k
// telephonic2, q2, set10, amazon

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        BST tree = new BST();
        tree.insert(20);
        tree.insert(11);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(40);
        tree.insert(3);
        tree.insert(9);
        tree.insert(1);
        tree.insert(2);

        tree.print();

        PairFinder pf = new PairFinder(tree, 20);
        pf.find();
    }
}

class PairFinder
{
    private Node head;
    private Integer target;

    public PairFinder(BST tree, Integer target)
    {
        head = tree.getHead();
        this.target = target;
    }

    public void find()
    {
        Node startNode = getNodeLessThanTarget();

        if (startNode == null)
        {
            System.out.println("no values in tree less than target");
            return;
        }

        Integer[] array = convertTreeToSortedArray(startNode);

        Integer start = 0;
        Integer end = array.length - 1;

        while (start < end)
        {
            Integer tempSum = array[start] + array[end];
            if (tempSum == target)
            {
                System.out.println("found pair " + array[start] + " " + array[end]);
                --end;
                ++start;
            }
            else if (tempSum > target)
            {
                --end;
            }
            else
            {
                ++start;
            }

        }
    }

    private Integer[] convertTreeToSortedArray(Node node)
    {
        List<Integer> list = new ArrayList<>();

        addNodesToList(node, list);

        Collections.sort(list);

        return list.toArray(new Integer[list.size()]);
    }

    private void addNodesToList(Node node, List<Integer> list)
    {
        if (node == null)
        {
            return;
        }

        list.add(node.getValue());

        addNodesToList(node.getLeft(), list);
        addNodesToList(node.getRight(), list);
    }

    private Node getNodeLessThanTarget()
    {
        Node cursor = head;

        while(cursor != null)
        {
            if (cursor.getValue() < target)
            {
                return cursor;
            }

            cursor = cursor.getLeft();
        }

        return null;
    }


}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void print()
    {
        print(head);
    }

    private void print(Node node)
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
        if (node.getValue() > value)
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
        else if (node.getValue() < value)
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