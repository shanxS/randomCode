// find LCA with O(1) space complexity

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {100, 50, 200, 40, 75, 150, 220, 60, 80, 210, 215};
        BT tree = new BT();
        for (Integer i : ar)
        {
            tree.insert(i);
        }
        tree.print(tree.getHead());

        System.out.println("---------------");

        LCAFinder lcaFinder = new LCAFinder();
        System.out.print(lcaFinder.find(tree.getHead(), 220, 215));
    }
}

class LCAFinder
{
    private int target1, target2, LCA;
    final private int SEARCHING=0, FOUND1=1,FOUND2=2, BOTHFOUND=3, INVALID=Integer.MIN_VALUE;
    private int status;
    public int find(Node head, int target1, int target2)
    {
        this.target1 = target1;
        this.target2 = target2;
        status = SEARCHING;
        LCA = INVALID;

        findFor(head);

        return LCA;
    }

    private void findFor(Node node)
    {
        if (node == null || status == BOTHFOUND)
        {
            return;
        }

        if (status == SEARCHING &&
                (node.getValue() == target2 || node.getValue() == target1))
        {
            if (node.getValue() == target1)
            {
                status = FOUND1;
                if (findFor(node, target2))
                {
                    LCA = node.getValue();
                    status = BOTHFOUND;
                }
            }
            else if (node.getValue() == target2)
            {
                status = FOUND2;
                if (findFor(node, target1))
                {
                    LCA = node.getValue();
                    status = BOTHFOUND;
                }
            }

            return;
        }
        else if ((status == FOUND1 && node.getValue() == target2)
                || (status == FOUND2 && node.getValue() == target1))
        {
            status = BOTHFOUND;
            return;
        }

        findFor(node.getLeft());
        if ((status == FOUND1 || status == FOUND2) && LCA == INVALID)
        {
            LCA = node.getValue();
        }
        findFor(node.getRight());
        if ((status == FOUND1 || status == FOUND2) && LCA == node.getValue())
        {
            LCA = INVALID;
        }
    }

    private boolean findFor(Node node, int target)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getValue() == target)
        {
            return true;
        }

        return findFor(node.getLeft(), target)
                || findFor(node.getRight(), target);
    }
}

class BT
{
    private Node head;

    public BT()
    {
        head = null;
    }

    public void insert(int v)
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

    private void insert(Node node, int v)
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
    final private int value;
    private Node left, right;

    public Node (int v)
    {
        value = v;
    }

    public int getValue()
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