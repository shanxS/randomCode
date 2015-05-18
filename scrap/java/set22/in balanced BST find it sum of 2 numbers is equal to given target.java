// in balanced BST find it sum of 2 numbers is equal to given target
// r1, q1, set22

// BST has to BALANCED

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{81,50,200,30,70,150,250,20,40,60,80,130,160,230,260};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        tree.printReverseInOrder();
        //System.out.println(tree.findSum(250));
        System.out.println(tree.findSum(162));
    }
}

class BST
{
    private Node head;
    private Integer target;
    private Stack<Node> leftStack, rightStack;

    public BST()
    {
        this.head = null;
        this.target = null;
        this.leftStack = new Stack<>();
        this.rightStack = new Stack<>();
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    public Boolean findSum(Integer target)
    {
        this.target = target;
        populateStack(head, head);

        return test();
    }

    private Boolean test()
    {
        while (leftStack.size() > 0 && rightStack.size() > 0)
        {
            Integer thisValue = leftStack.peek().getValue() + rightStack.peek().getValue();
            if (thisValue.intValue() == target.intValue())
            {
                if (leftStack.size() == 1 && rightStack.size() == 1)
                {
                    // dont count head twice
                    return false;
                }

                return true;
            }
            if (thisValue.intValue() < target.intValue())
            {
                removeMin();
            }
            if (thisValue.intValue() > target.intValue())
            {
                removeMax();
            }
        }

        return false;
    }

    private void removeMax()
    {
        Node cursor = rightStack.pop();
        if (cursor.getLeft() != null)
        {
            addMax(cursor.getLeft());
        }
    }

    private void addMax(Node left)
    {
        rightStack.push(left);
        if (left.getRight() != null)
        {
            addMax(left.getRight());
        }
    }

    private void removeMin()
    {
        Node cursor = leftStack.pop();
        if (cursor.getRight() != null)
        {
            addMin(cursor.getRight());
        }
    }

    private void addMin(Node right)
    {
        leftStack.push(right);
        if (right.getLeft() != null)
        {
            addMin(right.getLeft());
        }
    }

    private void populateStack(Node left, Node right)
    {
        if (left == null || right == null)
        {
            return;
        }

        leftStack.push(left);
        rightStack.push(right);

        populateStack(left.getLeft(), right.getRight());
    }


    public void printReverseInOrder()
    {
        printReverseInOrder(head);
    }

    private void printReverseInOrder(Node node)
    {
        if (node == null)
        {
            return;
        }

        printReverseInOrder(node.getRight());
        System.out.println(node.getValue());
        printReverseInOrder(node.getLeft());
    }


    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
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
                node.setRight(new Node(value));
            }
        }
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