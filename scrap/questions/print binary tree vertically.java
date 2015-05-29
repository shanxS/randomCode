// print binary tree vertically
// f2f r1, q1, set32
// code question 16

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{30, 20, 40, 10, 25, 35, 15};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        BST.print(tree.getHead());

        VerticalPrinter vp = new VerticalPrinter();
        vp.printVertical(tree.getHead());
    }
}

class VerticalPrinter
{
    public void printVertical (TreeNode treeHead)
    {
        DLLNode dllHead = new DLLNode(treeHead.getValue());
        computeVerticals(treeHead, dllHead);
        printVerticals(dllHead);
    }

    private void printVerticals(DLLNode dllHead)
    {
        DLLNode cursor = dllHead;
        while(cursor.getLeft() != null)
        {
            cursor = cursor.getLeft();
        }

        while(cursor != null)
        {
            for (Integer value : cursor.getValues())
            {
                System.out.print(value + " ");
            }
            System.out.println();

            cursor = cursor.getRight();
        }
    }

    private void computeVerticals(TreeNode treeNode, DLLNode dllNode)
    {
        if (treeNode.getLeft() != null)
        {
            Integer treeValue = treeNode.getLeft().getValue();
            if (dllNode.getLeft() == null)
            {
                DLLNode leftDllNode = new DLLNode(treeValue);
                leftDllNode.setRight(dllNode);
                dllNode.setLeft(leftDllNode);
            }
            else
            {
                dllNode.getLeft().putValue(treeValue);
            }

            computeVerticals(treeNode.getLeft(), dllNode.getLeft());
        }

        if (treeNode.getRight() != null)
        {
            Integer treeValue = treeNode.getRight().getValue();
            if (dllNode.getRight() == null)
            {
                DLLNode rightDllNode = new DLLNode(treeValue);
                rightDllNode.setLeft(dllNode);
                dllNode.setRight(rightDllNode);
            }
            else
            {
                dllNode.getRight().putValue(treeValue);
            }

            computeVerticals(treeNode.getRight(), dllNode.getRight());
        }
    }
}

class BST
{
    private TreeNode head;

    public BST()
    {
        this.head = null;
    }

    public static void print(TreeNode node)
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

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new TreeNode(value);
        }
        else
        {
            insert(head, value);
        }
    }

    private void insert(TreeNode node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new TreeNode(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new TreeNode(value));
            }
        }
    }

    public TreeNode getHead()
    {
        return head;
    }
}

class DLLNode
{
    private List<Integer> values;
    private DLLNode left, right;

    public DLLNode(Integer value)
    {
        this.values = new ArrayList<>();
        values.add(value);
    }

    public DLLNode getLeft()
    {
        return left;
    }

    public void setLeft(DLLNode left)
    {
        this.left = left;
    }

    public DLLNode getRight()
    {
        return right;
    }

    public void setRight(DLLNode right)
    {
        this.right = right;
    }

    public void putValue(Integer value)
    {
        values.add(value);
    }

    public List<Integer> getValues()
    {
        return values;
    }
}

class TreeNode
{
    private Integer value;
    private TreeNode left, right;

    public TreeNode(Integer value)
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

    public TreeNode getLeft()
    {
        return left;
    }

    public void setLeft(TreeNode left)
    {
        this.left = left;
    }

    public TreeNode getRight()
    {
        return right;
    }

    public void setRight(TreeNode right)
    {
        this.right = right;
    }
}