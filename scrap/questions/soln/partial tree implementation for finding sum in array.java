// partial tree implementation for finding sum in array
// code question 32

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,3,5,7,9,11};
        PartialTreeGenerator pt = new PartialTreeGenerator();
        Node tree = pt.create(array);

        PartialTreeGenerator.print(tree);

        //System.out.print(pt.getSum(tree, 2, 4));
        //System.out.print(pt.getSum(tree, 0, 1));
        System.out.print(pt.getSum(tree, 4, 2));
    }
}

class PartialTreeGenerator
{
    private Integer[] array;

    public Integer getSum(Node node, Integer startIndex, Integer endIndex)
    {
        if (node == null)
        {
            return 0;
        }

        if (node.completeOverlap(startIndex, endIndex))
        {
            return node.getValue();
        }
        else if (node.noOverlap(startIndex, endIndex))
        {
            return 0;
        }
        else if (node.partialOverlap(startIndex, endIndex))
        {
            Integer leftValue = getSum(node.getLeft(), startIndex, endIndex);
            Integer rightValue = getSum(node.getRight(), startIndex, endIndex);

            return leftValue + rightValue;
        }

        return null;
    }

    public Node create(Integer[] array)
    {
        this.array = array;

        return createNode(0, array.length-1);
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " [" + node.getStartIndex() + ", " + node.getEndIndex() + "] "  + " | ");
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

    private Node createNode(Integer startIndex, Integer endIndex)
    {
        if (startIndex > endIndex)
        {
            return null;
        }

        Node thisNode = new Node(calculateRangeSum(startIndex, endIndex), startIndex, endIndex);

        if (startIndex != endIndex)
        {
            Integer mid = (startIndex + endIndex) / 2;
            Node leftNode = createNode(startIndex, mid);
            Node rightNode = createNode(mid+1, endIndex);

            thisNode.setLeft(leftNode);
            thisNode.setRight(rightNode);
        }

        return thisNode;
    }

    private Integer calculateRangeSum(Integer startIndex, Integer endIndex)
    {
        Integer sum = 0;
        Integer cursor = startIndex;
        while(cursor <= endIndex)
        {
            sum += array[cursor];
            ++cursor;
        }

        return sum;
    }
}

class Node
{
    private Integer value, startIndex, endIndex;
    private Node left, right;

    public Node(Integer value, Integer startIndex, Integer endIndex)
    {
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public Integer getStartIndex()
    {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex)
    {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex()
    {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex)
    {
        this.endIndex = endIndex;
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

    public boolean completeOverlap(Integer start, Integer end)
    {
        return (startIndex >= start && endIndex <= end);
    }

    public boolean noOverlap(Integer start, Integer end)
    {
        return ((startIndex < start && endIndex < start)
            || (startIndex > end && endIndex > end));
    }

    public boolean partialOverlap(Integer start, Integer end)
    {
        return !(completeOverlap(start, end)) && !(noOverlap(start, end));
    }
}