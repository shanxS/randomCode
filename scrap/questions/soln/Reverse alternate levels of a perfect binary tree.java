// Reverse alternate levels of a perfect binary tree
// code question: 95


import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("---------------------------");

        AlternateLevelReverser alr = new AlternateLevelReverser();
        alr.reverse(bt.getHead());
        BinaryTree.print(bt.getHead());
    }
}

class AlternateLevelReverser
{
    public void reverse(Node head)
    {
        List<Node> list = new ArrayList<>();
        list.add(head);

        Integer level = 1;
        Integer cursor = 0;
        Integer size = list.size();

        while(cursor < size)
        {
            for (Integer i=cursor; i<size; ++i)
            {
                Node node = list.get(i);

                if (node.getLeft() != null)
                {
                    list.add(node.getLeft());
                }
                if (node.getRight() != null)
                {
                    list.add(node.getRight());
                }
            }

            if (level % 2 != 0)
            {
                Integer parentCursor = cursor;
                Integer childCursor = list.size() - 1;
                Node evenChildHolder = new Node(-2);

                while(parentCursor < size)
                {
                    if (childCursor%2 == 0)
                    {
                        list.get(parentCursor).setLeft(list.get(childCursor));

                        evenChildHolder.setLeft(list.get(childCursor).getLeft());
                        evenChildHolder.setRight(list.get(childCursor).getRight());

                    }
                    else
                    {
                        list.get(parentCursor).setRight(list.get(childCursor));

                        list.get(childCursor+1).setLeft(list.get(childCursor).getLeft());
                        list.get(childCursor+1).setRight(list.get(childCursor).getRight());

                        list.get(childCursor).setLeft(evenChildHolder.getLeft());
                        list.get(childCursor).setRight(evenChildHolder.getRight());

                        ++parentCursor;
                    }
                    --childCursor;
                }
            }

            cursor = size;
            size = list.size();
            ++level;
        }

    }
}

class BinaryTree
{
    private Integer arrayLength;
    private Node head;

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

            Integer rightChlidIndex = getRightChildIndex(arrayCursor);
            if (isValidIndex(rightChlidIndex) && isValidValue(array[rightChlidIndex]))
            {
                Node rightNode = new Node(array[rightChlidIndex]);
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

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLength);
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index * 2) + 1;
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

    public Node(Integer value)
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