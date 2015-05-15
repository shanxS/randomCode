// given BT nodes with forward and backward pointer - convert to doubly linked list
// written, q1, set20

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20,10,30,25,40};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BSTToDL b2d= new BSTToDL(tree);
        b2d.convert();
    }
}

class BSTToDL
{
    private Node head;

    public BSTToDL(BST tree)
    {
        this.head = tree.getHead();
    }

    public void convert()
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer cursor = 0;
        Integer size = list.size();
        while(cursor < size)
        {
            for (Integer i = cursor; i < size; ++i)
            {
                Node node = list.get(i);
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }
            }

            cursor = size;
            size = list.size();
        }


        for (Integer i = 0; i < list.size()-1; ++i)
        {
            Node first = list.get(i);
            Node second = list.get(i + 1);

            first.setForward(second);
            second.setBackward(first);
        }

        Node node = head;
        while(node != null)
        {
            System.out.print(node.getValue() + " ");
            node = node.getForward();
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
        if ((node.getValue() > value) == ruleLeftLessThan)
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
        else
        {
            if ((node.getValue() > value) != ruleLeftLessThan)
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
    private Node forward, backward;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.forward = null;
        this.backward = null;
    }

    public Node getForward()
    {
        return forward;
    }

    public void setForward(Node forward)
    {
        this.forward = forward;
    }

    public Node getBackward()
    {
        return backward;
    }

    public void setBackward(Node backward)
    {
        this.backward = backward;
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