// connect nodes at same level in BST without recursion
// r3, q1, set14

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{50,30,60,20,35,70,65};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());
        System.out.println("----------------------");

        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        list1.add(tree.getHead());
        while (list1.size() > 0 || list2.size() > 0)
        {
            if (list1.size() > 0)
            {
                link(list1);

                for (Node node : list1)
                {
                    System.out.print(node.getValue() + " ");

                    if (node.getLeft() != null)
                    {
                        list2.add(node.getLeft());
                    }
                    if(node.getRight() != null)
                    {
                        list2.add(node.getRight());
                    }
                }

                list1.clear();
            }
            else
            {
                link(list2);

                for (Node node : list2)
                {
                    System.out.print(node.getValue() + " ");

                    if (node.getLeft() != null)
                    {
                        list1.add(node.getLeft());
                    }
                    if(node.getRight() != null)
                    {
                        list1.add(node.getRight());
                    }
                }

                list2.clear();
            }

            System.out.println();

        }

        System.out.println("----------------------");
        BST.print(tree.getHead());
    }

    private static void link(List<Node> list)
    {
        if (list.size() < 2)
        {
            return;
        }

        Node parentNode = list.get(0);
        for (Integer i = 1; i<list.size(); ++i)
        {
            Node childNode = list.get(i);
            parentNode.setLink(childNode);

            parentNode = childNode;
        }
    }
}

class BST
{
    private Node head;
    private Boolean rule;

    public BST()
    {
        this.head = null;
        this.rule = true;
    }

    public static void print(Node node)
    {
        if(node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLink() != null)
        {
            System.out.print(node.getLink().getValue());
        }
        System.out.print(" - ");
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
        if (node.getValue() > value == rule)
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
        else if (node.getValue() > value != rule)
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
    private Node left, right, link;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.link = null;
    }

    public Node getLink()
    {
        return link;
    }

    public void setLink(Node link)
    {
        this.link = link;
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