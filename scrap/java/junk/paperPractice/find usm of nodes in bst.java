// find usm of nodes in bst

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {32,24,41,21,28,36,47,14,25,31,39};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }
        bst.pint(bst.getHead());

        Finder f = new Finder();
//        System.out.print(f.find(bst.getHead(), 29));
        f.find(bst.getHead(), 66);

    }
}

class Finder
{

    private Stack<Node> less, more;
    private Node head;

    public void find(Node head, Integer target)
    {
        this.head = head;

        less = new Stack<>();
        initLess();

        more = new Stack<>();
        initMore();

        while (less.peek().getValue() < more.peek().getValue())
        {
            Integer thisSum = less.peek().getValue() + more.peek().getValue();
            if (thisSum == target)
            {
                System.out.print(less.peek().getValue() + " " + more.peek().getValue());
                break;
            }
            else  if (thisSum > target)
            {
                doLess();
            }
            else
            {
                doMore();
            }
        }
    }

    private void doMore()
    {
        Node node = less.pop();
        if (node.getRight() != null)
        {
            Node cursor = node.getRight();
            while (cursor != null)
            {
                less.push(cursor);
                cursor = cursor.getLeft();
            }
        }
    }

    private void doLess()
    {
        Node node = more.pop();
        if (node.getLeft() != null)
        {
            Node cursor = node.getLeft();
            while (cursor != null)
            {
                more.push(cursor);
                cursor = cursor.getRight();
            }
        }
    }

    private void initMore()
    {
        Node cursor = head;
        while (cursor != null)
        {
            more.push(cursor);
            cursor = cursor.getRight();
        }
    }

    private void initLess()
    {
        Node cursor = head;
        while (cursor != null)
        {
            less.push(cursor);
            cursor= cursor.getLeft();
        }
    }


}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
    }

    public Node getHead()
    {
        return head;
    }

    public void pint(Node node)
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

        pint(node.getLeft());
        pint(node.getRight());
    }

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
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