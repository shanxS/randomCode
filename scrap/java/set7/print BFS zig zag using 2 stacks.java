// print BFS zig zag using 2 stacks
// r2, q1, set7, amazon

import java.util.Stack;

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

        BFSAlternatingUsing2Stacks bfsUsingRecursion = new BFSAlternatingUsing2Stacks(tree);
        bfsUsingRecursion.printBFS();
    }
}

class BFSAlternatingUsing2Stacks
{
    private Tree tree;
    private Stack<Node> stackRight;
    private Stack<Node> stackLeft;

    public BFSAlternatingUsing2Stacks(Tree tree)
    {
        this.tree = tree;
        this.stackRight = new Stack<>();
        this.stackLeft = new Stack<>();
    }

    public void printBFS()
    {
        stackLeft.push(tree.getHead());
        Boolean stackSwitch = true;

        while(stackLeft.size() >= 0 || stackRight.size() >= 0)
        {
            Node node = null;

            if (stackSwitch)
            {
                node = stackLeft.pop();
            }
            else
            {
                node = stackRight.pop();
            }

            System.out.print(node.getValue() + " ");

            if (stackSwitch)
            {
                if (node.getRight() != null) {
                    stackRight.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stackRight.push(node.getLeft());
                }

                if (stackLeft.size() == 0)
                {
                    stackSwitch = !stackSwitch;
                    System.out.println();
                }
            }
            else
            {
                if (node.getLeft() != null) {
                    stackLeft.push(node.getLeft());
                }
                if (node.getRight() != null) {
                    stackLeft.push(node.getRight());
                }

                if (stackRight.size() == 0)
                {
                    stackSwitch = !stackSwitch;
                    System.out.println();
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