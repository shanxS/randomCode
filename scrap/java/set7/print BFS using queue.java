// print BFS using queue

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);
        tree.insert(5);
        tree.insert(15);
        tree.insert(45);
        tree.insert(60);

        tree.print();

        BFSUsingQueue bfsUsingRecursion = new BFSUsingQueue(tree);
        bfsUsingRecursion.printBFS();
    }
}

class BFSUsingQueue
{
    private final Tree tree ;
    private Queue<Node> que;

    public BFSUsingQueue(Tree tree)
    {
        this.tree = tree;
        que = new LinkedList<>();
    }

    public void printBFS()
    {
        Node node = tree.getHead();
        que.add(node);
        Integer depth = que.size();

        while (que.size() > 0) {
            depth = que.size();
            while (depth > 0) {
                System.out.print(que.peek().getValue() + " ");
                addChildrenToQue(que.peek());
                que.remove();
                --depth;
            }
            System.out.println();
        }

    }

    private void addChildrenToQue(Node node) {
        if (node.getLeft() != null)
        {
            que.add(node.getLeft());
        }

        if (node.getRight() != null)
        {
            que.add(node.getRight());
        }
    }

    private Integer queueNodes() {
        return null;
    }

}

class Tree
{
    private Node head;

    public Tree()
    {
        head = null;
    }

    public void print()
    {
        print(head);
    }

    private void print(Node node) {
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

    private void insert(Node node, Integer value) {
        if (node.getValue() > value)
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
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
    }

    public Node getHead() {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}