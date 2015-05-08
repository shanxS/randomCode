// in binary tree if random pointer does NOT point to successor - set it to NULL
// r2, q1, set13

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 50, 45};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        tree.conenctMad(10,30);
        tree.conenctMad(40,35);
        tree.conenctMad(35,50);
        tree.conenctMad(25, 45);

        BST.print(tree.gethead());
        SuccesorFinder sf = new SuccesorFinder(tree);
        sf.find();
        System.out.println("--------------------");
        BST.print(tree.gethead());
    }
}

class SuccesorFinder
{
    private Node head;

    public SuccesorFinder(BST tree)
    {
        this.head = tree.gethead();
    }

    public void find()
    {
        find(head, new ArrayList<Node>());
    }

    private void find(Node node, List<Node> list)
    {
        if (node == null)
        {
            return;
        }


        List<Node> llist = new ArrayList<>();
        find(node.getLeft(), llist);
        llist.add(node.getLeft());

        List<Node> rlist = new ArrayList<>();
        find(node.getRight(), rlist);
        rlist.add(node.getRight());

        list.addAll(rlist);
        list.addAll(llist);

        test(node, list);
    }

    private void test(Node node, List<Node> list)
    {
        if (node.getMad() != null)
        {
            Boolean contains = false;
            for (Node item : list)
            {
                if (item != null && item.getValue() == node.getMad().getValue())
                {
                    contains = true;
                }
            }

            if (!contains)
            {
                node.setMad(null);
            }
        }

//        System.out.print(node.getValue() + " - ");
//        for (Node item : list)
//        {
//            if (item != null)
//            {
//                System.out.print(item.getValue() + " ");
//            }
//        }
//
//        System.out.println();
    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftGreaterThan;

    public BST()
    {
        this.head = null;
        this.ruleLeftGreaterThan = true;
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
        System.out.print(" MAD: ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
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
        if (node.getValue() > value == ruleLeftGreaterThan)
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
        else if (node.getValue() > value != ruleLeftGreaterThan)
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

    public void conenctMad(Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        if (node1 == null)
        {
            System.out.println("cant find to conect mad " + value1);
        }

        Node node2 = findNode(head, value2);
        if (node2 == null)
        {
            System.out.println("cant find to conect mad " + value2);
        }

        node1.setMad(node2);
    }

    private Node findNode(Node node, Integer value)
    {
        if (node == null || node.getValue() == value)
        {
            return node;
        }

        if (node.getValue() > value == ruleLeftGreaterThan)
        {
            return findNode(node.getLeft(), value);
        }
        else if (node.getValue() > value != ruleLeftGreaterThan)
        {
            return findNode(node.getRight(), value);
        }

        return null;
    }

    public Node gethead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right, mad;

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }

    public Node(Integer value)
    {
        this.value = value;
        this.right = null;
        this.left = null;
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

