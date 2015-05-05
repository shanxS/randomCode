// enhanced BST tester
// r3, q3, set10

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{20, 15, 30, 10, 18, 25, 40, 2, 19};
        BST tree = new BST();
        for (Integer i : array)
        {
            tree.insert(i);
        }

        BST.print(tree.getHead());
        Swapper swapper = new Swapper(tree);
        //swapper.swap(25, 18);
        System.out.println("---------------");
        BST.print(tree.getHead());

        BSTTester tester = new BSTTester(tree);
        System.out.println(tester.isBST());
    }
}

class BSTTester
{
    private Node head;
    private Boolean rule;
    private final Boolean leftGrandChild;
    private final Boolean rightGrandChild;

    public BSTTester(BST tree)
    {
        this.head = tree.getHead();
        this.rule = null;
        this.leftGrandChild = false;
        this.rightGrandChild = true;
    }

    public boolean isBST()
    {
        setRule();

        if (rule == null)
        {
            System.out.println("cannot determine rule");
            return false;
        }

        return validate (null, head, null);
    }

    private boolean validate(Node parent, Node node, Boolean relationWithParent)
    {
        if (node == null)
        {
            return true;
        }

        if (node.getRight() != null && node.getLeft() == null)
        {
            if (rule != head.getValue() > head.getRight().getValue()
                    && checkSubTreeValidity(parent, head.getRight(), relationWithParent))
            {
                return validate(node, node.getRight(), rightGrandChild);
            }
            else
            {
                System.out.println("rule breaks at " + node.getValue() + " right child " + node.getRight().getValue());
                return false;
            }
        }
        else if (node.getRight() == null && node.getLeft() != null)
        {
            if (rule = node.getValue() > node.getLeft().getValue()
                    && checkSubTreeValidity(parent, node.getLeft(), relationWithParent))
            {
                return validate(node, node.getLeft(), leftGrandChild);
            }
            else
            {
                System.out.println("rule breaks at " + node.getValue() + " left child " + node.getLeft().getValue());
                return false;
            }
        }
        else if (node.getRight() != null && node.getLeft() != null)
        {
            Node right = node.getRight();
            Node left = node.getLeft();

            if ((node.getValue() > left.getValue()) != (node.getValue() > right.getValue())
                    && checkSubTreeValidity(parent, node.getLeft(), relationWithParent)
                    && checkSubTreeValidity(parent, node.getRight(), relationWithParent))
            {
                if (rule == node.getValue() > left.getValue())
                {
                    return validate(node, node.getLeft(), leftGrandChild) && validate(node, node.getRight(), rightGrandChild);
                }
            }
            else
            {
                System.out.println("rule breaks at " + node.getValue() + " left child " + node.getLeft().getValue()
                                  + " right child " + node.getRight().getValue());
                return false;
            }
        }

        return true;
    }

    private boolean checkSubTreeValidity(Node grandParent, Node node, Boolean relationWithGrandParent)
    {
        if (relationWithGrandParent == null || grandParent == null)
        {
            return true;
        }

        if (relationWithGrandParent == leftGrandChild)
        {
            return (grandParent.getValue() > node.getValue()) == rule;
        }
        else
        {
            return (grandParent.getValue() > node.getValue()) != rule;
        }
    }

    private void setRule()
    {
        if (head == null)
        {
            rule = null;
            return;
        }

        if (head.getRight() != null && head.getLeft() == null)
        {
            rule = head.getValue() < head.getRight().getValue();
        }
        else if (head.getRight() == null && head.getLeft() != null)
        {
            rule = head.getValue() > head.getLeft().getValue();
        }
        else if (head.getRight() != null && head.getLeft() != null)
        {
            Node right = head.getRight();
            Node left = head.getLeft();

            if ((head.getValue() > left.getValue()) != (head.getValue() > right.getValue()))
            {
                rule = head.getValue() > left.getValue();
            }
            else
            {
                rule = null;
            }
        }
        else if (head.getRight() == null && head.getLeft() == null)
        {
            rule = null;
        }
    }
}

class Swapper
{
    private Node head;

    public Swapper(BST tree)
    {
        this.head = tree.getHead();
    }

    public void swap(Integer value1, Integer value2)
    {
        Node node1 = fetchNode(head, value1);
        Node node2 = fetchNode(head, value2);

        Integer temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    private Node fetchNode(Node node, Integer value)
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
            return fetchNode(node.getLeft(), value);
        }
        else if (node.getValue() < value)
        {
            return fetchNode(node.getRight(), value);
        }

        return null;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
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

    public Node getHead()
    {
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
}