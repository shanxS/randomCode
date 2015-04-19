// written test, q2, set 5, amazon
// Methods to serialize & deserialize a tree ,must complete the below 2 monthods. File serialize (node *root) & node * deserialize(File f)
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(3);
        bst.insert(1);
        bst.insert(9);

        bst.print();

        List<Integer> list = bst.serialize();
        System.out.print("serialised: ");
        for (Integer value : list)
        {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("deserialised: ");
        bst.deserialize(list);
        bst.print();
    }
}

class Node
{
    private Integer m_value;
    private Node m_right;
    private Node m_left;

    public Node(Integer value)
    {
        m_value = value;
        m_right = null;
        m_left = null;
    }

    public Node()
    {
        m_value = null;
        m_right = null;
        m_left = null;
    }

    public Integer getValue() {
        return m_value;
    }

    public void setValue(Integer m_value) {
        this.m_value = m_value;
    }

    public Node getRight() {
        return m_right;
    }

    public void setRight(Node m_right) {
        this.m_right = m_right;
    }

    public void setLeft(Node left)
    {
        m_left = left;
    }

    public Node getLeft()
    {
        return m_left;
    }
}

class BST
{
    private Node m_head;

    public BST()
    {
        m_head = null;
    }

    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            insert(m_head, value);
        }
    }

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
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
        else
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
    }

    public void print()
    {
        print(m_head);
    }

    private void print(Node node) {
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

    public List<Integer> serialize()
    {
        List<Integer> tree = new ArrayList<>();

        Node cursor = m_head;
        tree.add(cursor.getValue());
        serialize(tree, 0, cursor);

        return tree;
    }

    private void serialize(List<Integer> tree, Integer pIndex, Node pcursor) {
        if (pcursor.getRight() != null)
        {
            Integer rightChildIndex = getRightChildIndex(pIndex);
            while (tree.size()-1 < rightChildIndex)
            {
                tree.add(null);
            }

            tree.set(rightChildIndex, pcursor.getRight().getValue());
            serialize(tree, rightChildIndex, pcursor.getRight());
        }

        if (pcursor.getLeft() != null)
        {
            Integer leftChildIndex = getLeftChildIndex(pIndex);
            while (tree.size()-1 < leftChildIndex)
            {
                tree.add(null);
            }

            tree.set(leftChildIndex, pcursor.getLeft().getValue());
            serialize(tree, leftChildIndex, pcursor.getLeft());
        }
    }

    private Integer getLeftChildIndex(Integer pIndex) {
        return (pIndex*2) + 1;
    }

    private Integer getRightChildIndex(Integer pIndex) {
        return (pIndex*2) + 2;
    }

    public void deserialize(List<Integer> tree)
    {
        m_head = null;

        for (Integer value : tree)
        {
            if (value != null) {
                insert(value);
            }
        }
    }

}