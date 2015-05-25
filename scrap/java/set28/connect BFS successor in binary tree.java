// connect BFS successor in binary tree
// r3, q2, set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5,
                null,
                8,
                null, null, null,
                6,
                null, null,
                9,
                null, null, null, null, null, null, null,
                7,
                null, null, null, null, null, null,
                10
        };

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.println("------------");

        BFSConnector bfsConnector = new BFSConnector();
        bfsConnector.connect(bt.getHead());
        BinaryTree.print(bt.getHead());
    }
}

class BFSConnector
{
    public void connect(Node head)
    {
        connect (head, null);
    }

    private void connect(Node node, Node bfs)
    {
        if (node == null)
        {
            return;
        }

        if (bfs != null)
        {
            node.setBfs(bfs);
        }

        connect(node.getLeft(), getRightMostChildFromBFS(bfs));
        connect(node.getRight(),
                (node.getLeft() != null) ? (node.getLeft()) : getRightMostChildFromBFS(bfs));
    }

    private Node getRightMostChildFromBFS(Node node)
    {
        Node cursor = node;
        while (cursor != null)
        {
            if (cursor.getRight() != null)
            {
                return cursor.getRight();
            }
            else if (cursor.getLeft() != null)
            {
                return cursor.getLeft();
            }

            cursor = cursor.getBfs();
        }

        return null;
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
        if(node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.print(" - ");
        if(node.getBfs() != null)
        {
            System.out.print(node.getBfs().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public BinaryTree(Integer[] array)
    {
        this.array = array;
        this.head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);
        for (Integer arrayCursor = 0; arrayCursor<array.length; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChild(arrayCursor);
            if(isValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if(isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private Integer getLeftChild(Integer index)
    {
        return (index*2) + 1;
    }

    private Integer getRightChildIndex(Integer index)
    {
        return (index*2) + 2;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private boolean isValidValue(Integer value)
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
    private Node left, right, bfs;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.bfs = null;
    }

    public Node getBfs()
    {
        return bfs;
    }

    public void setBfs(Node bfs)
    {
        this.bfs = bfs;
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