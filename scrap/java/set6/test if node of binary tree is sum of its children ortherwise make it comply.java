// r 3, q 1,2, set6 amazon
// 1) Given a Binary Tree, check if every node is sum of all of its children.
// 2) Given any Binary Tree, convert it to a tree where every node is sum of all of its children.

public class Main {

    public static void main(String[] args)
    {
        BinaryTree bt = new BinaryTree();
        bt.insert(6);
        bt.insert(1);
        bt.insert(10);
        bt.insert(0);
        bt.insert(4);
        bt.insert(8);
        bt.insert(3);
        bt.insert(5);
        bt.insert(7);
        bt.insert(9);

        bt.print();

        ChildSummer st = new ChildSummer(bt);
        System.out.println(st.test());
        st.complier();

        bt.print();
    }
}

class ChildSummer
{
    private BinaryTree binaryTree;

    public ChildSummer(BinaryTree bt)
    {
        binaryTree = bt;
    }

    public Boolean test() {
        try
        {
            test(binaryTree.getHead());
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void complier()
    {
        complier(binaryTree.getHead());
    }

    private Integer complier(Node node) {
        if (node == null)
        {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            return node.getValue();
        }

        Integer leftSum = complier(node.getLeft());
        Integer rightSum = complier(node.getRight());

        node.setValue(leftSum + rightSum);

        return leftSum + rightSum;

    }

    private Integer test(Node node) throws Exception
    {
        if (node == null)
        {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            return node.getValue();
        }

        Integer leftSum = test(node.getLeft());
        Integer rightSum = test(node.getRight());

        if (leftSum + rightSum != node.getValue())
        {
            throw new Exception("rule broke at " + node.getValue());
        }

        return leftSum + rightSum;
    }
}

class BinaryTree
{
    private Node head;

    public BinaryTree()
    {
        head = null;
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
        if (node.getValue() < value)
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new Node(value));
            }
            else
            {
                insert(node.getLeft(), value);
            }
        }
        else
        {
            if (node.getRight() == null)
            {
                node.setRight(new Node(value));
            }
            else
            {
                insert(node.getRight(), value);
            }
        }
    }

    public void print()
    {
        print(head);
    }

    private void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.println(node);

        print(node.getLeft());
        print(node.getRight());
    }

    public Node getHead() {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    @Override
    public String toString()
    {
        String str = getValue() + " - ";
        if (getLeft() != null)
        {
            str += getLeft().getValue();
        }
        str += ", ";
        if (getRight() != null)
        {
            str += getRight().getValue();
        }

        return str;
    }

    public Node(Integer value)
    {
        this.value = value;
        left = null;
        right = null;
    }

    /*
    public Integer getSummedValue()
    {
        if (left == null && right == null)
        {
            return getValue();
        }
        else
        {
            return 2*getValue();
        }
    }*/

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