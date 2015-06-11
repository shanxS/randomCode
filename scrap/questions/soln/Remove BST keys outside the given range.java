// Remove BST keys outside the given range
// code question: 78

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

        BST.print(tree.getHead());
        System.out.println("------------------------");

        BSTCutter cutter = new BSTCutter();
//        BST.print(cutter.curInRange(tree.getHead(), 29, 40));
        BST.print(cutter.curInRange(tree.getHead(), 20, 29));
    }
}

class BSTCutter
{
    private Integer min, max;

    public Node curInRange(Node head, Integer min, Integer max)
    {
        this.min = min;
        this.max = max;

        if (head == null)
        {
            return null;
        }

        return traverseInorder(head);
    }

    private Node traverseInorder(Node node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() < min)
        {
            return traverseInorder(node.getRight());
        }
        else if (node.getValue() > max)
        {
            return traverseInorder(node.getLeft());
        }
        else
        {
            node.setLeft(traverseInorder(node.getLeft()));
            node.setRight(traverseInorder(node.getRight()));
            return node;
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
