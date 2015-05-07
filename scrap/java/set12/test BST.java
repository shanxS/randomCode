// test BST
// r3, q1

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

        BSTDetector bd = new BSTDetector(tree);
        System.out.println("before swap is BST " + bd.isBST());
        tree.swapNode(35, 50);
        //tree.swapNode(25, 50);
        System.out.println("after swap is BST " + bd.isBST());
    }
}

class BSTDetector
{
    private Node head;
    private List<Integer> values;
    private Boolean rule;
    private Boolean virdict;

    public BSTDetector(BST tree)
    {
        this.head = tree.getHead();
        this.values = new ArrayList<>();
        this.rule = tree.getRule();
        this.virdict = true;
    }

    public Boolean isBST()
    {
        isBST(head);

        return virdict;
    }

    private Boolean test(Integer value)
    {
        if (!virdict)
        {
            return virdict;
        }

        if (values.size() == 0)
        {
            values.add(value);
            virdict = true;
            return virdict;
        }

        if (values.size() == 1)
        {
            values.add(value);
            virdict = (values.get(1) > values.get(0) == rule);
            return virdict;
        }

        if (values.size() == 2)
        {
            values.remove(0);
            values.add(value);
            virdict = (values.get(1) > values.get(0) == rule);
            return virdict;
        }

        return null;
    }

    private void isBST(Node node)
    {
        if (!virdict || node == null)
        {
            return;
        }

        isBST(node.getLeft());
        //System.out.println(node.getValue() + " " + test(node.getValue()));
        test(node.getValue());
        isBST(node.getRight());
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

    public void swapNode(Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        if (node1 == null)
        {
            System.out.print("cant find ndoe " + value1);
        }

        Node node2 = findNode(head, value2);
        if (node2 == null)
        {
            System.out.print("cant find ndoe " + value2);
        }

        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);

    }

    private Node findNode(Node node, Integer value)
    {
        if (node == null || node.getValue() == value)
        {
            return node;
        }

        if (node.getValue() > value == ruleLeftLessThan)
        {
            return findNode(node.getLeft(), value);
        }
        else if (node.getValue() > value != ruleLeftLessThan)
        {
            return findNode(node.getRight(), value);
        }

        return null;
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
        this.right =  null;
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