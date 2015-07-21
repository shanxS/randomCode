// find inroder sucessor

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {32, 24, 41, 21, 28, 36, 14, 25, 31, 39};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }

        InOrderPreFinder ipf = new InOrderPreFinder();
//        System.out.print(ipf.findFor(bst.getHead(), 24));
//        System.out.print(ipf.findFor(bst.getHead(), 28));
        System.out.print(ipf.findFor(bst.getHead(), 32));

    }
}

class InOrderPreFinder
{
    public Integer findFor (Node head, Integer target)
    {
        return find(head, null, target);
    }

    private Integer find(Node node, Node parent, Integer target)
    {
        if (node == null)
        {
            return -1;
        }

        if (node.getValue() == target)
        {
            if (node.getRight() != null)
            {
                Node cursor = node.getRight();
                while (cursor.getLeft() != null)
                {
                    cursor = cursor.getLeft();
                }

                return cursor.getValue();
            }
            else
            {
                return (parent == null) ? null : parent.getValue();
            }
        }
        else if (node.getValue() < target)
        {
            return find(node.getRight(), node, target);
        }
        else
        {
            return find(node.getLeft(), node, target);
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

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

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