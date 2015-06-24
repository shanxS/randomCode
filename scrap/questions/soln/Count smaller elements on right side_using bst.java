// Count smaller elements on right side
// code question: 112

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {12, 1, 2, 3, 0, 11, 4};
        BST bst = new BST();
        for (Integer i = array.length - 1; i >= 0; --i)
        {
            bst.insert(array[i]);
        }

        bst.getResults().stream().forEach(x -> System.out.print(x + " "));
    }
}

class BST
{
    private Node head;
    private List<Integer> results;
    private Integer thisNodeValue;

    public List<Integer> getResults()
    {
        return results;
    }

    public BST()
    {
        head = null;
        results = new ArrayList<>();
    }

    public void insert(Integer value)
    {
        thisNodeValue = 0;

        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }

        results.add(thisNodeValue);
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
            thisNodeValue += node.getLeftLevel();
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
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
    }

    public Integer getLevel()
    {
        Integer leftLevel = 0;
        if (left != null)
        {
            leftLevel = left.getLevel();
        }

        Integer rightLevel = 0;
        if (right != null)
        {
            rightLevel = right.getLevel();
        }

        return 1 + Math.max(leftLevel, rightLevel);
    }

    public Integer getLeftLevel()
    {
        Integer leftChildLevel = 0;
        if (left != null)
        {
            leftChildLevel = left.getLevel();
        }

        return 1 + leftChildLevel;
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