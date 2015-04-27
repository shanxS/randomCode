// printing BFS of tree using recursion

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

        BFSUsingRecursion bfsUsingRecursion = new BFSUsingRecursion(tree);
        bfsUsingRecursion.printBFS();
    }
}

class BFSUsingRecursion
{
    private Tree tree;

    public BFSUsingRecursion(Tree tree)
    {
        this.tree = tree;
    }

    public void printBFS() {
        Integer level = 0;

        List<Integer> nodeList = new ArrayList<Integer>();
        getNodesAtLevel(tree.getHead(), level, nodeList);
        System.out.println("printing BFSUsingRecursion");
        while (nodeList.size() > 0) {
            for (Integer value : nodeList) {
                System.out.print(value + " ");
            }
            System.out.println();

            nodeList.clear();
            ++level;
            getNodesAtLevel(tree.getHead(), level, nodeList);
        }
        System.out.println("end printing BFSUsingRecursion");
    }

    private void getNodesAtLevel(Node node, Integer level, List<Integer> nodes)
    {
        if (node == null || level < 0)
        {
            return;
        }

        if (level == 0)
        {
            nodes.add(node.getValue());
        }

        getNodesAtLevel(node.getLeft(), level-1, nodes);
        getNodesAtLevel(node.getRight(), level-1, nodes);
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