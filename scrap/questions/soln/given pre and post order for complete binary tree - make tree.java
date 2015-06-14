// given pre and post order for complete binary tree - make tree
// code question: 106

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] pre = {1,2,4,8,9,5,3,6,7};
//        Integer[] post = {8,9,4,5,2,6,7,3,1};
        Integer[] pre = {1,2,4,8,9,5,10,11,3,6,7};
        Integer[] post = {8,9,4,10,11,5,2,6,7,3,1};

        CompleteBinaryTreeFromPostAndPreOrder cc = new CompleteBinaryTreeFromPostAndPreOrder();
        print(cc.makeTree(pre, post));
    }

    private static void print(Node node)
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

class CompleteBinaryTreeFromPostAndPreOrder
{
    private Integer[] pre, post;

    public Node makeTree(Integer[] pre, Integer[] post)
    {
        this.pre = pre;
        this.post = post;
        Node head = makeNodeFor(0, 0, pre.length-1);

        return head;
    }

    private Node makeNodeFor(Integer valueIndex, Integer childStart, Integer childEnd)
    {
        if (childEnd - childStart == 1)
        {
            Node node = new Node(pre[valueIndex]);
            Node leftNode = new Node(pre[childStart]);
            Node rightNode = new Node(pre[childEnd]);
            node.setLeft(leftNode);
            node.setRight(rightNode);

            return node;
        }

        Node thisNode = new Node(pre[valueIndex]);


        Integer leftChildIndex = valueIndex + 1;
        if (leftChildIndex < pre.length)
        {
            Integer leftChildChildStart = leftChildIndex + 1;

            Integer leftChildIndexInPost = getIndexInPost(pre[valueIndex + 1]);
            Integer leftChildChildCount = leftChildIndexInPost;
            Integer leftChildChildEnd = leftChildIndex + leftChildChildCount;
            Node leftNode = makeNodeFor(leftChildIndex, leftChildChildStart, leftChildChildEnd);

            Integer rightChildIndex = leftChildIndex + leftChildChildCount + 1;
            if (rightChildIndex < pre.length)
            {
                Integer rightChildChildStart = rightChildIndex + 1;

                Integer rightChildIndexInPost = getIndexInPost(pre[rightChildIndex]);
                Integer rightChildChildCount = rightChildIndexInPost - leftChildIndexInPost - 1;
                Integer rightChildChildEnd = rightChildIndex + rightChildChildCount;
                Node rightNode = makeNodeFor(rightChildIndex, rightChildChildStart, rightChildChildEnd);

                thisNode.setLeft(leftNode);
                thisNode.setRight(rightNode);
            }
        }

        return thisNode;
    }

    private Integer getIndexInPost(Integer preValue)
    {
        Integer index = 0;
        while (index < post.length)
        {
            if (post[index] == preValue)
            {
                break;
            }
            ++index;
        }

        return index;
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