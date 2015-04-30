// 2 nodes of bst are swapped find them with rule detector
// intersting notes in "2 nodes of bst are swapped find them.java"

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(40);
        tree.insert(5);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        tree.insert(25);
        tree.insert(60);

        tree.print();

        NodeSwapper ns = new NodeSwapper(tree);
        ns.swapNode(10, 50);

        System.out.println("----------------");
        Detector d = new Detector(tree);
        d.detectUsingLoop();

    }
}

class Detector
{
    private List<Integer> list;
    private Node head;
    Boolean isPreviousLessThan;


    public Detector(BinaryTree tree)
    {
        this.list = new ArrayList<>();
        this.head = tree.getHead();
        this.isPreviousLessThan = null;
    }

    private void setRule()
    {
        Integer previousLessThanCount = 0;
        Integer previousGreaterThanCount = 0;


        for (Integer current = 1; current < list.size(); ++current)
        {
            Integer prevoius = current-1;

            if (list.get(prevoius) > list.get(current))
            {
                ++previousGreaterThanCount;
            }
            else
            {
                ++previousLessThanCount;
            }
        }

        if (previousGreaterThanCount > previousLessThanCount)
        {
            isPreviousLessThan = false;
        }
        else if (previousGreaterThanCount < previousLessThanCount)
        {
            isPreviousLessThan = true;
        }
        else
        {
            isPreviousLessThan = null;
        }

        System.out.println("previous less then " + isPreviousLessThan);
    }

    public void detectUsingLoop()
    {
        populateList(head);
        list.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();

        setRule();

        if (list.size() < 3)
        {
            System.out.println("nodes less than 3 WTF!");
            return;
        }


        Integer first = null;
        Integer second = null;
        Integer third = null;
        for (Integer current=1; current<list.size(); ++current)
        {
            Integer previous = current-1;

            if ((list.get(previous) > list.get(current)) == isPreviousLessThan)
            {
                if (first == null)
                {
                    first = previous;
                    second = current;
                }
                else
                {
                    third = current;
                }
            }
        }

        if (first != null && third != null)
        {
            System.out.println("swapped nodes " + list.get(first) + " " + list.get(third));
        }
        else if (third == null)
        {
            System.out.println("swapped nodes " + list.get(first) + " " + list.get(second));
        }
        else
        {
            System.out.println("no nodes swapped ");
        }
    }

    private void populateList(Node node) {
        if (node == null)
        {
            return;
        }

        populateList(node.getLeft());
        list.add(node.getValue());
        populateList(node.getRight());
    }
}

class NodeSwapper
{
    private Node head;

    public NodeSwapper(BinaryTree tree) {
        head = tree.getHead();
    }

    public void swapNode(Integer value1, Integer value2)
    {
        Node node1 = fetchNode(head, value1);
        Node node2 = fetchNode(head, value2);

        if (node1 == null || node2 == null)
        {
            System.out.println("cant find node to swap");
            return;
        }

        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
    }

    private Node fetchNode(Node node, Integer value) {
        if (node == null)
        {
            return null;
        }

        if (node.getValue() == value)
        {
            return node;
        }
        else if (node.getLeft() != null && node.getValue() < value)
        {
            return fetchNode(node.getLeft(), value);
        }
        else
        {
            return (fetchNode(node.getRight(), value));
        }
    }

//    public void print()
//    {
//        print(head);
//    }
//
//    private void print(Node node) {
//        if (node == null)
//        {
//            return;
//        }
//
//        System.out.print(node.getValue() + " - ");
//        if (node.getRight() != null)
//        {
//            System.out.print(node.getRight().getValue());
//        }
//        System.out.print(", ");
//        if (node.getLeft() != null)
//        {
//            System.out.print(node.getLeft().getValue());
//        }
//        System.out.println();
//
//        print(node.getLeft());
//        print(node.getRight());
//    }

}

class BinaryTree
{
    private Node head;

    public BinaryTree()
    {
        head = null;
    }

    public void insert (Integer value)
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

    public void print()
    {
        print(head);
    }

    private void print(Node node) {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.print(", ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    private void insert(Node node, Integer value) {
        if (node.getValue() < value)
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
        else
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

    public Node getHead() {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}