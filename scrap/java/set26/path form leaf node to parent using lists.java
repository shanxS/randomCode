// path form leaf node to parent using lists
// related to r4, q3, set26

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{50,30,60,20,40,55,25};
        BST tree = new BST();
        for (Integer value : array)
        {
            tree.insert(value);
        }
        PathToLeafUsingLists printer = new PathToLeafUsingLists(tree);
        printer.computeLists();
    }
}

class PathToLeafUsingLists
{
    private Node head;
    private List<List<Integer>> lists;

    public PathToLeafUsingLists(BST tree)
    {
        this.head = tree.getHead();
    }

    public List<List<Integer>> computeLists()
    {
        lists = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        computeForNode(head, list);

        return lists;
    }

    private void computeForNode(Node node, List<Integer> parentList)
    {
        if (node == null)
        {
            return;
        }
        if (isLeafNode(node))
        {
            List<Integer> list = new ArrayList<>(parentList);
            list.add(node.getValue());
            lists.add(list);
            return;
        }

        parentList.add(node.getValue());
        computeForNode(node.getLeft(), parentList);
        computeForNode(node.getRight(), parentList);

        parentList.remove(node.getValue());
    }

    private boolean isLeafNode(Node node)
    {
        if (node.getLeft() == null && node.getRight() == null)
        {
            return true;
        }

        return false;
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