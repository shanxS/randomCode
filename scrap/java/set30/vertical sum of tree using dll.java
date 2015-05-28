// vertical sum of tree using dll
// r2, q3, set30

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,35,45,15};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

        VerticalSummer vs = new VerticalSummer();
        Node sum = vs.sum(tree);
        while(sum != null)
        {
            System.out.print(sum.getValue() + " ");
            sum = sum.getRight();
        }

    }
}

class VerticalSummer
{
    private Node headDll, headBST;

    public Node sum(BST tree)
    {
        headBST = tree.getHead();
        headDll = new Node(headBST.getValue());

        sum(headBST, headDll);

        while (headDll.getLeft() != null)
        {
            headDll = headDll.getLeft();
        }

        return headDll;
    }

    private void sum(Node nodeBST, Node nodeDll)
    {
        if (nodeBST.getLeft() != null)
        {
            Integer leftBSTValue = nodeBST.getLeft().getValue();

            if (nodeDll.getLeft() == null)
            {
                Node leftDLLNode = new Node(leftBSTValue);
                nodeDll.setLeft(leftDLLNode);
                leftDLLNode.setRight(nodeDll);
            }
            else
            {
                Integer previousValue = nodeDll.getLeft().getValue();
                nodeDll.getLeft().setValue(previousValue + leftBSTValue);
            }

            sum(nodeBST.getLeft(), nodeDll.getLeft());
        }

        if (nodeBST.getRight() != null)
        {
            Integer rightBSTValue = nodeBST.getRight().getValue();

            if (nodeDll.getRight() == null)
            {
                Node rightDLLNode = new Node (rightBSTValue);
                nodeDll.setRight(rightDLLNode);
                rightDLLNode.setLeft(nodeDll);
            }
            else
            {
                Integer previousValue = nodeDll.getRight().getValue();;
                nodeDll.getRight().setValue(previousValue + rightBSTValue);
            }

            sum(nodeBST.getRight(), nodeDll.getRight());
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
        this.left = null;
        this.right = null;
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