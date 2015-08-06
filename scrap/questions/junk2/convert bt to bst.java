// convert bt to bst

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,2,7,8,4};
        BTUtil btUtil = new BTUtil();
        Node tree = btUtil.makeFor(ar);

        btUtil.print(tree);
        (new BSTConverter()).convert(tree);
        System.out.println("--------------------");
        btUtil.print(tree);
    }
}

class BSTConverter
{
    private List<Integer> list;
    private final boolean copyTo=true, copyFrom=false;

    public void convert(Node head)
    {
        list = new ArrayList<>();
        doInorder(head, copyTo);
        Collections.sort(list);
        doInorder(head, copyFrom);
    }

    private void doInorder(Node node, boolean copyTo)
    {
        if (node == null)
        {
            return;
        }

        doInorder(node.getLeft(), copyTo);
        if (copyTo)
        {
            list.add(node.getValue());
        }
        else
        {
            node.setValue(list.get(0));
            list.remove(0);
        }

        doInorder(node.getRight(), copyTo);
    }
}

class BTUtil
{

    public Node makeFor(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

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
        }

        return nodes[0];
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
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }
}


class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer v)
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

    public void setValue(Integer value)
    {
        this.value = value;
    }
}