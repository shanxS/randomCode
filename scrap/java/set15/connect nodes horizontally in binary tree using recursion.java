// connect nodes horizontally in binary tree using recursion
// r3, q2, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30, 20, 40, 25, 35,45};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        System.out.print("depth " + tree.getDepth());

        HorizontalConnector hc = new HorizontalConnector(tree);
        BST.print(hc.connect());
    }
}

class HorizontalConnector
{
    private Node head;
    private Integer depth;
    private Node horizontalCursor;

    public HorizontalConnector(BST tree)
    {
        this.head = tree.getHead();
        this.depth = tree.getDepth();
        this.horizontalCursor = null;
    }

    public Node connect()
    {
        for (Integer i=0; i<depth; ++i)
        {
            horizontalCursor = null;
            gotoDepthAndConnect(head, i);
        }

        return head;
    }

    private void gotoDepthAndConnect(Node node, int depth)
    {
        if (node == null)
        {
            return;
        }

        if (depth == 0)
        {
            if (horizontalCursor == null)
            {
                horizontalCursor = node;
            }
            else
            {
                horizontalCursor.setLink(node);
                horizontalCursor = horizontalCursor.getLink();
            }

            return;
        }

        gotoDepthAndConnect(node.getLeft(), depth-1);
        gotoDepthAndConnect(node.getRight(), depth-1);
    }
}

class BST
{
    private Node head;
    private Integer depth;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.depth = 0;
        this.ruleLeftLessThan = true;
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
        if (node.getLink() != null)
        {
            System.out.print(" link: " + node.getLink().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            ++depth;
        }
        else
        {
            insert(head, value, 1);
        }
    }

    private void insert(Node node, Integer value, Integer currentDepth)
    {
        if ((node.getValue() > value) == ruleLeftLessThan)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value, currentDepth+1);
            }
            else
            {
                node.setLeft(new Node(value));
                if (currentDepth+1 > depth)
                {
                    depth = currentDepth+1;
                }
            }
        }
        else if ((node.getValue() > value) != ruleLeftLessThan)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value, currentDepth+1);
            }
            else
            {
                node.setRight(new Node(value));
                if (currentDepth+1 > depth)
                {
                    depth = currentDepth+1;
                }
            }
        }
    }

    public Node getHead()
    {
        return head;
    }

    public Integer getDepth()
    {
        return depth;
    }
}

class Node
{
    private Integer value;
    private Node left, right, link;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node getLink()
    {
        return link;
    }

    public void setLink(Node link)
    {
        this.link = link;
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