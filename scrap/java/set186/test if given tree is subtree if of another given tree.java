// test if given tree is subtree if of another given tree
// set186, r3, q1
// code question 43

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5,
                null,
                6,
                null,
                7};
        BinaryTree tree1 = new BinaryTree(array);

        Integer[] subArray = new Integer[]{2,  5, null, 7};
        BinaryTree tree2 = new BinaryTree(subArray);

        TestSubTree tst = new TestSubTree();
        System.out.print(tst.isSubTree(tree1.getHead(), tree2.getHead()));
    }
}

class TestSubTree
{
    public Boolean isSubTree(Node head1, Node head2)
    {
        List<Integer> inOrder1 = new ArrayList<>();
        setInorder(head1, inOrder1);
        List<Integer> preOrder1 = new ArrayList<>();
        setPreOrder(head1, preOrder1);

        List<Integer> inOrder2 = new ArrayList<>();
        setInorder(head2, inOrder2);
        List<Integer> preOrder2 = new ArrayList<>();
        setPreOrder(head2, preOrder2);

        if (preOrder1.size() > preOrder2.size())
        {
            if (isSubSet(preOrder1, preOrder2)
                    && isSubSet(inOrder1, inOrder2))
            {
                return true;
            }

            return false;
        }
        else
        {
            if (isSubSet(preOrder2, preOrder1)
                    && isSubSet(inOrder2, inOrder1))
            {
                return true;
            }

            return false;
        }
    }

    private boolean isSubSet(List<Integer> bigList, List<Integer> smallList)
    {
        Integer bigCursor = 0;
        Integer smallCursor = 0;

        while(bigCursor < bigList.size() && smallCursor < smallList.size())
        {
            if (bigList.get(bigCursor) == smallList.get(smallCursor))
            {
                ++smallCursor;
            }
            else
            {
                smallCursor = 0;
            }

            ++bigCursor;
        }

        if (smallCursor == smallList.size())
        {
            return true;
        }

        return false;
    }

    private void setPreOrder(Node node, List<Integer> list)
    {
        if (node == null)
        {
            return;
        }

        list.add(node.getValue());
        setPreOrder(node.getLeft(), list);
        setPreOrder(node.getRight(), list);
    }

    private void setInorder(Node node, List<Integer> list)
    {
        if (node == null)
        {
            return;
        }

        setInorder(node.getLeft(), list);
        list.add(node.getValue());
        setInorder(node.getRight(), list);
    }
}

class BinaryTree
{
    private Node head;
    final private Integer arrayLenght;

    public BinaryTree(Integer[] array)
    {
        this.arrayLenght = array.length;
        head = new Node (array[0]);

        List<Node> list = new ArrayList<>();
        list.add(head);

        for(Integer cursor = 0; cursor < arrayLenght; ++ cursor)
        {
            if (!isValidVaue(array[cursor]))
            {
                continue;
            }

            Node node = list.get(0);

            Integer leftChildIndex = getChildLeftIndex(cursor);
            if (isValidIndex(leftChildIndex) && isValidVaue(array[leftChildIndex]))
            {
                Node leftNode = new Node(array[leftChildIndex]);
                node.setLeft(leftNode);
                list.add(leftNode);
            }

            Integer rightChildIndex = getChildRightIndex(cursor);
            if (isValidIndex(rightChildIndex) && isValidVaue(array[rightChildIndex]))
            {
                Node rightNode = new Node(array[rightChildIndex]);
                node.setRight(rightNode);
                list.add(rightNode);
            }

            list.remove(node);
        }
    }

    private Integer getChildRightIndex(Integer index)
    {
        return getChildLeftIndex(index) + 1;
    }

    private Integer getChildLeftIndex(Integer index)
    {
        return (index*2) + 1;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < arrayLenght);
    }

    private boolean isValidVaue(Integer value)
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