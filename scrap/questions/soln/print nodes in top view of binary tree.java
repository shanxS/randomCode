// print nodes in top view of binary tree
// code question: 91

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {20, 8, 22, 5, 3, 4, 25,
//                null, null,
//                10, 14};

        Integer[] array = {1,2,3,
        null,
        4,
                null,null,
                null,null,null,
        5,
                null,null,null,null,
                null,null,null,null,null,null,null,
        6};

        BinaryTree bt = new BinaryTree(array);

        BinaryTree.print(bt.getHead());
        System.out.println("---------------------------");

        TopView bv = new TopView();
        bv.print(bt.getHead());

    }
}

class TopView
{
    private LLNode llHead;

    public void print(Node head)
    {
        llHead = new LLNode(head.getValue(), 1);
        setupLL(head, llHead, 1);

        while (llHead.getLeft() != null)
        {
            llHead = llHead.getLeft();
        }

        LLNode cursor = llHead;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
    }

    private void setupLL(Node node, LLNode llNode, Integer depth)
    {
        Integer childDepth = depth + 1;

        if (node.getLeft() != null)
        {
            if (llNode.getLeft() == null)
            {
                LLNode leftLLNode = new LLNode(node.getLeft().getValue(), childDepth);
                llNode.setLeft(leftLLNode);
                leftLLNode.setRight(llNode);
            }
            else
            {
                if (llNode.getLeft().getDepth() > childDepth)
                {
                    llNode.getLeft().setDepth(childDepth);
                    llNode.getLeft().setValue(node.getLeft().getValue());
                }
            }

            setupLL(node.getLeft(), llNode.getLeft(), childDepth);
        }

        if (node.getRight() != null)
        {
            if (llNode.getRight() == null)
            {
                LLNode rightLLNode = new LLNode(node.getRight().getValue(), childDepth);
                llNode.setRight(rightLLNode);
                rightLLNode.setLeft(llNode);
            }
            else
            {
                if (llNode.getRight().getDepth() > childDepth)
                {
                    llNode.getRight().setDepth(childDepth);
                    llNode.getRight().setValue(node.getRight().getValue());
                }
            }

            setupLL(node.getRight(), llNode.getRight(), childDepth);
        }
    }

    class LLNode
    {
        private LLNode left, right;
        private Integer value, depth;

        public LLNode(Integer value, Integer depth)
        {
            this.value = value;
            this.depth = depth;
        }

        public Integer getDepth()
        {
            return depth;
        }

        public void setDepth(Integer depth)
        {
            this.depth = depth;
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

        public Integer getValue()
        {
            return value;
        }

        public void setValue(Integer value)
        {
            this.value = value;
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