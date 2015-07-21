// make min height bst from sorted array

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {1,2,3,4};
//        Integer[] ar = {1,2,3,4,5,6,7,8};
//        Integer[] ar = {1,2,3,4,5,6,7};
        Integer[] ar = {1,2,3,4,5,6,7};
        BSTCreater bstCreater = new BSTCreater();
        bstCreater.print(bstCreater.create(ar));
    }
}

class BSTCreater
{
    private Integer[] ar;

    public Node create(Integer[] ar)
    {
        this.ar = ar;
        return makeFor(0, ar.length - 1);
    }

    private Node makeFor(Integer start, Integer end)
    {
        if (start < 0 || start > end)
        {
            return null;
        }

        Integer mid = (end + start) / 2;
        Node node = new Node(ar[mid]);
        node.setLeft(makeFor(start, mid - 1));
        node.setRight(makeFor(mid + 1, end));

        return node;
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

class Node
{
    private Node left, right;
    final private Integer value;

    public Node (Integer v)
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