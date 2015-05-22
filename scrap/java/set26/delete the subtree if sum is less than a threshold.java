// delete the subtree if sum is less than a threshold
// r4, q3, set26 realted

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{1, 2, 3, 4,
//                null, null,
//                5,
//                null,
//                7,
//                null, null, null, null,
//                6
//        };

        Integer[] array = new Integer[]{
                1,2,3,4,5,6,7,8,9,12,
                null, null, null,
                10,
                null, null, null,
                13, 14,
                null, null, null,null, null, null,null, null, null,
                11,
                null, null, null, null, null, null, null, null,
                15
        };

        BinaryTree bt = new BinaryTree(array);
        BinaryTree.print(bt.getHead());
        System.out.println("--------------------");

        SubTreeDeleter std = new SubTreeDeleter();
        std.delete(bt.getHead(), 20);
        BinaryTree.print(bt.getHead());
    }
}

class SubTreeDeleter
{
    public void delete (Node head, final Integer k)
    {
        Integer leftSubTreeSum = getSubTreeSum(head.getLeft(), k) + head.getValue();
        Integer rightSubTreeSum = getSubTreeSum(head.getRight(), k) + head.getValue();

        if (leftSubTreeSum < k)
        {
            head.setLeft(null);
        }
        if (rightSubTreeSum < k)
        {
            head.setRight(null);
        }
    }

    private Integer getSubTreeSum(Node node, Integer k)
    {
        if (node == null)
        {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            return node.getValue();
        }

        Integer leftSubTreeSum = getSubTreeSum(node.getLeft(), k) + node.getValue();
        Integer rightSubTreeSum = getSubTreeSum(node.getRight(), k) + node.getValue();


        if (rightSubTreeSum < k)
        {
            node.setRight(null);
            return leftSubTreeSum;
        }
        else if (leftSubTreeSum < k)
        {
            node.setLeft(null);
            return rightSubTreeSum;
        }

        return leftSubTreeSum + rightSubTreeSum - node.getValue();
    }

}

class BinaryTree
{
    private Node head;
    private Integer[] array;

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

    public BinaryTree(Integer[] array)
    {
        this.array = array;
        this.head = new Node(array[0]);
        generateTree(head, 0);
    }

    private void generateTree(Node parent, Integer parentIndex)
    {
        Integer leftChildIndex = getLeftChildIndex(parentIndex);
        if (isIndexValid(leftChildIndex) && isValidValue(array[leftChildIndex]))
        {
            Node leftNode = new Node(array[leftChildIndex]);
            parent.setLeft(leftNode);
            generateTree(leftNode, leftChildIndex);
        }

        Integer rightChildIndex = getRightChildIndex(parentIndex);
        if (isIndexValid(rightChildIndex) && isValidValue(array[rightChildIndex]))
        {
            Node rightNode = new Node(array[rightChildIndex]);
            parent.setRight(rightNode);
            generateTree(rightNode, rightChildIndex);
        }
    }

    private boolean isIndexValid(Integer index)
    {
        return (index < 0 || index >= array.length) ? false : true;
    }

    private Boolean isValidValue(Integer integer)
    {
        return (integer == null) ? false : true;
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return (2*parentIndex) + 2;
    }

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (2*parentIndex) + 1;
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
        this.left = null;
        this.right = null;
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