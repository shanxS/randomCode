// morris traversal without altering the tree

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 50, 200, 40, 80, 250, 75, 85, 210};
        BST tree = new BST();
        for (Integer i : ar)
        {
            tree.insert(i);
        }
        tree.print(tree.getHead());
        System.out.println("--------------------");

        MorrosInorderTraversal mit = new MorrosInorderTraversal();
        mit.printMorris(tree.getHead());

        System.out.println("--------------------");
        tree.print(tree.getHead());

    }
}

class MorrosInorderTraversal
{
    public void printMorris(Node head)
    {
        Node cursor = head;

        while (cursor != null)
        {
            if (cursor.getLeft() == null)
            {
                System.out.print(cursor.getValue() + " ");
                cursor = cursor.getRight();
            }
            else if (cursor.getLeft() != null)
            {
                Node pre = cursor.getLeft();
                while (pre.getRight() != null && pre.getRight() != cursor)
                {
                    pre = pre.getRight();
                }
                if (pre.getRight() != cursor)
                {
                    pre.setRight(cursor);
                    cursor = cursor.getLeft();
                }
                else
                {
                    System.out.print(cursor.getValue() + " ");
                    cursor = cursor.getRight();
                    pre.setRight(null);
                }
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
        if (node.getValue() > v)
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

    public Node(Integer v)
    {
        value = v;
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