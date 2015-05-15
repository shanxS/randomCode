// BST tester
// 1 f2f q1

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{30,20,40,10,25,35,50,45,60};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }

        BST.print(tree.getHead());

//        tree.swap(25, 45);
//        System.out.println("----------------");
//        BST.print(tree.getHead());

        BSTTester bstTester = new BSTTester(tree);
        System.out.print(bstTester.test());
    }
}

class BSTTester
{
    private Node head;
    private Boolean rule;

    public BSTTester(BST tree)
    {
        this.head = tree.getHead();
        this.rule = tree.getRule();
    }

    public Boolean test()
    {
        return test(head, null, null);
    }

    private Boolean test(Node node, Node leftParent, Node rightParent)
    {
        if (node == null)
        {
            return true;
        }

        if (leftParent != null)
        {
            if ((leftParent.getValue() > node.getValue()) != rule)
            {
                return false;
            }
        }

        if (rightParent != null)
        {
            if ((rightParent.getValue() > node.getValue()) == rule)
            {
                return false;
            }
        }

        return test(node.getLeft(), node, rightParent)
                && test(node.getRight(), leftParent, node);
    }

}

class BST
{
    private Node head;
    final private Boolean ruleLeftLessThan;

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

    public void swap(Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        Node node2 = findNode(head, value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant find nodes");
        }

        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
    }

    private Node findNode(Node node, Integer target)
    {
        if (node == null)
        {
            return node;
        }

        if (node.getValue() == target)
        {
            return node;
        }

        if ((node.getValue() > target) == ruleLeftLessThan)
        {
            return findNode(node.getLeft(), target);
        }
        else if ((node.getValue() > target) != ruleLeftLessThan)
        {
            return findNode(node.getRight(), target);
        }

        return null;
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