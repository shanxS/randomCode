// test if trees are isomorphic

public class Main {

    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        tree1.insert(4);
        tree1.insert(2);
        tree1.insert(6);
        tree1.insert(1);
        tree1.insert(5);
        tree1.insert(7);

        tree1.print();

        BinaryTree tree2 = new BinaryTree(tree1);
        tree2.rotate(4);
        tree2.rotate(2);
        tree2.print();

        
        IsomorphicTester it = new IsomorphicTester(tree1, tree2);
        System.out.println(it.areIsomorphic());
    }
}

class IsomorphicTester
{
    private BinaryTree tree1, tree2;

    public IsomorphicTester(BinaryTree tree1, BinaryTree tree2)
    {
        this.tree1 = tree1;
        this.tree2 = tree2;
    }

    public Boolean areIsomorphic() {
        return testIsomorphism(tree1.getHead(), tree2.getHead());
    }

    private Boolean testIsomorphism(Node node1, Node node2) {
        if ((node1 == null && node2 != null)
                || (node1 != null && node2 == null))
        {
            return false;
        }

        if(node1 == null && node2 == null)
        {
            return true;
        }
        if (node1.getValue() != node2.getValue())
        {
            return false;
        }

        return ((testIsomorphism(node1.getLeft(), node2.getLeft()) && testIsomorphism(node1.getRight(), node2.getRight()))
        || (testIsomorphism(node1.getLeft(), node2.getRight()) && testIsomorphism(node1.getRight(), node2.getLeft())));
    }
}

class BinaryTree
{
    private Node head;

    public BinaryTree()
    {
        this.head = null;
    }

    public BinaryTree(BinaryTree tree) {
        copyTree(tree.head);
    }

    private void copyTree(Node node) {
        if (node == null)
        {
            return;
        }

        insert(node.getValue());

        copyTree(node.getLeft());
        copyTree(node.getRight());
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
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else {
            if (node.getRight() != null) {
                insert(node.getRight(), value);
            } else {
                node.setRight(new Node(value));

            }
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


    public void rotate(Integer value) {
        Node node = fetchNode(head, value);
        if (node == null)
        {
            System.out.println("cannot find node " + value);
            return;
        }

        Node tmp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(tmp);

    }

    private Node fetchNode(Node node, Integer value) {
        if(node == null) {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }
        else if (node.getValue() > value)
        {
            return fetchNode(node.getLeft(), value);
        }
        else
        {
            return fetchNode(node.getRight(), value);
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

    public Node(Node node) {
        this.value = node.value;
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