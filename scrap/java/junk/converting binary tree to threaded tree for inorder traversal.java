// converting binary tree to threaded tree for inorder traversal

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 50, 200, 40, 75, 250, 55, 80, 210};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }
        tree.print(tree.getHead());
        System.out.println("-------------");

        MorrisConverter mc = new MorrisConverter();
        mc.Convert(tree.getHead());
        mc.print(tree.getHead());
    }
}

class MorrisConverter
{
    public void Convert(Node head)
    {
        convertFor(head, null, null);
    }

    private void convertFor(Node node, Node pre, Node succ)
    {
        if (node == null)
        {
            return;
        }

        if (isLeaf(node))
        {
            node.setRight(succ);
            node.setThreaded();
//            node.setLeft(pre);

            return;
        }

        convertFor(node.getLeft(), pre, node);
        convertFor(node.getRight(), node, succ);
    }

    private boolean isLeaf(Node node)
    {
        return (node.getRight() == null) && (node.getLeft() == null);
    }

    public void print(Node head)
    {
        Node cursor = head;

        while (cursor != null)
        {
            while (cursor.getLeft() != null)
            {
                cursor = cursor.getLeft();
            }

            System.out.print(cursor.getValue() + " ");
            boolean wasCusrorThreaded = cursor.isThreaded();
            cursor = cursor.getRight();
            if (cursor != null && wasCusrorThreaded)
            {
                System.out.print(cursor.getValue() + " ");
                cursor = cursor.getRight();
            }
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

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
    }

    private void insert(Node node, Integer v)
    {
        if(node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    public void print(Node node)
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    final private Integer value;
    private boolean isThreaded;

    public Node(Integer v)
    {
        value = v;
        isThreaded = false;
    }

    public void setThreaded()
    {
        isThreaded = true;
    }

    public boolean isThreaded()
    {
        return isThreaded;
    }

    public Integer getValue()
    {
        return value;
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