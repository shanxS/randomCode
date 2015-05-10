// find inorder successor of node in BST
// written, q1, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{50,30,60,20,55};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        System.out.print(tree.getInorderSuccessor(30));
    }
}

class BST
{
    private Node head, parent;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.parent = null;
        this.ruleLeftLessThan = true;
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
        if (node.getValue() > value == ruleLeftLessThan)
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
        else if (node.getValue() > value != ruleLeftLessThan)
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

    public Integer getInorderSuccessor(Integer target)
    {
        Node node = findNode(head, target);
        if (node == null)
        {
            System.out.print("cant find parent");
            return null;
        }


        return (node.getRight() == null) ? parent.getValue() : findInorderSuccessor(node.getRight());
    }

    private Node findNode(Node node, Integer target)
    {
        if (node == null || node.getValue() == target)
        {
            return node;
        }

        if (node.getValue() > target == ruleLeftLessThan)
        {
            parent = node;
            return findNode(node.getLeft(), target);
        }
        else if (node.getValue() > target != ruleLeftLessThan)
        {
            parent = node;
            return findNode(node.getRight(), target);
        }

        return null;
    }

    private Integer findInorderSuccessor(Node node)
    {
        if (node == null)
        {
            return null;
        }

        Integer leftValue  = findInorderSuccessor(node.getLeft());
        if (leftValue != null)
        {
            return leftValue;
        }

        return node.getValue();
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