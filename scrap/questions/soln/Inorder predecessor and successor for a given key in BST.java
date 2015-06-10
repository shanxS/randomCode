// Inorder predecessor and successor for a given key in BST
// code question: 69

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {20, 8, 22, 4, 12, 10, 14};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        InorderPreAndSuc in = new InorderPreAndSuc();
//        in.setPreAndSuc(bst.getHead(), 10);
//        in.setPreAndSuc(bst.getHead(), 4);
        in.setPreAndSuc(bst.getHead(), 22);

        if (in.pre == null)
        {
            System.out.println("no pre");
        }
        else
        {
            System.out.println(in.pre.getValue());
        }

        if (in.suc == null)
        {
            System.out.println("no suc");
        }
        else
        {
            System.out.println(in.suc.getValue());
        }
    }
}

class InorderPreAndSuc
{
    public Node pre = null, suc = null;
    private Boolean setSuc;
    private Integer targerValue;

    public void setPreAndSuc(Node head, Integer targetValue)
    {
        this.setSuc = false;
        this.targerValue = targetValue;

        traverseInorder(head, null);
    }

    private void traverseInorder(Node node, Node pre)
    {
        if (node == null)
        {
            return;
        }

        traverseInorder(node.getLeft(), pre);

        if (node.getValue() == targerValue)
        {
            this.setSuc = true;

            if (node.getLeft() != null)
            {
                this.pre = node.getLeft();
            }
            else
            {
                this.pre = pre;
            }
        }
        else if (this.setSuc)
        {
            this.suc = node;
            setSuc = false;
        }

        traverseInorder(node.getRight(), node);

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
        if(node == null)
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