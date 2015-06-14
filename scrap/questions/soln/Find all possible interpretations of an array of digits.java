// Find all possible interpretations of an array of digits
// code question: 104

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1,2,1};
//        Integer[] array = {9,1,8};
        Integer[] array = {1,2,1,2};
        List<Integer> numbers = new ArrayList<>();
        for (Integer value : array)
        {
            numbers.add(value);
        }

        Tree t = new Tree(numbers);
        t.printLeaves();
    }
}


class Tree
{
    private Node head;

    public Tree(List<Integer> numbers)
    {
        this.head = new Node("", numbers);

        createSubNode(head);
    }

    public void printLeaves()
    {
        print(head);
    }

    private void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null)
        {
            System.out.println(node.getData() + " ");
            return;
        }

        print(node.getLeft());
        print(node.getRight());
    }

    private void createSubNode(Node node)
    {
        List<Integer> thisNumbers = node.getList();
        if (thisNumbers.size() == 0)
        {
            return;
        }

        String thisData = node.getData();
        Node leftNode = new Node(thisData + (char) (96 + thisNumbers.get(0).intValue()),
                thisNumbers.subList(1, thisNumbers.size()));
        createSubNode(leftNode);

        Node rightNode = null;
        if (thisNumbers.size() >= 2 && thisNumbers.get(0) <= 2 && thisNumbers.get(0) <= 6)
        {
            int intValue = 96 + (10 * thisNumbers.get(0)) + thisNumbers.get(1);
            rightNode = new Node(thisData + (char)intValue,
                    thisNumbers.subList(2, thisNumbers.size()));

            createSubNode(rightNode);
        }

        node.setLeft(leftNode);
        node.setRight(rightNode);
    }
}

class Node
{
    private Node left, right;
    private String data;
    private List<Integer> list;

    public Node(String s, List<Integer> list)
    {
        data = s;
        this.list = new ArrayList<>(list);
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

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public List<Integer> getList()
    {
        return list;
    }

    public void setList(List<Integer> list)
    {
        this.list = list;
    }
}