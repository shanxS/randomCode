// query tree for array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {5,1,2,4,9,3};
        MakeQueryTree mqt = new MakeQueryTree();
        Node head = mqt.make(ar);
        mqt.print(head);

        Query query = new Query();
        System.out.println(query.findMinForRange(head, 0,2));
        System.out.println(query.findMinForRange(head, 2, 4));
        System.out.println(query.findMinForRange(head, 1, 4));
    }
}

class Query
{
    // assuming start and end are within bounds
    public Integer findMinForRange(Node node, Integer start, Integer end)
    {
        if (nodeLiesWithIn(node, start, end))
        {
            return node.getMin();
        }
        else if (nodePartiallyLiesIn(node, start, end))
        {
            return Math.min(findMinForRange(node.getLeft(), start, end),
                    findMinForRange(node.getRight(), start, end));
        }
        else
        {
            return Integer.MAX_VALUE;
        }
    }

    private boolean nodeLiesWithIn(Node node, Integer start, Integer end)
    {
        return (node.getStart() >= start && node.getEnd() <= end);
    }

    private boolean nodePartiallyLiesIn(Node node, Integer start, Integer end)
    {
        return (node.getEnd() >= start && node.getEnd() <= end &&
                    node.getStart() < start)
                || (node.getStart() >= start && node.getStart() <= end &&
                    node.getEnd() > end)
                || (node.getEnd() > end && node.getStart() < start);
    }
}

class MakeQueryTree
{
    private Integer[] ar;
    public Node make (Integer[] ar)
    {
        this.ar = ar;

        return makeFor(0, ar.length-1);
    }

    private Node makeFor(Integer start, Integer end)
    {
        if (start == end)
        {
            return new Node(ar[start], start, end);
        }

        Integer midIndex = start + ((end-start)/2);
        Node leftNode = makeFor(start, midIndex);
        Node rightNode = makeFor(midIndex+1, end);

        Node thisNode = new Node(Math.min(leftNode.getMin(), rightNode.getMin()),
                start, end, leftNode, rightNode);

        return thisNode;
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getStart() + ", " + node.getEnd() + "(" + node.getMin() + ")" + "  -   ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getStart() + ", " + node.getLeft().getEnd());
        }
        System.out.print(" | ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getStart() + ", " + node.getRight().getEnd());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }
}

class Node
{
    final private Integer start, end, value;
    private Node left, right;

    public Node(Integer value, Integer start, Integer end, Node left, Node right)
    {
        this.start = start;
        this.end = end;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(Integer v, Integer s, Integer e)
    {
        value = v;
        start = s;
        end = e;
    }

    public Node getLeft()
    {
        return left;
    }

    public Node getRight()
    {
        return right;
    }

    public Integer getStart()
    {
        return start;
    }

    public Integer getEnd()
    {
        return end;
    }

    public Integer getMin()
    {
        return value;
    }
}