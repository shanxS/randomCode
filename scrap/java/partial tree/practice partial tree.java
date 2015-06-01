// practice partial tree

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{6, 1, 5, 4, 5, 2, 6};
        PartialTree pt = new PartialTree(array);
        PartialTree.print(pt.getHead());
        System.out.print(pt.findMinInRange(2,4));
    }
}

class PartialTree
{
    private Node head;
    private Integer[] array;

    public PartialTree(Integer[] array)
    {
        this.array = array;
        head = createNode(calculateMinInRange(0, array.length-1), 0, array.length-1);
    }

    public Integer findMinInRange(Integer start, Integer end)
    {
        return find(head, start, end);
    }

    private Integer find(Node node, Integer start, Integer end)
    {
        if (start > end)
        {
            return Integer.MAX_VALUE;
        }

        if (node.completeOverlap(start, end))
        {
            return node.getValue();
        }
        else if (node.noOverlap(start, end))
        {
            return Integer.MAX_VALUE;
        }
        else
        {
            return Math.min(find(node.getLeft(), start, end), find(node.getRight(), start, end));
        }
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " [" + node.getStartIndex() + ", " + node.getEndIndex() + "] | ");
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

    private Node createNode(Integer value, Integer startIndex, Integer endIndex)
    {
        if (startIndex > endIndex)
        {
            return null;
        }

        Node thisNode = new Node(value, startIndex, endIndex);

        if (startIndex != endIndex)
        {
            Integer mid = (startIndex+endIndex)/2;
            Node leftNode = createNode(calculateMinInRange(startIndex, mid), startIndex, mid);
            Node rightNode = createNode(calculateMinInRange(mid+1, endIndex), mid+1, endIndex);

            thisNode.setLeft(leftNode);
            thisNode.setRight(rightNode);
        }

        return thisNode;
    }

    private Integer calculateMinInRange(Integer startIndex, Integer endIndex)
    {
        Integer cursor = startIndex;
        Integer min = Integer.MAX_VALUE;
        while(cursor <= endIndex)
        {
            if (min > array[cursor])
            {
                min = array[cursor];
            }

            ++cursor;
        }

        return min;
    }

    public Node getHead()
    {
        return head;
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

    public boolean noOverlap(Integer start, Integer end)
    {
        return (startIndex < start && endIndex < start)
                || (startIndex > end && endIndex > end);
    }

    public boolean completeOverlap(Integer start, Integer end)
    {
        return (startIndex >= start && endIndex <= end);
    }
}