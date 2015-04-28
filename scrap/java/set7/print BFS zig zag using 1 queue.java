// print BFS zig zag using 1 queue
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

        BFSAlternatingUsingQueue bfsUsingRecursion = new BFSAlternatingUsingQueue(tree);
        bfsUsingRecursion.printBFS();
    }
}

class BFSAlternatingUsingQueue{
    private Tree tree;
    private List<Node> que;

    public BFSAlternatingUsingQueue(Tree tree)
    {
        this.tree = tree;
        this.que = new ArrayList<>();
    }

    public void printBFS() {
        que.add(tree.getHead());
        Integer thisLevel = que.size();
        Boolean changeDirection = true;
        addChildren(changeDirection);

        while(que.size() > 0)
        {
            Node node = que.get(0);

            System.out.print(node.getValue() + " ");



            que.remove(node);
            --thisLevel;
            if (thisLevel == 0)
            {
                thisLevel = que.size();
                changeDirection  = !changeDirection;
                addChildren(changeDirection);

                System.out.println();
            }
        }
    }

    private void addChildren(Boolean changeDirection)
    {
        for (Integer i = que.size()-1; i>=0; --i) {
            if (changeDirection) {
                if (que.get(i).getRight() != null) {
                    que.add(que.get(i).getRight());
                }

                if (que.get(i).getLeft() != null ) {
                    que.add(que.get(i).getLeft());
                }
            } else {
                if (que.get(i).getLeft() != null ) {
                    que.add(que.get(i).getLeft());
                }

                if (que.get(i).getRight() != null) {
                    que.add(que.get(i).getRight());
                }
            }
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