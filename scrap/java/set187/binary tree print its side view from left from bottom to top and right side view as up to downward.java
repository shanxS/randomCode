// binary tree print its side view from left from bottom to top and right side view as up to downward
// r2, q1, set 187

import java.util.Map;
import java.util.TreeMap;

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

        SideViewer sv = new SideViewer();
        BST.print(tree.getHead());
        System.out.println();
        sv.printSide(tree.getHead());
    }
}

class SideViewer
{
    private Map<Integer, Integer> leftLevelValue;
    private Map<Integer, Integer> rightLevelValue;

    public void printSide(Node head)
    {
        leftLevelValue = new TreeMap<>();
        rightLevelValue = new TreeMap<>();

        setLeftLevels(head.getLeft(), 0);
        setRightLevels(head.getRight(), 0);

        for (Map.Entry<Integer, Integer> entry : leftLevelValue.entrySet())
        {
            System.out.print(entry.getValue() + " ");
        }
        System.out.print(head.getValue() + " ");
        for (Map.Entry<Integer, Integer> entry : rightLevelValue.entrySet())
        {
            System.out.print(entry.getValue() + " ");
        }
    }

    private void setRightLevels(Node node, Integer level)
    {
        if (node == null)
        {
            return;
        }

        if (rightLevelValue.get(level) == null)
        {
            rightLevelValue.put(level, node.getValue());
        }

        setRightLevels(node.getLeft(), level + 1);
        setRightLevels(node.getRight(), level+1);
    }

    private void setLeftLevels(Node node, Integer level)
    {
        if (node == null)
        {
            return;
        }

        if (leftLevelValue.get(level) == null)
        {
            leftLevelValue.put(level, node.getValue());
        }

        setLeftLevels(node.getLeft(), level+1);
        setLeftLevels(node.getRight(), level+1);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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