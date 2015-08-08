// find subtree in BT

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar1 = {50,30,70,40,50};
        BST bst1 = new BST();
        for (Integer  i : ar1)
        {
            bst1.insert(i);
        }
//        bst1.print(bst1.getHead());

        Integer[] ar2 = {50,30,70,40};
        BST bst2 = new BST();
        for (Integer i : ar2)
        {
            bst2.insert(i);
        }

        (new SubTreeTester()).search(bst1.getHead(), bst2.getHead());
    }
}

class SubTreeTester
{
    private boolean found;
    private Node head1, head2;
    public void search(Node head1, Node head2)
    {
        found = false;
        this.head1 = head1;
        this.head2 = head2;

        searchForHead(head1, head2.getValue());

        System.out.print(found);
    }

    private void searchForHead(Node node, Integer target)
    {
        if (node == null || found)
        {
            return;
        }

        if (node.getValue() == target && tallyTrees(node, head2))
        {
            found = true;
            return;
        }

        searchForHead(node.getRight(), target);
        searchForHead(node.getLeft(), target);
    }

    private boolean tallyTrees(Node node1, Node node2)
    {
        if(node2 == null)
        {
            return true;
        }
        else if(node1 == null)
        {
            return false;
        }

        if (node1.getValue() != node2.getValue())
        {
            return false;
        }

        return tallyTrees(node1.getRight(), node2.getRight())
                && tallyTrees(node1.getLeft(), node2.getLeft());
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