// max rectangular area of histogram - using partial tree

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{6, 2, 5, 4, 5, 2, 6};
        Integer[] array = new Integer[]{6, 2, 5, 4, 5, 1, 6};
        PartialTree pt = new PartialTree(array);

        pt.print(pt.getHead());

        MaxAreaFinder mf = new MaxAreaFinder();
        System.out.print(mf.getMaxSize(pt, array));
    }
}

class MaxAreaFinder
{
    private Integer size;
    private Integer[] array;
    private PartialTree pt;

    public Integer getMaxSize(PartialTree pt, Integer[] array)
    {
        size = Integer.MIN_VALUE;
        this.pt = pt;
        this.array = array;

        calculateSizeForNode(0, array.length-1);

        return size;
    }

    private void calculateSizeForNode(Integer startIndex, Integer endIndex)
    {
        Integer minIndex = pt.findMinIndexInRange(startIndex, endIndex);

        if (minIndex == null)
        {
            return;
        }

        Integer thisSize = array[minIndex] * (endIndex - startIndex + 1);
        if (thisSize > size)
        {
            size = thisSize;
        }

        if (minIndex > 0 && minIndex < array.length)
        {
            calculateSizeForNode(startIndex, minIndex-1);
            calculateSizeForNode(minIndex+1, endIndex);
        }
    }
}

class PartialTree
{
    private Node head;
    private Integer[] array;

    public PartialTree(Integer[] array)
    {
        this.array = array;
        head = createNode(searchMinValueIndex(0, array.length-1), 0, array.length-1);
    }

    public Integer findMinIndexInRange(Integer startIndex, Integer endIndex)
    {
        return findMinIndexInRange(head, startIndex, endIndex);
    }

    private Integer findMinIndexInRange(Node node, Integer start, Integer end)
    {
        if (node == null)
        {
            return null;
        }

        if (node.completeOverlap(start, end))
        {
            return node.getMinIndex();
        }
        else if (node.noOverlap(start, end))
        {
            return null;
        }
        else
        {
            Integer leftMinIndex = findMinIndexInRange(node.getLeft(), start, end);
            Integer rightMinIndex = findMinIndexInRange(node.getRight(), start, end);

            if (leftMinIndex != null && rightMinIndex != null)
            {
                if (array[leftMinIndex] < array[rightMinIndex])
                {
                    return leftMinIndex;
                }
                else
                {
                    return rightMinIndex;
                }
            }
            else if (leftMinIndex != null)
            {
                return leftMinIndex;
            }
            else
            {
                return rightMinIndex;
            }
        }
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " [" + node.getStartIndex() + ", " + node.getEndIndex() + "] | ");
        if (node.getLeft() != null)
        {
            System.out.print(array[node.getLeft().getMinIndex()]);
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(array[node.getRight().getMinIndex()]);
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    private Node createNode(Integer minIndex, Integer start, Integer end)
    {
        Node thisNode = new Node(minIndex, array[minIndex], start, end);

        if (start != end)
        {
            Integer mid = (start+end)/2;
            Node leftNode = createNode(searchMinValueIndex(start, mid), start, mid);
            Node rightNode = createNode(searchMinValueIndex(mid+1, end), mid+1, end);

            thisNode.setLeft(leftNode);
            thisNode.setRight(rightNode);
        }

        return thisNode;
    }

    private Integer searchMinValueIndex(Integer startIndex, Integer endIndex)
    {
        Integer cursor = startIndex;
        Integer minIndex = cursor;
        Integer minValue = Integer.MAX_VALUE;

        while(cursor <= endIndex)
        {
            if (array[cursor] < minValue)
            {
                minIndex = cursor;
                minValue = array[cursor];
            }
            ++cursor;
        }

        return minIndex;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer minIndex, value, startIndex, endIndex;
    private Node left, right;

    public Node(Integer minIndex, Integer value, Integer startIndex, Integer endIndex)
    {
        this.minIndex = minIndex;
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

    public Integer getMinIndex()
    {
        return minIndex;
    }

    public void setMinIndex(Integer minIndex)
    {
        this.minIndex = minIndex;
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
        return (startIndex < start && endIndex < start)
                && (startIndex > end && endIndex > end);
    }
}