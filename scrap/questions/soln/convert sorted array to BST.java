// convert sorted array to BST
// code question: 81

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1,2,3};
        Integer[] array = {1,2,3,4};
        SortedArrayToBST s2b = new SortedArrayToBST();
        SortedArrayToBST.print(s2b.convert(array));
    }
}

class SortedArrayToBST
{
    private Integer[] array;

    public Node convert(Integer[] array)
    {
        this.array = array;
        return getNodeFor(0, array.length-1);
    }

    private Node getNodeFor(Integer start, Integer end)
    {
        if (end < start)
        {
            return null;
        }

        Integer midIndex = start + (end-start)/2;

        Node thisNode = new Node(array[midIndex]);

        Node leftNode = getNodeFor(start, midIndex-1);
        thisNode.setLeft(leftNode);

        Node rightNode = getNodeFor(midIndex+1, end);
        thisNode.setRight(rightNode);

        return thisNode;
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