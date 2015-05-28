// getting nearest leaf node in BST
// hiring manager, q1, set31

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{20,10,30,8,11,25,40,5,9};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        NearestLeafFinder nf = new NearestLeafFinder();
        //System.out.print(nf.findLeaf(tree, 10).getValue());
        //System.out.print(nf.findLeaf(tree, 20).getValue());
        System.out.print(nf.findLeaf(tree, 30).getValue());
    }
}

class NearestLeafFinder
{
    private Node nearestNode;
    private Integer shallowestDepth;

    public Node findLeaf(BST tree, Integer target)
    {
        nearestNode = null;
        shallowestDepth = Integer.MAX_VALUE;
        Node head = tree.getHead();
        Node targetNode = findNode (head, target);
        setNearestNode(targetNode, 0);

        return nearestNode;
    }

    private void setNearestNode(Node node, Integer depth)
    {
        if (node == null)
        {
            return;
        }

        if (isLeafNode(node) && depth < shallowestDepth)
        {
            shallowestDepth = depth;
            nearestNode = node;

            return;
        }

        setNearestNode(node.getLeft(), depth+1);
        setNearestNode(node.getRight(), depth+1);
    }

    private boolean isLeafNode(Node node)
    {
        return (node.getLeft() == null && node.getRight() == null);
    }

    private Node findNode(Node node, Integer target)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == target)
        {
            return node;
        }
        else if (node.getValue() > target)
        {
            return findNode(node.getLeft(), target);
        }
        else if (node.getValue() < target)
        {
            return findNode(node.getRight(), target);
        }

        return null;
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

    public Node(Integer value)
    {
        this.value = value;
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