// print BFS zig zag using recursion
// r2, q1, set7, amazon

import java.util.ArrayList;
import java.util.List;

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

        BFSAlternatingUsingRecursion bfsUsingRecursion = new BFSAlternatingUsingRecursion(tree);
        bfsUsingRecursion.printBFS();
    }
}

class BFSAlternatingUsingRecursion
{
    private Tree tree;

    public BFSAlternatingUsingRecursion(Tree tree)
    {
        this.tree = tree;
    }

    public void printBFS() {
        Boolean direction = false;
        Integer level = 0;
        List<Node> nodeList = new ArrayList<>();
        getNodesInDirection(tree.getHead(), nodeList, direction, level);

        while(nodeList.size() > 0)
        {
            for(Node node : nodeList)
            {
                System.out.print(node.getValue() + " ");
            }
            System.out.println();

            nodeList.clear();
            direction = !direction;
            ++level;
            getNodesInDirection(tree.getHead(), nodeList, direction, level);
        }
    }

    private void getNodesInDirection(Node node, List<Node> nodeList, Boolean direction, Integer level) {
        if (node == null)
        {
            return;
        }

        if (level == 0)
        {
            nodeList.add(node);
        }

        if (direction)
        {
            getNodesInDirection(node.getRight(), nodeList, direction, level-1);
            getNodesInDirection(node.getLeft(), nodeList, direction, level-1);
        }
        else
        {
            getNodesInDirection(node.getLeft(), nodeList, direction, level-1);
            getNodesInDirection(node.getRight(), nodeList, direction, level-1);
        }
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