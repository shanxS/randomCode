// sum path to node

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {
                10,5,12,4,7
        };

        BT tree = new BT();
        Node head = tree.make(ar);
        tree.print(head);

        SumTester st = new SumTester();
        st.printPaths(head, 22);
    }
}

class SumTester
{
    private List<List<Integer>> paths;
    private Integer target;

    public void printPaths(Node head, Integer sum)
    {
        paths = new ArrayList<>();
        target = sum;
        computePaths(head, new ArrayList<Integer>(), 0);

        for (List<Integer> list : paths)
        {
            for (Integer i : list)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private void computePaths(Node node, List<Integer> path, Integer sumSoFar)
    {
        if (isLeaf(node))
        {
            sumSoFar += node.getValue();
            if (sumSoFar.intValue() == target.intValue())
            {
                path.add(node.getValue());
                paths.add(path);
            }

            return;
        }

        List<Integer> thisPath = new ArrayList<>(path);
        thisPath.add(node.getValue());
        sumSoFar += node.getValue();

        computePaths(node.getLeft(), thisPath, sumSoFar);
        computePaths(node.getRight(), thisPath, sumSoFar);
    }

    private boolean isLeaf(Node node)
    {
        return node.getRight() == null && node.getLeft() == null;
    }

}

class BT
{
    public Node make(Integer[] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parent = nodes[getParentIndex(i)];
            if (i%2 == 0)
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
            else
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
        }

        return nodes[0];
    }

    private int getParentIndex(Integer childINdex)
    {
        return (childINdex-1)/2;
    }

    public void print(Node node)
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

    public Node(Integer v)
    {
        value = v;
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
}
