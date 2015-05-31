// duplicate binary tree with mad pointer
// code question 24

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 10, 40, 25, 50, 15, 45, 60};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        tree.connectMad(10,15);
        tree.connectMad(25,50);
        tree.connectMad(45, 40);

        BST.print(tree.getHead());
        System.out.println("---------------");

        Duplicator duplicator = new Duplicator();
        BST.print(duplicator.duplicate(tree.getHead()));
    }
}

class Duplicator
{
    public Node duplicate(Node head)
    {
        doubleNodes(head);
        connectMad(head);

        Node copyHead = head.getRight();

        untangle(head);

        return copyHead;
    }

    private void untangle(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = node.getRight();
        node.setRight(copyNode.getRight());

        if (node.getRight() != null)
        {
            copyNode.setRight(node.getRight().getRight());
        }
        if (node.getLeft() != null)
        {
            copyNode.setLeft(node.getLeft().getRight());
        }

        untangle(node.getRight());
        untangle(node.getLeft());
    }

    private void connectMad(Node node)
    {
        if (node == null)
        {
            return;
        }

        if (node.getMad() != null)
        {
            Node subNode = node.getRight();
            subNode.setMad(node.getMad().getRight());
        }

        connectMad(node.getRight().getRight());
        connectMad(node.getLeft());
    }

    private void doubleNodes(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node subNode = new Node(node.getValue());
        subNode.setRight(node.getRight());
        node.setRight(subNode);

        doubleNodes(node.getRight().getRight());
        doubleNodes(node.getLeft());
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
    }

    public void connectMad(Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        Node node2 = findNode(head, value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cnat find nodes to connect");
        }

        node1.setMad(node2);
    }

    private Node findNode(Node node, Integer value)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }

        if (node.getValue() > value)
        {
            return findNode(node.getLeft(), value);
        }
        else
        {
            return findNode(node.getRight(), value);
        }
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
        if (node.getValue() > value)
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
        System.out.print(" - ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
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
    private Node left, right, mad;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }
}