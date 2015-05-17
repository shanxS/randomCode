// make binary tree form given inorder and preorder traversal
// r4, q1, set21

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] inorder = new Integer[]{6,4,2,5,1,7,3,9,8,10,11};
        Integer[] preorder = new Integer[]{1,2,4,6,5,3,7,8,9,10,11};

        BinaryTree bt = new BinaryTree();
        Node head = bt.create(inorder, preorder);
        BinaryTree.print(head);
    }
}

class BinaryTree
{
    Integer[] inorder, preorder;
    Integer preOrderIndex;

    public Node create(Integer[] inorder, Integer[] preorder)
    {
        this.inorder = inorder;
        this.preorder = preorder;
        this.preOrderIndex = 0;

        Integer thisValue = preorder[preOrderIndex];
        ++preOrderIndex;
        Node head = new Node(thisValue);

        Integer thisNodeIndex = 0;
        while(thisNodeIndex < inorder.length)
        {
            if (thisValue == inorder[thisNodeIndex])
            {
                break;
            }
            ++thisNodeIndex;
        }

        head.setLeft(getTree(0, thisNodeIndex));
        head.setRight(getTree(thisNodeIndex+1, inorder.length));

        return head;
    }

    private Node getTree(Integer startIn, Integer inLength)
    {
        if (startIn >= inLength || preOrderIndex >= preorder.length)
        {
            return null;
        }

        Integer thisValue = preorder[preOrderIndex];
        ++preOrderIndex;
        Node node = new Node(thisValue);

        Integer thisNodeIndex = 0;
        while(thisNodeIndex < inorder.length)
        {
            if (thisValue == inorder[thisNodeIndex])
            {
                break;
            }
            ++thisNodeIndex;
        }

        node.setLeft(getTree(startIn, thisNodeIndex));
        node.setRight(getTree(thisNodeIndex+1, inLength));

        return node;
    }

    public static void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " ");
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