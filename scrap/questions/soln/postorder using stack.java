// postorder using stack

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8,
                null, null,
                9,
                null, null,
                10};
        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        PostorderTravelsal po = new PostorderTravelsal();
        po.print(bt.getHead());
    }
}

class PostorderTravelsal
{
    private Stack<Node> stack;

    public void print(Node head)
    {
        stack = new Stack<>();
        stack.push(head);

        while(stack.size() > 0)
        {
            if (stack.peek().getLeft() != null)
            {
                stack.push(stack.peek().getLeft());
            }
            else if (stack.peek().getRight() != null)
            {
                stack.push(stack.peek().getRight());
            }
            else
            {
                popTillNew();
            }
        }
    }

    private void popTillNew()
    {
        Integer poppedValue = popAndPrint();

        while(stack.size() > 0)
        {
            if (stack.peek().getLeft() != null && stack.peek().getLeft().getValue() == poppedValue)
            {
                if (stack.peek().getRight() != null)
                {
                    stack.push(stack.peek().getRight());
                    break;
                }
                else
                {
                    poppedValue = popAndPrint();
                }
            }
            else if (stack.peek().getRight().getValue() == poppedValue)
            {
                poppedValue = popAndPrint();
            }
        }
    }

    private Integer popAndPrint()
    {
        Integer popped = stack.pop().getValue();
        System.out.print(popped + " ");

        return popped;
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

        for (Integer arrayCursor=0; arrayCursor<arrayLength; ++arrayCursor)
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