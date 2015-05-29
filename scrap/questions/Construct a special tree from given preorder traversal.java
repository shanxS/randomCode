// Construct a special tree from given preorder traversal
// www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
// set 32, written, q2
// code question 8

public class Main
{
    public static void main(String[] er)
    {
        Integer[] pre = new Integer[]{10, 30, 20, 5, 15};
        Character preLN[] = new Character[]{'N', 'N', 'L', 'L', 'L'};

        BinaryTree bt = new BinaryTree();
        BinaryTree.print(bt.create(pre, preLN));
    }
}

class BinaryTree
{
    private Node head;
    private Integer[] pre;
    private Character[] preLN;

    public Node create(Integer[] pre, Character[] preLN)
    {
        this.head = new Node(pre[0]);
        this.pre = pre;
        this.preLN = preLN;
        addNode(head, 0);
        addNode(head, 1);

        return head;
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

    private void addNode(Node previousNode, Integer previousIndex)
    {
        Integer thisIndex = previousIndex + 1;

        if (thisIndex >= pre.length)
        {
            return;
        }

        if (preLN[thisIndex] == 'D')
        {
            addNode(previousNode, previousIndex+1);
        }
        else
        {

            Node thisNode = new Node(pre[thisIndex]);
            if (previousNode.getLeft() == null)
            {
                previousNode.setLeft(thisNode);
            } else
            {
                previousNode.setRight(thisNode);
            }

            if (preLN[thisIndex] == 'N')
            {
                addNode(thisNode, previousIndex + 1);
                addNode(thisNode, previousIndex + 2);
            }

            preLN[thisIndex] = 'D';
        }
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
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