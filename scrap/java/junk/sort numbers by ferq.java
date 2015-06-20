// sort numbers by ferq

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST freqTree = new BST();
        Stack<Node> stack = new Stack<>();
        stack.push(tree.getHead());
        while (stack.size() > 0)
        {
            Node top = stack.pop();
            freqTree.insert(top);

            if (top.getRight() != null)
            {
                stack.push(top.getRight());
            }

            if (top.getLeft() != null)
            {
                stack.push(top.getLeft());
            }
        }


        freqTree.inorderPrint();
        System.out.print("--------------");
        freqTree.levelOrderPrint();

    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
        if (node.getValue() < value)
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
        else  if (node.getValue() > value)
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
        else
        {
            node.increaseFreq();
        }
    }

    public void insert(Node node)
    {
        if (head == null)
        {
            head = new Node (node);
        }
        else
        {
            insert(head, node);
        }
    }

    private void insert(Node parent, Node node)
    {
        if (parent.getFreq() < node.getFreq())
        {
            if (parent.getLeft() != null)
            {
                insert(parent.getLeft(), node);
            }
            else
            {
                parent.setLeft(new Node (node));
            }
        }
        else if (parent.getFreq() > node.getFreq())
        {
            if (parent.getRight() != null)
            {
                insert(parent.getRight(), node);
            }
            else
            {
                parent.setRight(new Node (node));
            }
        }
    }

    public void print()
    {
        BST.print(head);
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
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

    public void inorderPrint()
    {
        inorderPrint(head);
    }

    private void inorderPrint(Node node)
    {
        if (node == null)
        {
            return;
        }

        inorderPrint(node.getLeft());
        System.out.println(node.getValue() + " " + node.getFreq());
        inorderPrint(node.getRight());
    }

    public void levelOrderPrint()
    {
        Queue<Node> que = new ArrayDeque<>();
        que.add(head);

        while (que.size() > 0)
        {
            Node top  = que.remove();
            System.out.println(top.getValue() + " " + top.getFreq());

            if (top.getLeft() != null)
            {
                que.add(top.getLeft());
            }

            if (top.getRight() != null)
            {
                que.add(top.getRight());
            }
        }
    }
}

class Node
{
    private Node left, right;
    Integer value, freq;

    public Node(Integer value)
    {
        this.value = value;
        this.freq = 1;
    }

    public Node(Node otherNode)
    {
        this.value = otherNode.getValue();
        this.freq = otherNode.getFreq();
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

    public Integer getFreq()
    {
        return freq;
    }

    public void increaseFreq()
    {
        ++this.freq;
    }
}