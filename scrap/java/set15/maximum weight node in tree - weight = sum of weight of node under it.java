// maximum weight node in tree - weight = sum of weight of node under it
// r2, q1, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1, 0, 1, 2, -1, 3, -4};
        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());

        WeightCalculator wc = new WeightCalculator(bt);
        System.out.print("node " + wc.getHeaviestNodeValue());
    }
}

class WeightCalculator
{
    private Node head;
    private Pair nodeWeight;


    public WeightCalculator(BinaryTree tree)
    {
        this.head = tree.getHead();
        this.nodeWeight = new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public Integer getHeaviestNodeValue()
    {
        computeWeights(head);
        return nodeWeight.getNodeValue();
    }

    private Integer computeWeights(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        Integer leftTreeSum = computeWeights(node.getLeft());
        Integer rightTreeSum = computeWeights(node.getRight());

        Integer thisWeight = leftTreeSum + rightTreeSum + node.getValue();
        if (thisWeight > nodeWeight.getWeight())
        {
            nodeWeight.setWeight(thisWeight);
            nodeWeight.setNodeValue(node.getValue());
        }

        return thisWeight;
    }

    private class Pair
    {
        private Integer nodeValue, weight;

        public Integer getNodeValue()
        {
            return nodeValue;
        }

        public void setNodeValue(Integer nodeValue)
        {
            this.nodeValue = nodeValue;
        }

        public Integer getWeight()
        {
            return weight;
        }

        public void setWeight(Integer weight)
        {
            this.weight = weight;
        }

        public Pair()
        {
        }

        public Pair(Integer nodeValue, Integer weight)
        {
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }
}

class BinaryTree
{
    private Integer[] array;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.head = null;
        this.array = array;

        head = generateRecursively(0, this.array.length);
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

    private Node generateRecursively(int start, int length)
    {
        if (length - start < 1)
        {
            return null;
        }

        Integer mid = (start + length)/2;
        Node node = new Node(array[mid]);

        node.setLeft(generateRecursively(start, mid));
        node.setRight(generateRecursively(mid + 1, length));

        return node;
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