// test if trees are isomorphic
// r1, q5, set14

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array1 = new Integer[]{30, 20, 40, 10, 15, 35, 50, 45, 60};
        BST tree1 = new BST(true);
        for (Integer i : array1)
        {
            tree1.insert(i);
        }
        BST.print(tree1.getHead());

        Integer[] array2 = new Integer[]{50, 45, 60, 30, 10, 20, 40, 15, 35};
        BST tree2 = new BST(false);
        for (Integer i : array2)
        {
            tree2.insert(i);
        }
        System.out.println("-----------------------");
        BST.print(tree2.getHead());

        IsomorphicChecker ic = new IsomorphicChecker(tree1, tree2);
        System.out.print(ic.test());
    }
}

class IsomorphicChecker
{
    private Node head1, head2;

    public IsomorphicChecker(BST tree1, BST tree2)
    {
        this.head1 = tree1.getHead();
        this.head2 = tree2.getHead();
    }

    public Boolean test()
    {
        List<Integer> list1 = new ArrayList<Integer>();
        traverseInorder(head1, list1);

        List<Integer> list2 = new ArrayList<Integer>();
        traverseInorder(head2, list2);

        if (list1.size() != list2.size())
        {
            return false;
        }

        Collections.sort(list1);
        Collections.sort(list2);
        for (Integer i=0; i<list1.size(); ++i)
        {
            if (list1.get(i) != list2.get(i))
            {
                return false;
            }
        }

        return true;
    }

    private void traverseInorder(Node node, List<Integer> list)
    {
        if (node == null)
        {
            return;
        }

        traverseInorder(node.getLeft(), list);
        list.add(node.getValue());
        traverseInorder(node.getRight(), list);
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLessThan;

    public BST(Boolean rule)
    {
        this.head = null;
        this.ruleLeftLessThan = rule;
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