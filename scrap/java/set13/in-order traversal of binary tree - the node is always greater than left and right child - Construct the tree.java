// in-order traversal of binary tree - the node is always greater than left and right child - Construct the tree

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] inorder = new Integer[]{2,10,20,15,11,50,25,40,30};//{5,10,40,30,20};
        List<Integer> list = new ArrayList<>();
        for(Integer i : inorder)
        {
            list.add(i);
        }

        TreeMaker tm = new TreeMaker(list);

        print(tm.make());
    }

    private static void print(Node node)
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

class TreeMaker
{
    private List<Integer> list;

    public TreeMaker(List<Integer> list)
    {
        this.list = list;
    }

    public Node make()
    {
        Integer maxValue = Collections.max(list);
        Node head = new Node(maxValue);
        Integer maxIndex = list.indexOf(maxValue);

        make (head, list.subList(0, maxIndex), list.subList(maxIndex+1, list.size()));

        return head;
    }

    private void make(Node node, List<Integer> leftList, List<Integer> rightList)
    {
        Node leftNode = null;
        if (leftList.size() > 0)
        {
            Integer leftMaxValue = Collections.max(leftList);
            leftNode = new Node(leftMaxValue);
            node.setLeft(leftNode);
            Integer leftMaxIndex = leftList.indexOf(leftMaxValue);

            make(leftNode, leftList.subList(0, leftMaxIndex), leftList.subList(leftMaxIndex + 1, leftList.size()));
        }

        Node rightNode = null;
        if (rightList.size() > 0)
        {
            Integer rightMaxValue = Collections.max(rightList);
            rightNode = new Node(rightMaxValue);
            node.setRight(rightNode);
            Integer rightMaxIndex = rightList.indexOf(rightMaxValue);

            make(rightNode, rightList.subList(0, rightMaxIndex), rightList.subList(rightMaxIndex + 1, rightList.size()));
        }
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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