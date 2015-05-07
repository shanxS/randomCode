// distance between 2 nodes in BST
// r1, q1, set12

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 50, 5, 55};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());
        DistanceFinder df = new DistanceFinder(tree);
        //System.out.println("distance " + df.findDisatnce(20, 55));
        //System.out.println("distance " + df.findDisatnce(20, 5));
        System.out.println("distance " + df.findDisatnce(25, 30));
    }
}

class DistanceFinder
{
    private Node head;
    private Boolean rule;
    private List<Integer> pathNode1, pathNode2;

    public DistanceFinder(BST tree)
    {
        this.head = tree.getHead();
        this.rule = tree.getRule();
        this.pathNode1 = new ArrayList<>();
        this.pathNode2 = new ArrayList<>();
    }

    public Integer findDisatnce(Integer number1, Integer number2)
    {
        setPath(head, pathNode1, number1);
        if (pathNode1.get(pathNode1.size() - 1) != number1)
        {
            System.out.println("cant find number in tree" + number1);
            return null;
        }

        setPath(head, pathNode2, number2);
        if (pathNode2.get(pathNode2.size() - 1) != number2)
        {
            System.out.println("cant find number in tree" + number2);
            return null;
        }

        Integer cursor1 = 0;
        Integer cursor2 = 0;
        while (cursor1 < pathNode1.size() && cursor2 < pathNode2.size())
        {
            Integer value1 = pathNode1.get(cursor1);
            Integer value2 = pathNode2.get(cursor2);

            if (value1 != value2)
            {
                break;
            }

            ++cursor1;
            ++cursor2;
        }
        --cursor1;
        --cursor2;

        return (pathNode2.size() - cursor2) + (pathNode1.size() - cursor1) - 1;
    }

    private void setPath(Node node, List<Integer> path, Integer target)
    {
        if (node == null)
        {
            return;
        }

        path.add(node.getValue());

        if (node.getValue() == target)
        {
            return;
        }

        if (node.getValue() > target == rule)
        {
            setPath(node.getLeft(), path, target);
        }
        else if (node.getValue() > target != rule)
        {
            setPath(node.getRight(), path, target);
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

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value == ruleLeftLessThan)
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
        else if (node.getValue() > value != ruleLeftLessThan)
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

    public Node getHead()
    {
        return head;
    }

    public Boolean getRule()
    {
        return ruleLeftLessThan;
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