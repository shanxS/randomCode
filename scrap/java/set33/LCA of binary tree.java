// LCA of binary tree

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 15, 25, 35, 50, 5,
                null,null,null,null,null,
                45};
        BinaryTree tree = new BinaryTree();
        Node head = tree.createTree(array);
        BinaryTree.print(head);


        LCAFinder finder = new LCAFinder();
        System.out.print(finder.find(head, 15, 25).getValue());
//        System.out.print(finder.find(head, 15, 45).getValue());
//        System.out.print(finder.find(head, 35, 45).getValue());


    }
}

class LCAFinder
{
    List<Node> path1, path2;

    public Node find(Node head, Integer value1, Integer value2)
    {
        path1 = new ArrayList<>();
        path2 = new ArrayList<>();

        findPath(head, value1, path1);
        findPath(head, value2, path2);

        Integer path1Counter = 0;
        Integer path2Counter = 0;

        Node LCA = null;
        while((path1Counter < path1.size() && path2Counter < path2.size())
                && (path1.get(path1Counter).getValue()) == (path2.get(path2Counter).getValue()))
        {
            LCA = path1.get(path1Counter);
            ++path1Counter;
            ++path2Counter;
        }

        return LCA;
    }

    private Boolean findPath(Node node, Integer value, List<Node> path)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getValue() == value)
        {
            return true;
        }

        path.add(node);
        if (findPath(node.getLeft(), value, path))
        {
            return true;
        }
        else if (findPath(node.getRight(), value, path))
        {
            return true;
        }

        path.remove(node);
        return false;
    }
}

class BinaryTree
{
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

    public Node createTree(Integer[] array)
    {

        this.array = array;
        Node head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor=0; arrayCursor<array.length; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIindex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIindex) && isValidValue(array[leftChildIindex]))
            {
                Node leftNode = new Node(array[leftChildIindex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }

        return head;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index*2) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
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