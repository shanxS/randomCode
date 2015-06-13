// Print all nodes at horizontal distance k from a given node
// code question: 97

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {20, 8, 22, 4, 12,
                null, null, null, null,
                10, 14};

        BinaryTree bt = new BinaryTree(array);
        HorizontalDistanceAggregator ha = new HorizontalDistanceAggregator();
        ha.printNodesAtDistance(bt.getHead(), 1);
    }
}

class HorizontalDistanceAggregator
{
    LLNode llHead;

    public void printNodesAtDistance(Node head, Integer k)
    {
        llHead = new LLNode(head.getValue());

        setupLL(head, llHead);

        LLNode cursor = llHead;
        Integer count = k;
        while(count>0 && cursor != null)
        {
            cursor = cursor.getRight();
            --count;
        }
        if (count == 0 && cursor != null)
        {
            cursor.getValues().stream().forEach(x -> System.out.print(x + " "));
        }

        cursor = llHead;
        count = k;
        while(count>0 && cursor != null)
        {
            cursor = cursor.getLeft();
            --count;
        }
        if (count == 0 && cursor != null)
        {
            cursor.getValues().stream().forEach(x -> System.out.print(x + " "));
        }
    }

    private void setupLL(Node node, LLNode llNode)
    {
        if (node.getLeft() != null)
        {
            if (llNode.getLeft() == null)
            {
                LLNode leftLLNode = new LLNode(node.getLeft().getValue());
                llNode.setLeft(leftLLNode);
                leftLLNode.setRight(llNode);
            }
            else
            {
                llNode.getLeft().insertValue(node.getLeft().getValue());
            }

            setupLL(node.getLeft(), llNode.getLeft());
        }

        if (node.getRight() != null)
        {
            if (llNode.getRight() == null)
            {
                LLNode rightLLNode = new LLNode(node.getRight().getValue());
                llNode.setRight(rightLLNode);
                rightLLNode.setLeft(llNode);
            }
            else
            {
                llNode.getRight().insertValue(node.getRight().getValue());
            }

            setupLL(node.getRight(), llNode.getRight());
        }
    }

    private class LLNode
    {
        private LLNode left, right;
        private List<Integer> values;

        public LLNode(Integer value)
        {
            this.values = new ArrayList<>();
            values.add(value);
        }

        public void insertValue(Integer value)
        {
            values.add(value);
        }

        public List<Integer> getValues()
        {
            return values;
        }

        public LLNode getLeft()
        {
            return left;
        }

        public void setLeft(LLNode left)
        {
            this.left = left;
        }

        public LLNode getRight()
        {
            return right;
        }

        public void setRight(LLNode right)
        {
            this.right = right;
        }
    }
}

class BinaryTree
{
    private Node head;
    private Integer arrayLength;

    public BinaryTree(Integer[] array)
    {
        this.arrayLength = array.length;
        this.head = new Node(array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for (Integer arrayCursor = 0; arrayCursor<arrayLength; ++arrayCursor)
        {
            if (!isValidValue(array[arrayCursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getLeftChildIndex(arrayCursor);
            if (isValidIndex(leftChildIndex) && isValidValue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getRigthChildIndex(arrayCursor);
            if (isValidIndex(rightChildIndex) && isValidValue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
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

    private Integer getRigthChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
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
    private Node left, right;
    private Integer value;

    public Node (Integer value)
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}