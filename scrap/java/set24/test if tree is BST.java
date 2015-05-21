// test if tree is BST
// telephonic, q2, set24

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{50,30,80,20,40,70,100,25,35,90};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        //tree.swap(25,70);
        //tree.swap(40,20);
        tree.swap(30, 20);

        BSTTester bt = new BSTTester();
        System.out.print(bt.test(tree));
    }
}


class BSTTester
{
    private Node head;

    public Boolean test(BST tree)
    {
        this.head = tree.getHead();

        return testTree(head, null, null);
    }

    private Boolean testTree(Node node, Node lessNode, Node greaterNode)
    {
        if (node == null)
        {
            return true;
        }

        if ((lessNode != null && lessNode.getValue() > node.getValue())
            || (greaterNode != null && greaterNode.getValue() < node.getValue()))
        {
            return false;
        }

        return testTree(node.getLeft(), lessNode, node) && testTree(node.getRight(), node, greaterNode);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
    }

    public void swap (Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        if (node1 == null)
        {
            System.out.print("cant find node " + value1);
        }
        Node node2 = findNode(head, value2);
        if (node2 == null)
        {
            System.out.print("cant find node " + value2);
        }

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

        if (node.getValue() > value)
        {
            return findNode(node.getLeft(), value);
        }
        else if (node.getValue() < value)
        {
            return findNode(node.getRight(), value);
        }

        return null;
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node (Integer value)
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