// given BST and target - check if any pair of numbers sums up to it
// uses recursion - is inefficient and counts head twice
// telephonic1, q1, set28

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{21, 10, 31, 15, 25, 40, 35};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        PairFinder pf = new PairFinder();
        //System.out.print(pf.find(tree.getHead(), 40));
        //System.out.print(pf.find(tree.getHead(), 25));
//        System.out.print(pf.find(tree.getHead(), 60) +
//                " " + pf.getNode1().getValue() +
//                " " + pf.getNode2().getValue());
//        System.out.print(pf.find(tree.getHead(), 75) +
//                " " + pf.getNode1().getValue() +
//                " " + pf.getNode2().getValue());
//        Integer result = pf.find(tree.getHead(), 42);
        Integer result = pf.find(tree.getHead(), 45);
        if (result == pf.EQUAL)
        {
            System.out.print(" " + pf.getNode1().getValue() +
                    " " + pf.getNode2().getValue());
        }
        else
        {
            System.out.print(result);
        }
    }
}

class PairFinder
{
    private Integer target;
    final public Integer EQUAL = 0;
    final public Integer LESS = -1;
    final public Integer MORE = 1;
    private Node node1, node2;

    public Integer find (Node head, Integer target)
    {
        this.target = target;
        this.node1 = null;
        this.node2 = null;
        return findSum(head, head);
    }

    private Integer findSum(Node left, Node right)
    {
        Integer previousSumStatus = null;
        if (left.getLeft() != null || right.getRight() != null)
        {
            previousSumStatus = findSum(getLeftIfAvailable(left), getRightIfAvailable(right));
        }

        System.out.println("evaluating " + left.getValue() + " " + right.getValue());

        final Integer currentSumStatus = getSumStatus(left, right);
        if (previousSumStatus == null)
        {
            previousSumStatus = currentSumStatus;
        }

        if (previousSumStatus == EQUAL)
        {
            if (!((left.getLeft() != null || right.getRight() != null)))
            {
                node1 = left;
                node2 = right;
            }
            return previousSumStatus;
        }
        else
        {

            if (currentSumStatus == EQUAL)
            {
                node1 = left;
                node2 = right;
                return currentSumStatus;
            }
            else if (currentSumStatus == MORE)
            {
                if (right.getLeft() != null)
                {
                    return findSum(left, right.getLeft());
                }
                else
                {
                    return currentSumStatus;
                }
            }
            else if (previousSumStatus == LESS)
            {
                if (left.getRight() != null)
                {
                    return findSum(left.getRight(), right);
                }
                else
                {
                    return currentSumStatus;
                }
            }
        }

        return null;
    }

    public Node getNode1()
    {
        return node1;
    }

    public Node getNode2()
    {
        return node2;
    }

    private Node getRightIfAvailable(Node node)
    {
        if (node.getRight() != null)
        {
            return node.getRight();
        }

        return node;
    }

    private Node getLeftIfAvailable(Node node)
    {
        if (node.getLeft() != null)
        {
            return node.getLeft();
        }

        return node;
    }

    private Integer getSumStatus(Node left, Node right)
    {
        if (target == left.getValue() + right.getValue())
        {
            return EQUAL;
        }
        else if (target > left.getValue() + right.getValue())
        {
            return LESS;
        }
        else if (target < left.getValue() + right.getValue())
        {
            return MORE;
        }

        return null;
    }
}

class BST
{
    private Node head;

    public BST()
    {
        this.head = null;
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
        if(node.getValue() > value)
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