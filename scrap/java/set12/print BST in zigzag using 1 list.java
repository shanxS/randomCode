// print BST in zigzag using 1 list
// r2, q1, set12

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
        tree.printZigZag();
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

    public void printZigZag()
    {
        List<Node> que = new ArrayList<>();

        que.add(head);
        Integer cursor = 0;
        Integer depth = que.size();
        Boolean direction = true;

        while(cursor != depth)
        {
            for (Integer i=cursor; i<depth; ++i)
            {
                System.out.print(que.get(i).getValue() + " ");
            }
            System.out.println();

            for(Integer i=depth-1; i >= cursor; --i)
            {
                Node node = que.get(i);
                if (direction)
                {
                    if (node.getRight() != null)
                    {
                        que.add(node.getRight());
                    }
                    if (node.getLeft() != null)
                    {
                        que.add(node.getLeft());
                    }
                }
                else
                {
                    if (node.getLeft() != null)
                    {
                        que.add(node.getLeft());
                    }
                    if (node.getRight() != null)
                    {
                        que.add(node.getRight());
                    }
                }
            }

            direction = !direction;
            cursor = depth;
            depth = que.size();
        }
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