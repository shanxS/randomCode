// bst from preorder

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10, 5, 1, 7, 40, 50};
        List<Integer> list = new ArrayList<>();
        for (Integer i : ar)
        {
            list.add(i);
        }

        BSTUtil util = new BSTUtil();
        util.print(util.makeFromPreorder(list));
    }
}

class BSTUtil
{
    public Node makeFromPreorder(List<Integer> ar)
    {
        if (ar.size() == 0)
        {
            return null;
        }

        Node thisNode = new Node(ar.get(0));

        Integer leftCursor = 1;
        while (leftCursor < ar.size() && ar.get(leftCursor) < ar.get(0))
        {
            ++leftCursor;
        }
        Node leftNode = makeFromPreorder(ar.subList(1, leftCursor));
        thisNode.setLeft(leftNode);

        Node rightNode = makeFromPreorder(ar.subList(leftCursor, ar.size()));
        thisNode.setRight(rightNode);

        return thisNode;
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
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
}
