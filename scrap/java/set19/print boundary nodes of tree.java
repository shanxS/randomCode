// print boundary nodes of tree
// r5, q3, set19

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{7, 5, 9, 8, 10, 6, 11, 12};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());
        BoundaryPrinter bp = new BoundaryPrinter(tree);
        bp.printBoundary();
    }
}

class BoundaryPrinter
{
    private Node head;
    private List<Node> leftNodes, rightNodes, stack;

    public BoundaryPrinter(BST tree)
    {
        this.head = tree.getHead();
        this.leftNodes = new ArrayList<>();
        this.rightNodes = new ArrayList<>();
        this.stack = new ArrayList<>();
    }

    public void printBoundary()
    {
        stack.add(head);

        while(stack.size() > 0)
        {
            Integer staleCount = stack.size();
            leftNodes.add(stack.get(0));
            if (staleCount > 1)
            {
                rightNodes.add(stack.get(staleCount - 1));
            }

            for (Integer cursor=0; cursor<staleCount; ++cursor)
            {
                Node node = stack.get(cursor);

                if (cursor != 0 && cursor != staleCount-1)
                {
                    leftNodes.add(node);
                }

                if (node.getLeft() != null)
                {
                    stack.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    stack.add(node.getRight());
                }
            }

            stack = stack.subList(staleCount, stack.size());
        }

        for (Node node : leftNodes)
        {
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
        for (Node node : rightNodes)
        {
            System.out.print(node.getValue() + " ");
        }
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.ruleLeftLessThan = true;
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

    private void insert(Node node, Integer value)
    {
        if ((node.getValue() > value) == ruleLeftLessThan)
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
        if ((node.getValue() >value) != ruleLeftLessThan)
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
        this.left = null;
        this.right = null;
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