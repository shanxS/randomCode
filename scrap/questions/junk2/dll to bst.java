// dll to bst

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4};
        LLUtil llUtil = new LLUtil();
        Node ll = llUtil.make(ar);

        BTUtil btUtil = new BTUtil();
        Node head = btUtil.convert(ll);
        btUtil.print(head);
    }
}

class BTUtil
{
    public Node convert(Node node)
    {
        if (node == null)
        {
            return null;
        }

        Node leader = node;
        Node midNode = leader;
        while (leader.getRight() != null && leader.getRight().getRight() != null)
        {
            leader = leader.getRight().getRight();
            midNode = midNode.getRight();
        }

        Node leftSub = midNode.getLeft();
        if (leftSub != null)
        {
            leftSub.setRight(null);
            midNode.setLeft(convert(node));
        }

        Node rightSub = midNode.getRight();
        if (rightSub != null)
        {
            rightSub.setLeft(null);
            midNode.setRight(convert(rightSub));
        }

        return midNode;
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

class LLUtil
{
    public Node make(Integer[] ar)
    {
        Node head = new Node(ar[0]);
        Node end = head;

        for (Integer i=1; i<ar.length; ++i)
        {
            end.setRight(new Node(ar[i]));
            end.getRight().setLeft(end);

            end = end.getRight();
        }

        return head;
    }

    public void print(Node head)
    {
        Node cursor = head;
        while (cursor.getRight() != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println(cursor.getValue());

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getLeft();
        }
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