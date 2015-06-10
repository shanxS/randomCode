// Transform a BST to greater sum tree
// code question: 70

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {11, 2, 29, 1, 7, 15, 40, 35};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());

        BSTToGreaterSumConverter bstToGreaterSumConverter = new BSTToGreaterSumConverter();
        bstToGreaterSumConverter.convert(bst.getHead());
        System.out.println("-------------------");
        BST.print(bst.getHead());
    }
}

class BSTToGreaterSumConverter
{
    public void convert(Node head)
    {
        reverseInorder(head, 0);
    }

    private Integer reverseInorder(Node node, Integer parentSum)
    {
        if (node == null)
        {
            return 0;
        }

        Integer thisValue = node.getValue();

        Integer rightSum = reverseInorder(node.getRight(), parentSum);
        node.setValue(rightSum + parentSum);

        Integer leftSum = reverseInorder(node.getLeft(), thisValue + node.getValue());

        return thisValue + leftSum + rightSum;
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
    private Node left, right;
    private Integer value;

    public Node(Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}