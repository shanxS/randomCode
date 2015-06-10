// Find a pair with given sum in a Balanced BST

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {20, 11, 30, 5, 16, 25, 40, 12, 27, 13};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());
        System.out.print("---------------");

        PairFinder pf = new PairFinder();
        System.out.print(pf.searchPair(bst.getHead(), 40));
    }
}

class PairFinder
{
    private Stack<Node> less, more;

    public Boolean searchPair(Node head, Integer target)
    {
        less = new Stack<>();
        populateLess(head);

        more = new Stack<>();
        populateMore(head);

        return find(target);
    }

    private Boolean find(Integer target)
    {
        while(less.size() > 0 && more.size() > 0)
        {
            Integer thisSum = less.peek().getValue() + more.peek().getValue();
            if (thisSum == target)
            {
                return true;
            }
            else if (thisSum < target)
            {
                populateLess(less.pop().getRight());
            }
            else if (thisSum > target)
            {
                populateMore(more.pop().getLeft());
            }
        }

        return false;
    }

    private void populateMore(Node node)
    {
        if (node == null)
        {
            return;
        }

        more.push(node);
        populateMore(node.getRight());
    }

    private void populateLess(Node node)
    {
        if (node == null)
        {
            return;
        }

        less.push(node);
        populateLess(node.getLeft());
    }

}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
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
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}