// get kth largest number in BST
// code question: 68

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{20, 30, 25, 40, 35, 50, 41};
        Integer[] array = new Integer[]{20,8,22,4,12,10,14};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());
        KthBiggest kbn = new KthBiggest();
        //System.out.print(kbn.kthBigggest(bst.getHead(), 1));
        //System.out.print(kbn.kthBigggest(bst.getHead(), 3));
        //System.out.print(kbn.kthBigggest(bst.getHead(), 2));

//        System.out.print(kbn.kthBigggest(bst.getHead(), 3));
        System.out.print(kbn.kthBigggest(bst.getHead(), 5));
    }

}

class KthBiggest
{
    private Node kthNode;

    public Integer kthBigggest(Node head, Integer k)
    {
        kthNode = null;
        reverseInorder(head, k);

        return kthNode.getValue();
    }

    private Integer reverseInorder(Node node, Integer k)
    {
        if (node == null)
        {
            return null;
        }

        Integer kRight = reverseInorder(node.getRight(), k);
        if (kRight != null && kRight == 0
            || (kRight == null && k ==0))
        {
            kthNode = node;
        }

        Integer kLeft;
        if (kRight != null)
        {
            kLeft = reverseInorder(node.getLeft(), kRight - 1);
        }
        else
        {
            kLeft = reverseInorder(node.getLeft(), k-1);
        }

        return (kLeft == null) ? k-1 : kLeft;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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