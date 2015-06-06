// test if right and left binary subtrees are mirror images
// code question: 51

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7,
//                null, null,
//                8, 9, 10, 11};

//        Integer[] array = new Integer[]{1,2,2,4,5,4,5,
//        null, null,
//        8,9,8,9};

        Integer[] array = new Integer[]{1,2,2,4,5,5,4,
        null, null,
        8,9,
        9,8};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        Folder f = new Folder();
        System.out.print(f.test(bt.getHead()));
    }
}

class Folder
{
    public Boolean test(Node head)
    {
        return test(head.getLeft(), head.getRight());
    }

    private Boolean test(Node leftSubTree, Node rightSubTree)
    {
        if ((leftSubTree == null && rightSubTree != null)
              ||  (leftSubTree != null && rightSubTree == null))
        {
            return false;
        }
        else  if (leftSubTree == null && rightSubTree == null)
        {
            return true;
        }

        if (rightSubTree.getValue() != leftSubTree.getValue())
        {
            return false;
        }

        return (test(leftSubTree.getLeft(), rightSubTree.getRight())
                && test(leftSubTree.getRight(), rightSubTree.getLeft()));
    }
}

class BinaryTree
{
    private Node head;
    final private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        head = new Node(array[0]);
        arrayLength = array.length;

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
            if (ifValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (ifValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private Integer getRightChildIndex(Integer idnex)
    {
        return getLeftChildIndex(idnex) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
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

    private boolean ifValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
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
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
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