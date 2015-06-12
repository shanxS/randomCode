// post order traversal by iteration

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 7, 5,
                null,
                6,
                null,
                9,
                null, null,
                1, 11,
                null, null,
                4};

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        IterativePreOrder ilo = new IterativePreOrder();
        ilo.print(bt.getHead());
    }
}

class IterativePreOrder
{
    private Stack<Node> stack1, stack2;
    public void print(Node head)
    {
        stack1 = new Stack<>();
        stack2 = new Stack<>();

        stack1.push(head);

        while(stack1.size() > 0)
        {
            Node topNode = stack1.pop();
            stack2.push(topNode);

            if (topNode.getRight() != null)
            {
                stack1.push(topNode.getRight());
            }
            if(topNode.getLeft() != null)
            {
                stack1.push(topNode.getLeft());
            }
        }

        System.out.println("read in reverse");
        for (Node node : stack2)
        {
            System.out.print(node.getValue() + " ");
        }
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
