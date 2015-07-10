import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] er)
    {
        try
        {
            Integer N = Integer.parseInt(bufferedReader.readLine());

            String[] input = bufferedReader.readLine().split(" ");

            AVL tree= new AVL(N);
            for (Integer i=0; i<input.length; ++i)
            {
                tree.insert(Integer.parseInt(input[i]), i);
            }

            for (Integer r : tree.getRanking())
            {
                System.out.print(r + " ");
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

//        Integer[] marks = {3,3,4,1,1,5,10,20,20};
//        AVL tree= new AVL(marks.length);
//        for (Integer i=0; i<marks.length; ++i)
//        {
//            tree.insert(marks[i], i);
//        }

//        int[] rank = tree.getRanking();
//        for (Integer i=0; i<marks.length; ++i)
//        {
//            System.out.println(i + " marks: " + marks[i] + " rank:" + rank[i]);
//        }
    }
}


class AVL
{
    private Node head;
    private int[] ranking;
    private Integer nodeCount;

    public AVL(Integer rollCount)
    {
        head = null;
        ranking = new int[rollCount];
        nodeCount = 0;
    }


    public int[] getRanking()
    {
        inroder(head, nodeCount);
        return ranking;
    }

    private Integer inroder(Node node, Integer rank)
    {

        Integer thisRank = rank;
        if (node.getLeft() != null)
        {
            thisRank = inroder(node.getLeft(), thisRank);
        }

        for (Integer roll : node.getRoll())
        {
            ranking[roll] = thisRank;
        }

        Integer returnRank = thisRank-1;
        if (node.getRight() != null)
        {
            returnRank = inroder(node.getRight(), returnRank);
        }

        return returnRank;
    }

    public void insert(Integer value, Integer roll)
    {
        if (head == null)
        {
            head = new Node(value, roll);
            ++nodeCount;
        }
        else
        {
            insert(head, value, roll, null);
        }
    }

    private void insert(Node node, Integer value, Integer roll, Node parent)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value, roll, node);
            }
            else
            {
                node.setLeft(new Node(value, roll));
                ++nodeCount;
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value, roll, node);
            }
            else
            {
                node.setRight(new Node(value, roll));
                ++nodeCount;
            }
        }
        else if (node.getValue() == value)
        {
            node.addRoll(roll);
            return;
        }

        if (unbalanced(node))
        {
            balance(node, parent);
        }
    }

    private void balance(Node z, Node parent)
    {
        Node newZ;
        if (z.getRightLevel() > z.getLeftLevel())
        {
            Node y = z.getRight();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                newZ = rotateLeft(z);
            }
            else
            {
                z.setRight(rotateRight(y));
                newZ = rotateLeft(z);
            }
        }
        else
        {
            Node y = z.getLeft();
            if (y.getRightLevel() > y.getLeftLevel())
            {
                z.setLeft(rotateLeft(y));
                newZ = rotateRight(z);
            }
            else
            {
                newZ = rotateRight(z);
            }
        }

        if (parent == null)
        {
            head = newZ;
        }
        else
        {
            if (parent.getValue() > newZ.getValue())
            {
                parent.setLeft(newZ);
            }
            else
            {
                parent.setRight(newZ);
            }
        }
    }

    public static void printPreOrder(Node node)
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

        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    private Node rotateLeft(Node parent)
    {
        Node child  = parent.getRight();
        Node childLeftChild = child.getLeft();

        parent.setRight(childLeftChild);
        child.setLeft(parent);

        return child;
    }


    private Node rotateRight(Node parent)
    {
        Node child = parent.getLeft();
        Node childRightChild = child.getRight();

        parent.setLeft(childRightChild);
        child.setRight(parent);

        return child;
    }

    private boolean unbalanced(Node node)
    {
        return Math.abs(node.getLeftLevel() - node.getRightLevel()) > 1;
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private List<Integer> roll;
    private Integer value, leftLevel, rightLevel;

    public Node (Integer value, Integer r)
    {
        this.value = value;
        this.leftLevel = 0;
        this.rightLevel = 0;
        this.roll = new ArrayList<>();
        roll.add(r);
    }

    public void addRoll(Integer r)
    {
        roll.add(r);
    }

    public List<Integer> getRoll()
    {
        return roll;
    }

    public Integer getValue()
    {
        return value;
    }

    public Integer getLeftLevel()
    {
        updateLevel();
        return leftLevel;
    }

    public Integer getRightLevel()
    {
        updateLevel();
        return rightLevel;
    }

    public Integer getLevel()
    {
        updateLevel();
        return 1 + Math.max(leftLevel, rightLevel);
    }

    private void updateLevel()
    {
        if (left == null)
        {
            leftLevel = 0;
        }
        else
        {
            leftLevel = left.getLevel();
        }

        if (right == null)
        {
            rightLevel = 0;
        }
        else
        {
            rightLevel = right.getLevel();
        }
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