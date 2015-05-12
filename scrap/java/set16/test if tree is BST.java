// test if tree is BST
// r1,q1,set17

public class Main
{
    public static void main(String[] args)
    {
        System.out.print(case0());
        //System.out.print(case1());
        //System.out.print(case2());
        //System.out.print(case3());
        //System.out.print(case4());
    }

    private static Boolean case0()
    {
        System.out.println("CASE0 ======================");

        Integer[] array = new Integer[]{30,20,50,10,25,35,55,45,51};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());
        BSTTester tester = new BSTTester(bst);

        return tester.isBST();
    }

    private static Boolean case4()
    {
        System.out.println("CASE4 ======================");

        Integer[] array = new Integer[]{30,20,50,10,25,35,55,45,51};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        bst.swap(25, 51);
        BST.print(bst.getHead());
        BSTTester tester = new BSTTester(bst);

        return tester.isBST();
    }

    private static Boolean case3()
    {
        System.out.println("CASE3 ======================");

        Integer[] array = new Integer[]{30,50,35,55,45,51};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        bst.swap(30,50);
        BST.print(bst.getHead());
        BSTTester tester = new BSTTester(bst);

        return tester.isBST();
    }

    private static Boolean case2()
    {
        System.out.println("CASE2 ======================");

        Integer[] array = new Integer[]{30,20,50,10,25,35,55,45,51};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        bst.swap(30,50);
        BST.print(bst.getHead());
        BSTTester tester = new BSTTester(bst);

        return tester.isBST();
    }

    private static Boolean case1()
    {
        System.out.println("CASE1 ======================");

        Integer[] array = new Integer[]{30,20,50,10,25,35,55,45,51};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        bst.swap(45,51);
        BST.print(bst.getHead());
        BSTTester tester = new BSTTester(bst);

        return tester.isBST();
    }
}

class BSTTester
{
    private Node head;
    private Boolean rule;

    public BSTTester(BST tree)
    {
        this.head = tree.getHead();
        this.rule = setRule();
    }

    private Boolean setRule()
    {
        if (head == null)
        {
            return null;
        }

        if (head.getLeft() != null)
        {
            return (head.getValue() > head.getLeft().getValue());
        }
        else if (head.getRight() != null)
        {
            return (head.getValue() < head.getRight().getValue());
        }

        return null;
    }

    public Boolean isBST()
    {
        if (rule == null)
        {
            return  false;
        }

        return test(head, null, null);
    }

    private Boolean test(Node node, Integer less, Integer big)
    {
        if (node == null)
        {
            return true;
        }

        if (less != null)
        {
            if (node.getValue() > less)
            {
                return false;
            }
        }
        if (big != null)
        {
            if (node.getValue() < big)
            {
                return false;
            }
        }

        return test(node.getLeft(), node.getValue(), big)
                && test(node.getRight(), less, node.getValue());
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

    public void swap(Integer value1, Integer value2)
    {
        Node node1 = findNode(head, value1);
        Node node2 = findNode(head, value2);

        if (node1 == null || node2 == null)
        {
            System.out.print("cant find node");
        }

        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
    }

    private Node findNode(Node node, Integer value)
    {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }
        else if ((node.getValue() > value) == ruleLeftLessThan)
        {
            return findNode(node.getLeft(), value);
        }
        else if ((node.getValue() > value) != ruleLeftLessThan)
        {
            return findNode(node.getRight(), value);
        }

        return null;
    }

    public static void print(Node node)
    {
        if(node == null)
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

    public Node getHead()
    {
        return head;
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