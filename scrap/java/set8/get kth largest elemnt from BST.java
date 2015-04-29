// get kth largest elemnt from BST
// telephonic q1, set 8, amazon

public class Main {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(20);
        bst.insert(15);
        bst.insert(30);

        LargestGetter lg = new LargestGetter(bst);
        System.out.println(lg.getKthLargest(2));
    }


}

class LargestGetter
{
    private BinarySearchTree bst;
    private Boolean isLeftSmallRule;

    public LargestGetter(BinarySearchTree tree)
    {
        this.bst = tree;
        this.isLeftSmallRule = null;
    }

    public Integer getKthLargest(Integer targetlevel) {
        setRule();

        Integer thisLevel = 0;
        Node node = getLargestNodeAt(thisLevel, targetlevel, bst.getHead());

        return (node == null) ? null : node.getValue();
    }

    private Node getLargestNodeAt(Integer thisLevel, Integer targetlevel, Node node) {
        if (node == null && thisLevel != targetlevel)
        {
            return null;
        }

        if (thisLevel == targetlevel)
        {
            return node;
        }
        else
        {
            if (isLeftSmallRule) {
                return getLargestNodeAt(thisLevel + 1, targetlevel, node.getRight());
            }
            else
            {
                return getLargestNodeAt(thisLevel + 1, targetlevel, node.getLeft());
            }
        }
    }

    private void setRule() {
        Node head = bst.getHead();
        Node left = head.getLeft();
        Node right = head.getRight();

        if (left == null && right == null)
        {
            isLeftSmallRule = null;
            System.out.println("cannot set rule");
        }
        else if (left == null)
        {
            isLeftSmallRule = head.getValue() < right.getValue();
        }
        else if (right == null)
        {
            isLeftSmallRule = head.getValue() > left.getValue();
        }
        else
        {
            if ((head.getValue() > left.getValue()) == (head.getValue() < right.getValue()))
            {
                isLeftSmallRule = head.getValue() > left.getValue();
            }
            else
            {
                isLeftSmallRule = null;
                System.out.println("cannot set rule");
            }
        }
    }
}

class BinarySearchTree
{
    private Node head;

    public BinarySearchTree()
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
        else
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