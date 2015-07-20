// bst tester

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100,
                50,200,
                40,70, 150,250,
                null,45, 65,220, 140,null, 80
        };

        BT tree = new BT();
        Node head = tree.make(ar);
        tree.print(head);

        BSTTester bstTester = new BSTTester();
        System.out.print(bstTester.test(head));
    }
}

class BSTTester
{
    public boolean test(Node head)
    {
        return test(head, null, null);
    }

    private boolean test(Node node, Integer lessThan, Integer moreThan)
    {
        if (node == null)
        {
            return true;
        }

        if (lessThan != null && node.getValue() > lessThan)
        {
            return false;
        }

        if (moreThan != null && node.getValue() < moreThan)
        {
            return false;
        }

        return test(node.getLeft(), node.getValue(), moreThan)
                && test(node.getRight(), lessThan, node.getValue());
    }
}

class BT
{
    public Node make (Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parent = nodes[getParentIndex(i)];
            if (i%2==0)
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
            else
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
        }

        return nodes[0];
    }

    private int getParentIndex(Integer i)
    {
        return (i-1)/2;
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