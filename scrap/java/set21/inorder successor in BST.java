// inorder successor in BST
// r4, q2, set21

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{10,1,20,2,30,3,40,4,50};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());
        InorderSuccesorFinder isf = new InorderSuccesorFinder(tree);
        isf.printInorder();
        System.out.println(isf.findSuccessor(4));
        System.out.println(isf.findSuccessor(30));
        System.out.println(isf.findSuccessor(50));
    }
}

class InorderSuccesorFinder
{
    private Node head;
    private Boolean ruleLeftLessThan;
    private Node lastBiggest;

    public InorderSuccesorFinder(BST tree)
    {
        this.head = tree.getHead();
        this.ruleLeftLessThan = tree.getRule();
        this.lastBiggest = null;
    }


    public Integer findSuccessor(Integer target)
    {
        if ((head.getValue() > target) == ruleLeftLessThan)
        {
            lastBiggest = head;
            return find(head.getLeft(), target);
        }
        else if ((head.getValue() > target) != ruleLeftLessThan)
        {
            lastBiggest = null;
            return find(head.getRight(), target);
        }


        return null;
    }

    private Integer find(Node node, Integer target)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == target)
        {
            if (node.getRight() != null)
            {
                Node successor = node.getRight();
                while(successor.getLeft() != null)
                {
                    successor = successor.getLeft();
                }

                return successor.getValue();
            }
            else
            {
                if (lastBiggest != null)
                {
                    return lastBiggest.getValue();
                }
                else
                {
                    return null;
                }
            }
        }

        if ((node.getValue() > target) == ruleLeftLessThan)
        {
            lastBiggest = node;
            return find(node.getLeft(), target);
        }
        else if ((node.getValue() > target) != ruleLeftLessThan)
        {
            return find(node.getRight(), target);
        }

        return null;
    }

    public void printInorder()
    {
        printInorder(head);
        System.out.println();
    }

    private void printInorder(Node node)
    {
        if (node == null)
        {
            return;
        }

        printInorder(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInorder(node.getRight());

    }
}

class BST
{
    private Node head;
    private Boolean ruleLeftLessThan;

    public BST()
    {
        this.head = null;
        this.ruleLeftLessThan = true;
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
        if ((node.getValue() > value) == ruleLeftLessThan)
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
        else if ((node.getValue() > value) != ruleLeftLessThan)
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

    public Boolean getRule()
    {
        return ruleLeftLessThan;
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