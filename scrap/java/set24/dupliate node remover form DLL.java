// dupliate node remover form DLL
// r3, q1, set23

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,1,2,3,1};
        DLL dll = new DLL();
        for (Integer value : array)
        {
            dll.insert(value);
        }

        DuplicateRemover dr = new DuplicateRemover();
        DLL.print(dr.removeDuplicate(dll));
    }
}

class DuplicateRemover
{
    private Node head;
    private Map<Integer, Node> valueNode;

    public Node removeDuplicate(DLL dll)
    {
        this.head = dll.getHead();
        this.valueNode = new HashMap<>();

        Node cursor = head;
        while (cursor != null)
        {
            Node savedNode = valueNode.get(cursor.getValue());
            if (savedNode != null)
            {
                delete(savedNode);
            }

            valueNode.put(cursor.getValue(), cursor);
            cursor = cursor.getRight();
        }

        return head;
    }

    private void delete(Node savedNode)
    {
        Node parentSavedNode  = savedNode.getLeft();
        if (parentSavedNode == null)
        {
            head = head.getRight();
            head.setLeft(null);
            savedNode.setRight(null);
        }
        else
        {
            parentSavedNode.setRight(savedNode.getRight());
            savedNode.getRight().setLeft(parentSavedNode);
        }
    }
}

class DLL
{
    private Node head;

    public DLL()
    {
        this.head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            Node cursor = head;

            while(cursor.getRight() != null)
            {
                cursor = cursor.getRight();
            }

            Node node = new Node(value);
            cursor.setRight(node);
            node.setLeft(cursor);
        }
    }

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        Node cursor = node;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getRight();
        }
        System.out.println();
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