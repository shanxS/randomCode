// BST tester
// telephonic, q2, set 179

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{20, 10, 30, 5, 15,
//                null,
//                35,
//                null, null,
//                11};

//        Integer[] array = new Integer[]{20, 10, 30, 5, 35,
//                null,
//                15,
//                null, null,
//                11};

//        Integer[] array = new Integer[]{20, 5, 30, 10, 15,
//                null,
//                35,
//                null, null,
//                11};

        Integer[] array = new Integer[]{20, 10, 30, 11, 15,
                null,
                35,
                null, null,
                5};

        BinaryTree bt = new BinaryTree(array);

        BSTTester bstTester = new BSTTester();
        System.out.print(bstTester.test(bt.getHead()));
    }
}

class BSTTester
{
    public Boolean test(Node head)
    {
        return test(head, null, null);
    }

    private Boolean test(Node node, Integer lessThan, Integer greaterThan)
    {
        if (node == null)
        {
            return true;
        }

        if (!test(node.getLeft(), node.getValue(), greaterThan)
                || (lessThan != null && node.getValue() > lessThan))
        {
            return false;
        }

        return test(node.getRight(), lessThan, node.getValue());
    }
}

class BinaryTree
{
    final private Integer arrayLength;
    private Node head;

    public BinaryTree(Integer[] array)
    {
        this.arrayLength = array.length;
        this.head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor < arrayLength; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildiIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildiIndex) && isValidValue(array[leftChildiIndex]))
            {
                Node leftNode = new Node(array[leftChildiIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private boolean isValidValue(Integer value)
    {
        return (value != null);
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

    public Node (Integer value)
    {
        this.value = value;
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