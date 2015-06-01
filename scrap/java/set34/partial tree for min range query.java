// partial tree for min range query
// related r2, last question, set 34

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{6, 1, 5, 4, 5, 2, 6};
        PartialTreeUtil ptu = new PartialTreeUtil();

        PartialTreeUtil.print(ptu.createTree(array));
        System.out.println(ptu.getMin(2, 5));
        System.out.println(ptu.getMin(2, 4));
    }
}

class PartialTreeUtil
{
    private Integer[] array;
    private Node head;

    public Integer getMin(Integer startIndex, Integer endIndex)
    {
        return getMin(head, startIndex, endIndex);
    }

    private Integer getMin(Node node, Integer startIndex, Integer endIndex)
    {
        if (node == null)
        {
            return Integer.MAX_VALUE;
        }
        else if (node.completeOverlap(startIndex, endIndex))
        {
            return node.getValue();
        }
        else if (node.partialOverlap(startIndex, endIndex))
        {
            Integer leftMin = getMin(node.getLeft(), startIndex, endIndex);
            Integer rightMin = getMin(node.getRight(), startIndex, endIndex);

            return Math.min(leftMin, rightMin);
        }
        else if (node.noOverlap(startIndex, endIndex))
        {
            return Integer.MAX_VALUE;
        }

        return Integer.MAX_VALUE;
    }

    public Node createTree(Integer[] array)
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

        System.out.print(node.getValue() + " [" + node.getStartIndex() + " - "
                + node.getEndIndex() + "] | ");
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

        Node thisNode = new Node(calculateRangeMin(startIndex, endIndex), startIndex, endIndex);

        if (startIndex != endIndex)
        {
            Integer mid = (startIndex + endIndex)/2;
            Node leftNode = createNode(startIndex, mid);
            Node rightNode = createNode(mid+1, endIndex);

            thisNode.setLeft(leftNode);
            thisNode.setRight(rightNode);
        }

        this.head = thisNode;
        return thisNode;
    }

    private Integer calculateRangeMin(Integer startIndex, Integer endIndex)
    {
        Integer min = Integer.MAX_VALUE;
        Integer cursor = startIndex;
        while(cursor <= endIndex)
        {
            if (array[cursor] < min)
            {
                min = array[cursor];
            }

            ++cursor;
        }

        return min;
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

    public boolean partialOverlap(Integer start, Integer end)
    {
        return !(completeOverlap(start, end)) && !(noOverlap(start, end));
    }

    public boolean noOverlap(Integer start, Integer end)
    {
        return ((startIndex < start && endIndex < start)
                || (startIndex > end && endIndex > end));
    }
}