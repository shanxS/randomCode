// Remove nodes on root to leaf paths of length < K
// code question: 86

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1,2,3,4,5,8,7,6};
        Integer[] array = {1,2,11,4,5,3,
        null,null,
        6,
                null,null,
        8,7};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        PathPruner pp = new PathPruner();
        pp.prune(bt.getHead(), 4);

        System.out.println("----------------------");
        BinaryTree.print(bt.getHead());
    }
}

class PathPruner
{
    private Integer k;

    public void prune(Node head, Integer k)
    {
        this.k = k;
        pruneFor(head, 1);
    }

    private Boolean pruneFor(Node node, Integer lenghtTillParent)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getLeft() == null & node.getRight() == null)
        {
            if (lenghtTillParent + 1 > k)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        Boolean leftResult = pruneFor(node.getLeft(), lenghtTillParent+1);
        Boolean rightResult = pruneFor(node.getRight(), lenghtTillParent+1);


        if (leftResult != null && !leftResult)
        {
            node.setLeft(null);
        }

        if (rightResult != null && !rightResult)
        {
            node.setRight(null);
        }

        if (rightResult != null && leftResult != null && !rightResult && !leftResult)
        {
            return false;
        }

        return true;
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        this.head = new Node(array[0]);
        this.arrayLength = array.length;

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor < arrayLength; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
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

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }


    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
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
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}
