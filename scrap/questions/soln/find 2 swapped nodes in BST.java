// find 2 swapped nodes in BST
// code question: 76

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {30, 20, 40, 25, 35, 21, 29, 38};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

//        tree.swap(25, 35);
//        tree.swap(25, 20);
//        tree.swap(25, 30);
//        tree.swap(30, 29);
        tree.swap(21, 38);
        BST.print(tree.getHead());
        System.out.println("-------------------------------");

        SwapFinder sf = new SwapFinder();
        sf.find(tree.getHead());
        System.out.print(sf.value1.getValue() + " " + sf.value2.getValue());
    }
}

class SwapFinder
{
    public Node value1 = null;
    public Node value2 = null;
    private Node predecessor = null, successor = null;
    private boolean firstFound = false;

    public void find(Node head)
    {
        inorderTraversal(head);

        if (value2 == null && value1 != null)
        {
            value2 = successor;
        }
    }

    private void inorderTraversal(Node node)
    {
        if (node == null)
        {
            return;
        }

        inorderTraversal(node.getLeft());

        if (predecessor != null && predecessor.getValue() > node.getValue())
        {
            analyeAnomaly(node);
        }
        predecessor = node;

        inorderTraversal(node.getRight());
    }

    private void analyeAnomaly(Node node)
    {
        if (!firstFound)
        {
            value1 = predecessor;
            successor = node;
            firstFound = true;
        }
        else
        {
            value2 = node;
        }
    }
}

class BST
{
    private Node head;

    public BST()
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

    public void swap (Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        Node node2 = findNode(head, value2);

        // test for null of node1, node2

        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
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
        else if (node.getValue() > value)
        {
            return findNode(node.getLeft(), value);
        }
        else
        {
            return findNode(node.getRight(), value);
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
        if (node ==  null)
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
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}