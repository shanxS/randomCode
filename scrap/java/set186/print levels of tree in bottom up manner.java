// print levels of tree in bottom up manner
// set186, r4, q1
// code question 

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 21, 26, 27};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        ReverseLevelPrinter rp = new ReverseLevelPrinter();
        rp.print(tree.getHead());
    }
}

class ReverseLevelPrinter
{
    public void print(Node head)
    {
        List<Node> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        list.add(head);
        Integer cursor = 0;
        Integer size = list.size();

        while(cursor < size)
        {
            for (Integer i=cursor; i<size; ++i)
            {
                Node node = list.get(i);

                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }

                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }
            }

            cursor = size;
            stack.push(size);
            size = list.size();
        }

        Integer printCursor = list.size()-1;
        while(stack.size() > 0)
        {
            Integer stackTop = stack.pop();
            while(printCursor >= stackTop)
            {
                System.out.print(list.get(printCursor).getValue() + " ");
                --printCursor;
            }
            System.out.println();
        }

        while (printCursor >= 0)
        {
            System.out.print(list.get(printCursor).getValue() + " ");
            --printCursor;
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
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