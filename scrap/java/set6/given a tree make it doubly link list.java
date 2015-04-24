public class Main {

    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(7);
        bst.insert(3);
        bst.insert(1);
        bst.insert(5);

        bst.setParent();

        bst.print();

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

    public void setParent()
    {
        setParent(head);
    }

    private void setParent(Node node) {
        if (node == null)
        {
            return;
        }

        if (node.getRight() != null)
        {
            node.getRight().setParent(node);
            setParent(node.getRight());
        }

        if (node.getLeft() != null)
        {
            node.getLeft().setParent(node);
            setParent(node.getLeft());
        }
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

        System.out.print(node.getValue() + ": " + (node.getParent() == null ? "null" : node.getParent().getValue()) + " - ");
        if (node.getRight()!= null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.print(", ");
        if (node.getLeft()!= null)
        {
            System.out.print(node.getLeft().getValue());
        }

        System.out.println();

        print(node.getRight());
        print(node.getLeft());
    }

    private void insert(Node head, Integer value) {
        if (head.getValue() < value)
        {
            if (head.getLeft() != null)
            {
                insert(head.getLeft(), value);
            }
            else
            {
                head.setLeft(new Node(value));
            }
        }
        else
        {
            if (head.getRight() != null)
            {
                insert(head.getRight(), value);
            }
            else
            {
                head.setRight(new Node(value));
            }
        }
    }
}

class Node
{
    private Integer value;
    private Node left;
    private Node right;
    private Node parent;

    public Node(Integer value)
    {
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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