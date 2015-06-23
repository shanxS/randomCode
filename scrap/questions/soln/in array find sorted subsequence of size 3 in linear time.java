// in array find sorted subsequence of size 3 in linear time
// code question: 116

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {4, 3, 2, 1};//{1, 2, 3, 4};//{12, 11, 10, 5, 6, 2, 30};
        BST tree = new BST();
        for (Integer i=array.length-1; i>=0; --i)
        {
            tree.insert(array[i]);
            if (tree.getNodes().size() == 3)
            {
                break;
            }
        }

//        BST.print(tree.getHead());
        tree.getNodes().stream().forEach(x -> System.out.print(" " + x.getValue()));

    }
}

class BST
{
    private Node head;
    private final Integer MAX;
    private List<Node> nodes;

    public BST()
    {
        head = null;
        MAX = 3;
    }


    public void insert(Integer value)
    {
        nodes = new ArrayList<>();
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
            nodes.add(node);
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                Node newNode = new Node (value);
                node.setLeft(newNode);
                nodes.add(newNode);
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
        if(node ==  null)
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

    public List<Node> getNodes()
    {
        return nodes;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node (Integer valeu)
    {
        this.value = valeu;
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