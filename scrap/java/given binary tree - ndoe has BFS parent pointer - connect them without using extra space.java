// given binary tree - ndoe has BFS parent pointer - connect them without using extra space
// maybe from set28

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,
            null, null, null,
            9,
            null, null,
            10};

        BST tree = new BST(array);
        BST.print(tree.getHead());
        System.out.println("----------------------");

        BSFConnector connector = new BSFConnector();
        connector.connect(tree);
        BST.print(tree.getHead());

    }
}

class BSFConnector
{
    public void connect(BST tree)
    {
        connect(tree.getHead(), null);
    }

    private void connect(Node node, Node bfs)
    {
        if (node == null)
        {
            return;
        }

        node.setBfs(bfs);

        if (node.getLeft() != null)
        {
            connect(node.getLeft(), getRightMostChildFromBFS(node));
            connect(node.getRight(), node.getLeft());
        }
        else
        {
            connect(node.getRight(), getRightMostChildFromBFS(node));
        }
    }

    private Node getRightMostChildFromBFS(Node node)
    {
        Node BFSParentCurror = node.getBfs();
        while(BFSParentCurror != null)
        {
            if (BFSParentCurror.getRight() != null)
            {
                return BFSParentCurror.getRight();
            }
            else if (BFSParentCurror.getLeft() != null)
            {
                return BFSParentCurror.getLeft();
            }

            BFSParentCurror = BFSParentCurror.getBfs();
        }

        return null;
    }
}

class BST
{
    private Node head;
    private Integer[] array;

    public Node getHead()
    {
        return head;
    }

    public BST(Integer[] array)
    {
        this.head = new Node(array[0]);
        this.array = array;
        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer dataCursor=0; dataCursor<array.length; ++dataCursor)
        {
            if (!isValidData(array[dataCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(dataCursor);
            if (isValidIndex(leftChildIndex) && isValidData(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(dataCursor);
            if (isValidIndex(rightChildIndex) && isValidData(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(0);
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
        System.out.print(" ---- ");
        if (node.getBfs() != null)
        {
            System.out.print(node.getBfs().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    private boolean isValidData(Integer data)
    {
        return (data != null);
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < array.length);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return (2*index) + 2;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }
}

class Node
{
    private Node left, right, bfs;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.bfs = null;
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
}