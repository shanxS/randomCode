// partial tree for range query

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 3, 5, 7, 9, 11};
        PartialTree pt = new PartialTree(array);
        pt.print();

        RangeQueryEngine rqe = new RangeQueryEngine();
        System.out.print(rqe.findSumInRange(2, 4, pt.getHead()));
    }
}

class RangeQueryEngine
{
    private Node pt;

    public Integer findSumInRange(Integer start, Integer end, Node pt)
    {
        this.pt = pt;

        return queryFor(pt, start, end);
    }

    private Integer queryFor(Node node, Integer start, Integer end)
    {
        if (node == null)
        {
            return 0;
        }

        if (node.completeOverlap(start, end))
        {
            return node.getValue();
        }
        else if (node.noOverlap(start, end))
        {
            return 0;
        }
        else //if (node.noOverlap(start, end))
        {
            return queryFor(node.getRight(), start, end)
                    + queryFor(node.getLeft(), start, end);
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
        this.head = createNodeFor(0, array.length-1);
    }

    private Node createNodeFor(Integer start, Integer end)
    {
        if (end < start)
        {
            return null;
        }

        Node thisNode = new Node(computeSum(start, end), start, end);

        Node leftNode = null, rightNode = null;

        if (end-start == 1)
        {
            leftNode = createNodeFor(start, start);
            rightNode = createNodeFor(end, end);
        }
        else if(end-start > 1)
        {
            Integer mid = (start + end) / 2;
            leftNode = createNodeFor(start, mid);
            rightNode = createNodeFor(mid+1, end);
        }

        thisNode.setLeft(leftNode);
        thisNode.setRight(rightNode);

        return thisNode;
    }

    public void print()
    {
        print(head);
    }

    private void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " [" + node.getStart() + " " + node.getEnd() + "] - ");
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

    private Integer computeSum(Integer start, Integer end)
    {
        Integer sum = 0;
        for (Integer i=start; i<=end; ++i)
        {
            sum += array[i];
        }

        return sum;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer start, end;
    private Integer value;
    private Node left, right;

    public Node(Integer value, Integer start, Integer end)
    {
        this.end = end;
        this.start = start;
        this.value = value;
    }

    public Integer getStart()
    {
        return start;
    }

    public Integer getEnd()
    {
        return end;
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

    public boolean completeOverlap(Integer startIndex, Integer endIndex)
    {
        return (start >= startIndex && end <= endIndex);
    }

    public boolean noOverlap(Integer startIndex, Integer endIndex)
    {
        return (start > endIndex || end < startIndex);
    }
}