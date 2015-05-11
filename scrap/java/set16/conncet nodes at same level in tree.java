// conncet nodes at same level in tree
// r3, q1, set16

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6,7};
        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        ConncetNode cn = new ConncetNode(bt);
        cn.connect();
        BinaryTree.print(bt.getHead());
    }
}

class ConncetNode
{
    private Node head;
    public ConncetNode(BinaryTree tree)
    {
        this.head = tree.getHead();
    }

    public void connect()
    {
        List<Node>list = new ArrayList<>();
        list.add(head);
        while(list.size() > 0)
        {
            Integer popCount = list.size();
            while(popCount > 0)
            {
                Node node = list.get(0);
                list.remove(0);
                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }

                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }

                --popCount;
            }

            if (list.size() > 0)
            {
                Node node = list.get(0);
                for (Integer i = 1; i < list.size(); ++i)
                {
                    node.setLink(list.get(i));
                    node = node.getLink();
                }
            }
        }
    }
}


class BinaryTree
{
    private Integer[] array;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.head = null;
        this.array = array;

        this.head = generateTree(0, this.array.length-1);
    }

    private Node generateTree(Integer start, Integer length)
    {
        if (length == start)
        {
            return new Node(array[start]);
        }

        Integer mid  = (start + length)/2;
        Node node = new Node(array[mid]);

        node.setLeft(generateTree(start, mid-1));
        node.setRight(generateTree(mid+1, length));

        return node;
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
        System.out.print(" LINK ");
        if (node.getLink() != null)
        {
            System.out.print(node.getLink().getValue());
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
    private Integer value;
    private Node left, right, link;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.link = null;
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

    public Node getLink()
    {
        return link;
    }

    public void setLink(Node link)
    {
        this.link = link;
    }
}