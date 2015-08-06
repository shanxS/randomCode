// swapped node in bst

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {20,15,30,5,10,25,40};
        BTUtil util = new BTUtil();
        Node head = util.makeFor(ar);
        (new Fixer()).fix(head);
    }
}

class Fixer
{
    Node node1, node2, node1Conflict;
    public void fix(Node head)
    {
        node1 = null;
        node2 = null;

        evaluate(head, null, null);

        if (node1 == null && node2 == null)
        {
            System.out.print("no swap");
        }
        else if (node2 == null)
        {
            System.out.print(node1.getValue() + " " + node1Conflict.getValue());
        }
        else
        {
            System.out.print(node1.getValue() + " " + node2.getValue());
        }
    }

    private void evaluate(Node node, Node leftParen, Node rightParent)
    {
        if (node == null)
        {
            return;
        }

        evaluate(node.getLeft(), node, rightParent);

        if (leftParen != null && node.getValue() > leftParen.getValue())
        {
            setDiff(node, leftParen);
        }
        if (rightParent != null && node.getValue() < rightParent.getValue() )
        {
            setDiff(node, rightParent);
        }

        evaluate(node.getRight(), leftParen, node);
    }

    private void setDiff(Node node, Node conflictWith)
    {
        if (node1 == null)
        {
            node1 = node;
            node1Conflict = conflictWith;
        }
        else if (node2 == null)
        {
            node2 = node;
        }
        else
        {
            System.out.print("more than expected conflicts");
        }
    }
}

class BTUtil
{

    public Node makeFor(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {continue;}

            Node parent = nodes[getParentIndex(i)];
            if (i%2 == 0)
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

    public void setValue(Integer value)
    {
        this.value = value;
    }
}