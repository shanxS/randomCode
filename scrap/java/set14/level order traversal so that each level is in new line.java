// level order traversal so that each level is in new line
// r2, q1, set14

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] dum)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,35,50,45,60};
        BST tree = new BST();
        for(Integer i : array)
        {
            tree.insert(i);
        }
        BST.print(tree.getHead());

        LevelPrinter lp = new LevelPrinter(tree);
        lp.printLevelQueue();

    }
}

class LevelPrinter
{
    private Node head;

    public LevelPrinter(BST tree)
    {
        this.head = tree.getHead();
    }

    public void printLevelQueue()
    {
        List<Node> que = new ArrayList<>();
        que.add(head);
        Integer cursor = 0;
        Integer depth = que.size();

        while(cursor < depth)
        {
            for (Integer i=cursor; i<depth; ++i)
            {
                Node node = que.get(i);

                System.out.print(node.getValue() + " ");
                if (node.getLeft() != null)
                {
                    que.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    que.add(node.getRight());
                }
            }

            System.out.println();
            cursor = depth;
            depth = que.size();
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
        this.ruleLeftLessThan = false;
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